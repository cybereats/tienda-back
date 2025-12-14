package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.service.AuthService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.AuthResponseDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LoginRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.RegisterRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
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
}
