package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

  private final UserMapper mapper = UserMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de UserJpaEntity a UserDto")
  void shouldMapEntityToDto() {
    UserJpaEntity entity = new UserJpaEntity(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password",
        UserRole.CLIENT);

    UserDto dto = mapper.fromUserJpaEntityToUserDto(entity);

    assertNotNull(dto);
    assertEquals(entity.getId(), dto.id());
    assertEquals(entity.getUsername(), dto.username());
  }

  @Test
  @DisplayName("Debería mapear de UserDto a UserJpaEntity")
  void shouldMapDtoToEntity() {
    UserDto dto = new UserDto(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password",
        UserRole.CLIENT);

    UserJpaEntity entity = mapper.fromUserDtoToUserJpaEntity(dto);

    assertNotNull(entity);
    assertEquals(dto.id(), entity.getId());
    assertEquals(dto.username(), entity.getUsername());
  }
}
