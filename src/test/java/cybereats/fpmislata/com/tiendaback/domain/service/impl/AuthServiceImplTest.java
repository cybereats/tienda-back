package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Token;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.repository.AuthRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.AuthResponseDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LoginRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.RegisterRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

  @Mock
  private AuthRepository authRepository;

  @InjectMocks
  private AuthServiceImpl authService;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  private UserDto userDto;
  private User user;
  private String rawPassword = "password123";

  @BeforeEach
  void setUp() {
    String encodedPassword = passwordEncoder.encode(rawPassword);
    userDto = new UserDto(
        1L,
        "Test",
        "User",
        "test@example.com",
        "2000-01-01",
        "testuser",
        encodedPassword,
        UserRole.CLIENT);

    user = new User.Builder()
        .id(1L)
        .name("Test")
        .username("testuser")
        .role(UserRole.CLIENT)
        .build();
  }

  @Nested
  @DisplayName("Tests for login")
  class LoginTests {

    @Test
    @DisplayName("Should return AuthResponseDto when login is successful")
    void shouldReturnTokenWhenLoginSuccessful() {
      LoginRequestDto loginRequest = new LoginRequestDto("testuser", rawPassword);
      when(authRepository.findByUsername("testuser")).thenReturn(Optional.of(userDto));

      AuthResponseDto response = authService.login(loginRequest);

      assertNotNull(response);
      assertNotNull(response.token());
      assertEquals(userDto.username(), response.user().username());
      verify(authRepository).findByUsername("testuser");
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when user not found")
    void shouldThrowExceptionWhenUserNotFound() {
      LoginRequestDto loginRequest = new LoginRequestDto("unknown", "pass");
      when(authRepository.findByUsername("unknown")).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> authService.login(loginRequest));
    }

    @Test
    @DisplayName("Should throw BusinessException when password invalid")
    void shouldThrowExceptionWhenPasswordInvalid() {
      LoginRequestDto loginRequest = new LoginRequestDto("testuser", "wrongpass");
      when(authRepository.findByUsername("testuser")).thenReturn(Optional.of(userDto));

      assertThrows(BusinessException.class, () -> authService.login(loginRequest));
    }
  }

  @Nested
  @DisplayName("Tests for register")
  class RegisterTests {

    @Test
    @DisplayName("Should return AuthResponseDto when register successful")
    void shouldReturnTokenWhenRegisterSuccessful() {
      RegisterRequestDto registerRequest = new RegisterRequestDto(
          "Test", "User", "test@example.com", "2000-01-01", "newuser", rawPassword, UserRole.CLIENT);

      when(authRepository.existsByUsername("newuser")).thenReturn(false);
      when(authRepository.register(any(UserDto.class))).thenReturn(userDto);

      AuthResponseDto response = authService.register(registerRequest);

      assertNotNull(response);
      assertNotNull(response.token());
      verify(authRepository).register(any(UserDto.class));
    }

    @Test
    @DisplayName("Should throw BusinessException when username exists")
    void shouldThrowExceptionWhenUsernameExists() {
      RegisterRequestDto registerRequest = new RegisterRequestDto(
          "Test", "User", "test@example.com", "2000-01-01", "existing", rawPassword, UserRole.CLIENT);

      when(authRepository.existsByUsername("existing")).thenReturn(true);

      assertThrows(BusinessException.class, () -> authService.register(registerRequest));
      verify(authRepository, never()).register(any());
    }
  }

  @Nested
  @DisplayName("Tests for getUsuarioFromToken")
  class GetUsuarioFromTokenTests {

    @Test
    @DisplayName("Should return User when token is valid")
    void shouldReturnUserWhenTokenValid() {
      String token = JwtUtil.generateToken(user);
      Token tokenObj = new Token.Builder()
          .token(token)
          .expiresAt(LocalDateTime.now().plusHours(1))
          .build();

      when(authRepository.findById(1L)).thenReturn(Optional.of(userDto));

      User result = authService.getUsuarioFromToken(tokenObj);

      assertNotNull(result);
      assertEquals(1L, result.getId());
      assertEquals("testuser", result.getUsername());
    }

    @Test
    @DisplayName("Should throw BusinessException when token is invalid")
    void shouldThrowExceptionWhenTokenInvalid() {
      Token tokenObj = new Token.Builder()
          .token("invalid.token.string")
          .expiresAt(LocalDateTime.now().plusHours(1))
          .build();

      assertThrows(BusinessException.class, () -> authService.getUsuarioFromToken(tokenObj));
    }
  }
}
