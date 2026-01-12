package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;

import java.util.Optional;

public interface ProductJpaDao extends GenericJpaDao<ProductJpaEntity> {
    Optional<ProductJpaEntity> findBySlug(String slug);

    void deleteBySlug(String slug);

    java.util.List<ProductJpaEntity> search(String text, String category, int page, int size);

    long countSearch(String text, String category);
}
