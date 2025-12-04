package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;

import java.util.Optional;

public interface PCJpaDao extends GenericJpaDao<PCJpaEntity> {
    Optional<PCJpaEntity> findBySlug(String slug);
    void deleteBySlug(String slug);
}
