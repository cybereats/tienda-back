package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaDao {
    UserJpaEntity insert(UserJpaEntity user);

    UserJpaEntity update(UserJpaEntity user);

<<<<<<< HEAD
<<<<<<< HEAD
    Optional<UserJpaEntity> findById(Long id);

    List<UserJpaEntity> findAll();
=======
    Optional<UserJpaEntity> getById(Long id);

    List<UserJpaEntity> getAll();
>>>>>>> ismael_7
=======
    Optional<UserJpaEntity> getById(Long id);

    List<UserJpaEntity> getAll();
>>>>>>> ismael_8

    void deleteById(Long id);
}
