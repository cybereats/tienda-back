package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.mapper.TokenMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Token;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.repository.AuthRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.AuthService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.AuthResponseDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LoginRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.RegisterRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User getUsuarioFromToken(Token token) {
        if (!JwtUtil.validateToken(token.getToken())) {
            throw new BusinessException("Invalid or expired token");
        }

        Long userId = JwtUtil.extractUserId(token.getToken());
        String username = JwtUtil.extractUsername(token.getToken());
        String name = JwtUtil.extractName(token.getToken());

        return new User.Builder()
                .id(userId)
                .username(username)
                .name(name)
                .build();
    }

    @Override
    public Token createTokenFromUser(User user) {
        String tokenString = JwtUtil.generateToken(user);
        LocalDateTime expiresAt = JwtUtil.getExpirationTime();

        return TokenMapper.getInstance().fromStringToToken(tokenString, expiresAt);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        UserDto userDto = authRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));

        if (!passwordEncoder.matches(request.password(), userDto.password())) {
            throw new BusinessException("Invalid username or password");
        }

        User user = new User.Builder()
                .id(userDto.id())
                .name(userDto.name())
                .surname(userDto.surname())
                .bornDate(userDto.bornDate())
                .username(userDto.username())
                .password(userDto.password())
                .build();

        Token token = createTokenFromUser(user);

        UserDto userResponse = new UserDto(
                userDto.id(),
                userDto.name(),
                userDto.surname(),
                userDto.email(),
                userDto.bornDate(),
                userDto.username(),
                null,
                userDto.role()
        );

        return new AuthResponseDto(
                token.getToken(),
                token.getExpiresAt(),
                userResponse
        );
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (authRepository.existsByUsername(request.username())) {
            throw new BusinessException("Username already exists");
        }

        UserDto newUser = new UserDto(
                null,
                request.name(),
                request.surname(),
                request.email(),
                request.bornDate(),
                request.username(),
                request.password(),
                request.role()
        );

        UserDto savedUser = authRepository.register(newUser);

        User user = new User.Builder()
                .id(savedUser.id())
                .name(savedUser.name())
                .surname(savedUser.surname())
                .bornDate(savedUser.bornDate())
                .username(savedUser.username())
                .password(savedUser.password())
                .build();

        Token token = createTokenFromUser(user);

        UserDto userResponse = new UserDto(
                savedUser.id(),
                savedUser.name(),
                savedUser.surname(),
                savedUser.email(),
                savedUser.bornDate(),
                savedUser.username(),
                null,
                savedUser.role()
        );

        return new AuthResponseDto(
                token.getToken(),
                token.getExpiresAt(),
                userResponse
        );
    }
}
