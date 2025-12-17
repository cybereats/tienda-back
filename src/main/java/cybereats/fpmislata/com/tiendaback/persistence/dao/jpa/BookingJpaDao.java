package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;

import java.util.List;

public interface BookingJpaDao extends GenericJpaDao<BookingJpaEntity> {
    List<BookingJpaEntity> findAll();
}
