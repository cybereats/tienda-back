package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.Optional;

public interface UserJpaDao extends GenericJpaDao<UserJpaEntity> {
    Optional<UserJpaEntity> findByUsername(String username);
}
