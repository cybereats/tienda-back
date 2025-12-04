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

    public UserResponse fromDtoToUserResponse(UserDto userDto) {
        return new UserResponse(
                userDto.id(),
                userDto.name(),
                userDto.surname(),
                userDto.born_date(),
                userDto.username(),
                userDto.password());
    }

    public UserDto fromUserRequestToDto(UserRequest userRequest) {
        return new UserDto(
                userRequest.id(),
                userRequest.name(),
                userRequest.surname(),
                userRequest.born_date(),
                userRequest.username(),
                userRequest.password());
    }
}