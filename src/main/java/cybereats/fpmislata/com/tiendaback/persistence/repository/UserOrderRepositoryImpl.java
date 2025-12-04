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
        return userOrderJpaEntity.map(entity -> UserOrderMapper.getInstance().userOrderJpaEntityToUserOrderDto(entity));
    }

    @Override
    public List<UserOrderDto> getAll() {
        return userOrderJpaDao.getAll().stream()
                .map(entity -> UserOrderMapper.getInstance().userOrderJpaEntityToUserOrderDto(entity))
                .toList();
    }

    @Override
    public UserOrderDto save(UserOrderDto userOrderDto) {
        if (userOrderDto.id() == null) {
            UserOrderJpaEntity userOrderJpaEntity = UserOrderMapper.getInstance()
                    .userOrderDtoToUserOrderJpaEntity(userOrderDto);
            return UserOrderMapper.getInstance()
                    .userOrderJpaEntityToUserOrderDto(userOrderJpaDao.insert(userOrderJpaEntity));
        } else {
            UserOrderJpaEntity userOrderJpaEntity = UserOrderMapper.getInstance()
                    .userOrderDtoToUserOrderJpaEntity(userOrderDto);
            return UserOrderMapper.getInstance()
                    .userOrderJpaEntityToUserOrderDto(userOrderJpaDao.update(userOrderJpaEntity));
        }
    }

    @Override
    public void deleteById(Long id) {
        userOrderJpaDao.deleteById(id);
    }

}