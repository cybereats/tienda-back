package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.impl;

import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.BookingJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class BookingJpaDaoImpl implements BookingJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BookingJpaEntity> findAll(int page, int size) {
        int pageIndex = Math.max(page - 1, 0);

        String sql = "SELECT b FROM BookingJpaEntity b ORDER BY b.id";
        TypedQuery<BookingJpaEntity> bookingJpaEntityPage = entityManager
                .createQuery(sql, BookingJpaEntity.class)
                .setFirstResult(pageIndex * size)
                .setMaxResults(size);

        return bookingJpaEntityPage.getResultList();
    }

    @Override
    public Optional<BookingJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BookingJpaEntity.class, id));
    }

    @Override
    public BookingJpaEntity insert(BookingJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public BookingJpaEntity update(BookingJpaEntity jpaEntity) {
        BookingJpaEntity managed = entityManager.find(BookingJpaEntity.class, jpaEntity.getId());
        if(managed == null) {
            throw new ResourceNotFoundException("Booking with id " + jpaEntity.getId() + " not found");
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(BookingJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM BookingJpaEntity b", Long.class)
                .getSingleResult();
    }
}
