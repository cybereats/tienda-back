package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;

public interface UserOrderJpaDao {
    public Optional<UserOrderJpaEntity> findById(Long id);

    public List<UserOrderJpaEntity> findAll();

    public List<UserOrderJpaEntity> findAll(int page, int size);

    public UserOrderJpaEntity insert(UserOrderJpaEntity userOrderJpaEntity);

    public UserOrderJpaEntity update(UserOrderJpaEntity userOrderJpaEntity);

    public void deleteById(Long id);

    public long count();

    public List<UserOrderJpaEntity> search(String text, String status, String date, int page, int size);

    public long countSearch(String text, String status, String date);
}
