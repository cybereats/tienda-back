package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;

import java.util.Optional;

public interface CategoryPCJpaDao extends GenericJpaDao<CategoryPCJpaEntity> {
    Optional<CategoryPCJpaEntity> findBySlug(String slug);
}
