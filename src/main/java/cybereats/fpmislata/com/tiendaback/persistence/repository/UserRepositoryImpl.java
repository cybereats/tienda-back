package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
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
                        UserJpaEntity userJpaEntity = UserMapper.getInstance().fromUserDtoToUserJpaEntity(user);
                        return UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaDao.update(userJpaEntity));
                } else {
                        UserJpaEntity userJpaEntity = UserMapper.getInstance().fromUserDtoToUserJpaEntity(user);
                        return UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaDao.insert(userJpaEntity));
                }
        }

        @Override
        public Optional<UserDto> findById(Long id) {
                Optional<UserJpaEntity> userJpaEntity = userJpaDao.findById(id);
                return userJpaEntity.map(entity -> UserMapper.getInstance().fromUserJpaEntityToUserDto(entity));
        }

        @Override
        public Page<UserDto> findAll(int page, int size) {
                List<UserDto> content = userJpaDao.findAll(page, size).stream()
                                .map(entity -> UserMapper.getInstance().fromUserJpaEntityToUserDto(entity))
                                .toList();
                long totalElements = userJpaDao.count();
                return new Page<>(content, page, size, totalElements);
        }

        @Override
        public Page<UserDto> search(String text, String role, int page, int size) {
                List<UserDto> content = userJpaDao.search(text, role, page, size).stream()
                                .map(entity -> UserMapper.getInstance().fromUserJpaEntityToUserDto(entity))
                                .toList();
                long totalElements = userJpaDao.countSearch(text, role);
                return new Page<>(content, page, size, totalElements);
        }

        @Override
        public void deleteById(Long id) {
                userJpaDao.deleteById(id);
        }
}
