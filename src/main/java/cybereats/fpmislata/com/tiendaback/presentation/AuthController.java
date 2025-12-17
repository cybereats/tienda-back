package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.mapper.TokenMapper;
import cybereats.fpmislata.com.tiendaback.domain.mapper.UserMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Token;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.AuthService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.AuthResponseDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LoginRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.RegisterRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserProfileResponse;
import cybereats.fpmislata.com.tiendaback.security.AllowedRoles;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        DtoValidator.validate(loginRequest);
        AuthResponseDto response = authService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequest) {
        DtoValidator.validate(registerRequest);
        AuthResponseDto response = authService.register(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    @AllowedRoles({UserRole.ADMIN, UserRole.CLIENT})
    public ResponseEntity<UserProfileResponse> verify(HttpServletRequest request) {
        String tokenString = extractToken(request);
        if (tokenString == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Token token = TokenMapper.getInstance().fromStringToToken(tokenString, JwtUtil.extractExpirationDate(tokenString));
        User user = authService.getUsuarioFromToken(token);

        UserDto userDto = cybereats.fpmislata.com.tiendaback.domain.mapper.UserMapper.getInstance().fromUserToUserDto(user);
        UserProfileResponse userResponse = cybereats.fpmislata.com.tiendaback.presentation.mapper.UserMapper.getInstance().fromUserDtoToUserProfileResponse(userDto);

        return ResponseEntity.ok(userResponse);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
