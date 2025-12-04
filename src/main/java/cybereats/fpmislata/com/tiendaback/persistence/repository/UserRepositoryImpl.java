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
                        UserJpaEntity userJpaEntity = UserMapper.getInstance().userDtoToUserJpaEntity(user);
                        return UserMapper.getInstance().userJpaEntityToUserDto(userJpaDao.update(userJpaEntity));
                } else {
                        UserJpaEntity userJpaEntity = UserMapper.getInstance().userDtoToUserJpaEntity(user);
                        return UserMapper.getInstance().userJpaEntityToUserDto(userJpaDao.insert(userJpaEntity));
                }
        }

        @Override
        public Optional<UserDto> getUserById(Long id) {
                Optional<UserJpaEntity> userJpaEntity = userJpaDao.getById(id);
                return userJpaEntity.map(entity -> UserMapper.getInstance().userJpaEntityToUserDto(entity));
        }

        @Override
        public List<UserDto> getAll() {
                return userJpaDao.getAll().stream()
                                .map(entity -> UserMapper.getInstance().userJpaEntityToUserDto(entity))
                                .toList();
        }

        @Override
        public void deleteById(Long id) {
                userJpaDao.deleteById(id);
        }
}
