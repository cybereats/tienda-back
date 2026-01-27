package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaDao extends GenericJpaDao<UserJpaEntity> {
    Optional<UserJpaEntity> findByUsername(String username);

    Optional<UserJpaEntity> findByEmail(String email);

    List<UserJpaEntity> search(String text, String role, int page, int size);

    long countSearch(String text, String role);
}
