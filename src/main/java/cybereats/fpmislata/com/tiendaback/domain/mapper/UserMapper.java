package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;

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

    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getBorn_date(),
                user.getUsername(),
                user.getPassword());
    }

    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

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