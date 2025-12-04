package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;

public interface UserOrderJpaDao {
    public Optional<UserOrderJpaEntity> getUserOrderById(Long id);

    public List<UserOrderJpaEntity> getAll();

    public UserOrderJpaEntity insert(UserOrderJpaEntity userOrderJpaEntity);

    public UserOrderJpaEntity update(UserOrderJpaEntity userOrderJpaEntity);

    public void deleteById(Long id);
}
