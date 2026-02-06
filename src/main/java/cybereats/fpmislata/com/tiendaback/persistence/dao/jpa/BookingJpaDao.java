package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;

import java.util.List;
import java.util.Optional;

public interface BookingJpaDao extends GenericJpaDao<BookingJpaEntity> {
    List<BookingJpaEntity> findAll();

    Optional<BookingJpaEntity> findActiveByUserId(Long userId);

    List<BookingJpaEntity> findAllActiveByUserId(Long userId);

    long getMaxId();
}
