package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getBorn_date(),
                user.getUsername(),
                user.getPassword());

    }

    public static User toUser(UserDto userDto) {
        return new User.Builder()
                .id(userDto.id())
                .name(userDto.name())
                .surname(userDto.surname())
                .born_date(userDto.born_date())
                .username(userDto.username())
                .password(userDto.password())
                .build();
    }
}