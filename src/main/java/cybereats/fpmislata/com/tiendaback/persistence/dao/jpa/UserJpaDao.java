package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaDao {
    UserJpaEntity insert(UserJpaEntity user);

    UserJpaEntity update(UserJpaEntity user);

    Optional<UserJpaEntity> findById(Long id);

    List<UserJpaEntity> findAll();

    void deleteById(Long id);
}
