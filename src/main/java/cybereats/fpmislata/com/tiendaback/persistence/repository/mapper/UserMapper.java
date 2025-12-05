package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

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

    public UserJpaEntity fromUserDtoToUserJpaEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return new UserJpaEntity(
                userDto.id(),
                userDto.name(),
                userDto.surname(),
                userDto.bornDate(),
                userDto.username(),
                userDto.password());
    }

    public UserDto fromUserJpaEntityToUserDto(UserJpaEntity userJpaEntity) {
        if (userJpaEntity == null) {
            return null;
        }

        return new UserDto(
                userJpaEntity.getId(),
                userJpaEntity.getName(),
                userJpaEntity.getSurname(),
                userJpaEntity.getBornDate(),
                userJpaEntity.getUsername(),
                userJpaEntity.getPassword());
    }
}
