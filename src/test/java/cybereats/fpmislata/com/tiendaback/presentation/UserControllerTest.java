package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.UserService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserRequest;
import cybereats.fpmislata.com.tiendaback.security.AuthInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  @MockitoBean
  private AuthInterceptor authInterceptor;

  @Autowired
  private ObjectMapper objectMapper;

  private UserDto userDto;

  @BeforeEach
  void setUp() throws Exception {
    when(authInterceptor.preHandle(any(), any(), any())).thenReturn(true);
    userDto = new UserDto(1L, "John", "Doe", "john@example.com", "01-01-1990", "johndoe", "password", UserRole.CLIENT);
  }

  @Nested
  @DisplayName("Tests para el método getAllUsers")
  class GetAllUsersTests {
    @Test
    @DisplayName("Debería devolver 200 y una página de usuarios")
    void shouldReturnOkAndPage() throws Exception {
      Page<UserDto> page = new Page<>(List.of(userDto), 1, 10, 1L);
      when(userService.findAll(1, 10)).thenReturn(page);

      mockMvc.perform(get("/api/users?page=1&size=10"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data[0].id").value(userDto.id()))
          .andExpect(jsonPath("$.data[0].username").value(userDto.username()));
    }
  }

  @Nested
  @DisplayName("Tests para el método getUserById")
  class GetUserByIdTests {
    @Test
    @DisplayName("Debería devolver 200 cuando el usuario existe")
    void shouldReturnOkWhenExists() throws Exception {
      when(userService.findById(1L)).thenReturn(Optional.of(userDto));

      mockMvc.perform(get("/api/users/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(userDto.id()))
          .andExpect(jsonPath("$.username").value(userDto.username()));
    }
  }

  @Nested
  @DisplayName("Tests para el método createUser")
  class CreateUserTests {
    @Test
    @DisplayName("Debería devolver 201 cuando la petición es válida")
    void shouldReturnCreatedWhenValid() throws Exception {
      UserRequest request = new UserRequest(null, "New", "User", "new@example.com", "01-01-1990", "newuser", "pass",
          UserRole.CLIENT);
      UserDto createdDto = new UserDto(2L, "New", "User", "new@example.com", "01-01-1990", "newuser", "pass",
          UserRole.CLIENT);
      when(userService.insert(any(UserDto.class))).thenReturn(createdDto);

      mockMvc.perform(post("/api/users")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(2L))
          .andExpect(jsonPath("$.username").value("newuser"));
    }
  }

  @Nested
  @DisplayName("Tests para el método updateUser")
  class UpdateUserTests {
    @Test
    @DisplayName("Debería devolver 200 cuando la petición es válida")
    void shouldReturnOkWhenValid() throws Exception {
      UserRequest request = new UserRequest(1L, "Updated", "User", "upd@example.com", "01-01-1990", "johndoe",
          "newpass", UserRole.CLIENT);
      when(userService.update(any(UserDto.class))).thenReturn(userDto);

      mockMvc.perform(put("/api/users/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Debería devolver 400 cuando los IDs no coinciden")
    void shouldReturnBadRequestWhenIdMismatch() throws Exception {
      UserRequest request = new UserRequest(2L, "Mismatch", "User", "mismatch@example.com", "01-01-1990", "mismatch",
          "pass", UserRole.CLIENT);

      mockMvc.perform(put("/api/users/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteUser")
  class DeleteUserTests {
    @Test
    @DisplayName("Debería devolver 204 cuando el borrado es exitoso")
    void shouldReturnNoContent() throws Exception {
      doNothing().when(userService).deleteById(1L);

      mockMvc.perform(delete("/api/users/1"))
          .andExpect(status().isNoContent());
    }
  }
}
