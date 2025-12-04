package cybereats.fpmislata.com.tiendaback.persistence.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.repository.UserOrderRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserOrderJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.UserOrderMapper;

public class UserOrderRepositoryImpl implements UserOrderRepository {

    private final UserOrderJpaDao userOrderJpaDao;

    public UserOrderRepositoryImpl(UserOrderJpaDao userOrderJpaDao) {
        this.userOrderJpaDao = userOrderJpaDao;
    }

    @Override
    public Optional<UserOrderDto> getUserOrderById(Long id) {
        Optional<UserOrderJpaEntity> userOrderJpaEntity = userOrderJpaDao.getUserOrderById(id);
        return Optional.ofNullable(UserOrderMapper.toUserOrderDto(userOrderJpaEntity.get()));
    }

    @Override
    public List<UserOrderDto> getAll() {
        return userOrderJpaDao.getAll().stream().map(UserOrderMapper::toUserOrderDto).toList();
    }

    @Override
    public UserOrderDto save(UserOrderDto userOrderDto) {
        if (userOrderDto.id() == null) {
            UserOrderJpaEntity userOrderJpaEntity = UserOrderMapper.toUserOrderJpaEntity(userOrderDto);
            return UserOrderMapper.toUserOrderDto(userOrderJpaDao.insert(userOrderJpaEntity));
        } else {
            UserOrderJpaEntity userOrderJpaEntity = UserOrderMapper.toUserOrderJpaEntity(userOrderDto);
            return UserOrderMapper.toUserOrderDto(userOrderJpaDao.update(userOrderJpaEntity));
        }
    }

    @Override
    public void deleteById(Long id) {
        userOrderJpaDao.deleteById(id);
    }

}