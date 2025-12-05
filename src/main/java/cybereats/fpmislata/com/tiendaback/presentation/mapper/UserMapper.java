package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserResponse;

public class UserMapper {
    private static UserMapper INSTANCE;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }
        return INSTANCE;
    }

    public UserResponse fromUserDtoToUserResponse(UserDto userDto) {
        return new UserResponse(
                userDto.id(),
                userDto.name(),
                userDto.surname(),
                userDto.bornDate(),
                userDto.username(),
                userDto.password());
    }

    public UserDto fromUserRequestToUserDto(UserRequest userRequest) {
        return new UserDto(
                userRequest.id(),
                userRequest.name(),
                userRequest.surname(),
                userRequest.born_date(),
                userRequest.username(),
                userRequest.password());
    }
}