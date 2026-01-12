package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;

public interface CategoryProductJpaDao extends GenericJpaDao<CategoryProductJpaEntity> {

    List<CategoryProductJpaEntity> findAll();

    Optional<CategoryProductJpaEntity> findBySlug(String slug);

    void deleteBySlug(String slug);
}
