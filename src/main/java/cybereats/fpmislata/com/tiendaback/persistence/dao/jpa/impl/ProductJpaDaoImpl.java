package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductJpaDaoImpl implements ProductJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductJpaEntity> findAll(int page, int size) {
        int pageIndex = Math.max(page - 1, 0);

        String sql = "SELECT b FROM ProductJpaEntity b ORDER BY b.id";
        TypedQuery<ProductJpaEntity> productJpaEntityPage = entityManager
                .createQuery(sql, ProductJpaEntity.class)
                .setFirstResult(pageIndex * size)
                .setMaxResults(size);

        return productJpaEntityPage.getResultList();
    }

    @Override
    public Optional<ProductJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ProductJpaEntity.class, id));
    }

    @Override
    public ProductJpaEntity insert(ProductJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public ProductJpaEntity update(ProductJpaEntity jpaEntity) {
        ProductJpaEntity managed = entityManager.find(ProductJpaEntity.class, jpaEntity.getId());
        if (managed == null) {
            throw new ResourceNotFoundException("Product with id " + jpaEntity.getId() + " not found");
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(ProductJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM ProductJpaEntity b", Long.class)
                .getSingleResult();
    }

    @Override
    public Optional<ProductJpaEntity> findBySlug(String slug) {
        String sql = "SELECT b FROM ProductJpaEntity b WHERE b.slug = :slug";
        try {
            return Optional.of(entityManager.createQuery(sql, ProductJpaEntity.class)
                    .setParameter("slug", slug)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteBySlug(String slug) {
        findBySlug(slug).ifPresent(entityManager::remove);
    }

    @Override
    public List<ProductJpaEntity> search(String text, String category, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductJpaEntity> cq = cb.createQuery(ProductJpaEntity.class);
        Root<ProductJpaEntity> product = cq.from(ProductJpaEntity.class);

        Predicate[] predicates = getSearchPredicates(cb, product, text, category);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }
        cq.orderBy(cb.asc(product.get("id")));

        return entityManager.createQuery(cq)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long countSearch(String text, String category) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProductJpaEntity> product = cq.from(ProductJpaEntity.class);

        cq.select(cb.count(product));
        Predicate[] predicates = getSearchPredicates(cb, product, text, category);
        if (predicates.length > 0) {
            cq.where(cb.and(predicates));
        }

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate[] getSearchPredicates(CriteriaBuilder cb, Root<ProductJpaEntity> root, String text,
            String category) {
        List<Predicate> predicates = new ArrayList<>();

        if (text != null && !text.isBlank() && !text.equalsIgnoreCase("null")) {
            String pattern = "%" + text.toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("id").as(String.class)), pattern),
                    cb.like(cb.lower(root.get("label")), pattern),
                    cb.like(cb.lower(root.get("slug")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern),
                    cb.like(cb.lower(root.get("categoryProductJpaEntity").get("label")), pattern)));
        }

        if (category != null && !category.isBlank() && !category.equalsIgnoreCase("Todas las categorías")
                && !category.equalsIgnoreCase("null")) {
            predicates.add(cb.or(
                    cb.equal(cb.lower(root.get("categoryProductJpaEntity").get("label")), category.toLowerCase()),
                    cb.equal(cb.lower(root.get("categoryProductJpaEntity").get("slug")), category.toLowerCase())));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
