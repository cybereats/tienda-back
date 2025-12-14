package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.repository.AuthRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class AuthRepositoryImpl implements AuthRepository {

    private final UserJpaDao userJpaDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthRepositoryImpl(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<UserJpaEntity> userJpaEntity = userJpaDao.findByUsername(username);
        return userJpaEntity.map(entity -> UserMapper.getInstance().fromUserJpaEntityToUserDto(entity));
    }

    @Override
    @Transactional
    public UserDto register(UserDto user) {
        UserDto userWithHashedPassword = new UserDto(
                user.id(),
                user.name(),
                user.surname(),
                user.bornDate(),
                user.username(),
                passwordEncoder.encode(user.password())
        );

        UserJpaEntity userJpaEntity = UserMapper.getInstance().fromUserDtoToUserJpaEntity(userWithHashedPassword);
        UserJpaEntity savedEntity = userJpaDao.insert(userJpaEntity);
        return UserMapper.getInstance().fromUserJpaEntityToUserDto(savedEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaDao.findByUsername(username).isPresent();
    }
}
