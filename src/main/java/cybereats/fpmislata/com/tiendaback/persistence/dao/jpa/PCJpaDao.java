package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;

import java.util.List;
import java.util.Optional;

public interface PCJpaDao extends GenericJpaDao<PCJpaEntity> {
    Optional<PCJpaEntity> findBySlug(String slug);

    List<PCJpaEntity> findAll();

    void deleteBySlug(String slug);

    java.util.List<PCJpaEntity> search(String text, String category, int page, int size);

    long countSearch(String text, String category);
}
