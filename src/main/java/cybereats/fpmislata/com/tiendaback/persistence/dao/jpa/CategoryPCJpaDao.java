package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryPCJpaDao extends GenericJpaDao<CategoryPCJpaEntity> {
    List<CategoryPCJpaEntity> findAll();

    Optional<CategoryPCJpaEntity> findBySlug(String slug);
}
