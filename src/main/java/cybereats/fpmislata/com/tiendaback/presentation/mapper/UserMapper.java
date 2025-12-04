package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.UserRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.UserResponse;

public class UserMapper {
    public static UserResponse fromDtoToUserResponse(UserDto userDto) {
        return new UserResponse(
                userDto.id(),
                userDto.name(),
                userDto.surname(),
                userDto.born_date(),
                userDto.username(),
                userDto.password());
    }

    public static UserDto fromUserResponseToDto(UserResponse userResponse) {
        return new UserDto(
                userResponse.id(),
                userResponse.name(),
                userResponse.surname(),
                userResponse.born_date(),
                userResponse.username(),
                userResponse.password());
    }

    public static UserRequest fromUserDtoToUserRequest(UserDto userDto) {
        return new UserRequest(
                userDto.id(),
                userDto.name(),
                userDto.surname(),
                userDto.born_date(),
                userDto.username(),
                userDto.password());
    }

    public static UserDto fromUserRequestToDto(UserRequest userRequest) {
        return new UserDto(
                userRequest.id(),
                userRequest.name(),
                userRequest.surname(),
                userRequest.born_date(),
                userRequest.username(),
                userRequest.password());
    }
}