package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;

public interface CategoryProductJpaDao extends GenericJpaDao<CategoryProductJpaEntity> {

    Optional<CategoryProductJpaEntity> findBySlug(String slug);

    void deleteBySlug(String slug);
}
