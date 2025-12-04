package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.repository.UserRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.UserMapper;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserJpaDao userJpaDao;

    public UserRepositoryImpl(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Override
    public UserDto save(UserDto user) {

        if (user.id() != null) {
            UserJpaEntity userJpaEntity = UserMapper.toUserJpaEntity(user);
            return UserMapper.toUserDto(userJpaDao.update(userJpaEntity));
        } else {
            UserJpaEntity userJpaEntity = UserMapper.toUserJpaEntity(user);
            return UserMapper.toUserDto(userJpaDao.insert(userJpaEntity));
        }
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<UserJpaEntity> userJpaEntity = userJpaDao.findById(id);
        return Optional.of(UserMapper.toUserDto(userJpaEntity.get()));
    }

    @Override
    public List<UserDto> getAll() {
        return userJpaDao.findAll().stream().map(UserMapper::toUserDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        userJpaDao.deleteById(id);
    }
}
