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
    public Optional<UserOrderDto> findById(Long id) {
        Optional<UserOrderJpaEntity> userOrderJpaEntity = userOrderJpaDao.findById(id);
        return userOrderJpaEntity.map(entity -> UserOrderMapper.getInstance().fromUserOrderJpaEntityToUserOrderDto(entity));
    }

    @Override
    public List<UserOrderDto> findAll() {
        return userOrderJpaDao.findAll().stream()
                .map(entity -> UserOrderMapper.getInstance().fromUserOrderJpaEntityToUserOrderDto(entity))
                .toList();
    }

    @Override
    public UserOrderDto save(UserOrderDto userOrderDto) {
        if (userOrderDto.id() == null) {
            UserOrderJpaEntity userOrderJpaEntity = UserOrderMapper.getInstance()
                    .fromUserOrderDtoToUserOrderJpaEntity(userOrderDto);
            return UserOrderMapper.getInstance()
                    .fromUserOrderJpaEntityToUserOrderDto(userOrderJpaDao.insert(userOrderJpaEntity));
        } else {
            UserOrderJpaEntity userOrderJpaEntity = UserOrderMapper.getInstance()
                    .fromUserOrderDtoToUserOrderJpaEntity(userOrderDto);
            return UserOrderMapper.getInstance()
                    .fromUserOrderJpaEntityToUserOrderDto(userOrderJpaDao.update(userOrderJpaEntity));
        }
    }

    @Override
    public void deleteById(Long id) {
        userOrderJpaDao.deleteById(id);
    }

}