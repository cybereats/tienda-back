package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

public class UserMapper {

    public static UserJpaEntity toUserJpaEntity(UserDto userDto) {
        return new UserJpaEntity(
                userDto.id(),
                userDto.name(),
                userDto.surname(),
                userDto.born_date(),
                userDto.username(),
                userDto.password());
    }

    public static UserDto toUserDto(UserJpaEntity userJpaEntity) {
        return new UserDto(
                userJpaEntity.getId(),
                userJpaEntity.getName(),
                userJpaEntity.getSurname(),
                userJpaEntity.getBorn_date(),
                userJpaEntity.getUsername(),
                userJpaEntity.getPassword());
    }
}
