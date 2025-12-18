package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserProfileResponse;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

  private final UserMapper mapper = UserMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de UserRequest a UserDto")
  void shouldMapRequestToDto() {
    UserRequest request = new UserRequest(1L, "John", "Doe", "john@example.com", "01-01-1990", "johndoe", "password",
        UserRole.CLIENT);

    UserDto dto = mapper.fromUserRequestToUserDto(request);

    assertNotNull(dto);
    assertEquals(request.id(), dto.id());
    assertEquals(request.name(), dto.name());
    assertEquals(request.born_date(), dto.bornDate());
    assertEquals(request.role(), dto.role());
  }

  @Test
  @DisplayName("Debería mapear de UserDto a UserResponse")
  void shouldMapDtoToResponse() {
    UserDto dto = new UserDto(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password",
        UserRole.CLIENT);

    UserResponse response = mapper.fromUserDtoToUserResponse(dto);

    assertNotNull(response);
    assertEquals(dto.id(), response.id());
    assertEquals(dto.name(), response.name());
    assertEquals(dto.username(), response.username());
  }

  @Test
  @DisplayName("Debería mapear de UserDto a UserProfileResponse")
  void shouldMapDtoToProfileResponse() {
    UserDto dto = new UserDto(1L, "John", "Doe", "john@example.com", "1990-01-01", "johndoe", "password",
        UserRole.CLIENT);

    UserProfileResponse response = mapper.fromUserDtoToUserProfileResponse(dto);

    assertNotNull(response);
    assertEquals(dto.id(), response.id());
    assertEquals(dto.username(), response.username());
  }
}
