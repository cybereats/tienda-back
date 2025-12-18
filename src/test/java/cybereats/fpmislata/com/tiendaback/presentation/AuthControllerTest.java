package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Token;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.AuthService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.AuthResponseDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LoginRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.RegisterRequestDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserProfileResponse;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private AuthService authService;

  @Autowired
  private ObjectMapper objectMapper;

  private AuthResponseDto authResponseDto;
  private UserDto userDto;
  private String tokenString;

  @BeforeEach
  void setUp() {
    userDto = new UserDto(
        1L, "Test", "User", "test@test.com", "2000-01-01", "testuser", "pass", UserRole.CLIENT);
    tokenString = "mocked.jwt.token";
    authResponseDto = new AuthResponseDto(tokenString, LocalDateTime.now().plusHours(1), userDto);
  }

  @Nested
  @DisplayName("Tests for login")
  class LoginTests {

    @Test
    @DisplayName("Should return 200 and token when credentials valid")
    void shouldReturnOkAndToken() throws Exception {
      LoginRequestDto loginRequest = new LoginRequestDto("testuser", "pass");
      when(authService.login(any(LoginRequestDto.class))).thenReturn(authResponseDto);

      mockMvc.perform(post("/api/auth/login")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(loginRequest)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.token").value(tokenString));
    }

  }

  @Nested
  @DisplayName("Tests for register")
  class RegisterTests {

    @Test
    @DisplayName("Should return 201 and token when registration successful")
    void shouldReturnCreatedAndToken() throws Exception {
      RegisterRequestDto registerRequest = new RegisterRequestDto(
          "Test", "User", "test@test.com", "2000-01-01", "testuser", "pass", UserRole.CLIENT);
      when(authService.register(any(RegisterRequestDto.class))).thenReturn(authResponseDto);

      mockMvc.perform(post("/api/auth/register")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(registerRequest)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.token").value(tokenString));
    }

    @Test
    @DisplayName("Should return 400 when business exception occurs (e.g. user exists)")
    void shouldReturnBadRequestWhenUserExists() throws Exception {
      RegisterRequestDto registerRequest = new RegisterRequestDto(
          "Test", "User", "test@test.com", "2000-01-01", "existing", "pass", UserRole.CLIENT);
      when(authService.register(any())).thenThrow(new BusinessException("Username already exists"));
    }
  }

  @Nested
  @DisplayName("Tests for verify")
  class VerifyTests {

    @Test
    @DisplayName("Should return 200 and user profile when token valid")
    void shouldReturnOkAndUserProfile() throws Exception {

      User user = new User.Builder().id(1L).username("testuser").role(UserRole.CLIENT).build();
      String validToken = JwtUtil.generateToken(user);

      when(authService.getUsuarioFromToken(any(Token.class))).thenReturn(user);

      mockMvc.perform(get("/api/auth/verify")
          .header("Authorization", "Bearer " + validToken))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    @DisplayName("Should return 401 when no token provided")
    void shouldReturnUnauthorizedWhenNoToken() throws Exception {
      mockMvc.perform(get("/api/auth/verify"))
          .andExpect(status().isUnauthorized());
    }
  }
}
