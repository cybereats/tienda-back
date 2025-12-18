package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

  private final UserMapper mapper = UserMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de User a UserDto")
  void shouldMapModelToDto() {
    User user = new User.Builder()
        .id(1L)
        .name("John")
        .surname("Doe")
        .email("john@example.com")
        .bornDate("1990-01-01")
        .username("johndoe")
        .password("password")
        .role(UserRole.CLIENT)
        .build();

    UserDto dto = mapper.fromUserToUserDto(user);

    assertNotNull(dto);
    assertEquals(user.getId(), dto.id());
    assertEquals(user.getName(), dto.name());
    assertEquals(user.getUsername(), dto.username());
    assertEquals(user.getRole(), dto.role());
  }

  @Test
  @DisplayName("Debería mapear de UserDto a User")
  void shouldMapDtoToModel() {
    UserDto dto = new UserDto(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password",
        UserRole.CLIENT);

    User user = mapper.fromUserDtoToUser(dto);

    assertNotNull(user);
    assertEquals(dto.id(), user.getId());
    assertEquals(dto.name(), user.getName());
    assertEquals(dto.username(), user.getUsername());
    assertEquals(dto.role(), user.getRole());
  }
}
