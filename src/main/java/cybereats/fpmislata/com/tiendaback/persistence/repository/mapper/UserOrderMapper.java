package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;

public class UserOrderMapper {
    private static UserOrderMapper INSTANCE;

    private UserOrderMapper() {
    }

    public static UserOrderMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserOrderMapper();
        }
        return INSTANCE;
    }

    public UserOrderJpaEntity userOrderDtoToUserOrderJpaEntity(UserOrderDto userOrderDto) {
        if (userOrderDto == null) {
            return null;
        }

        return new UserOrderJpaEntity(
                userOrderDto.id(),
                UserMapper.getInstance().userDtoToUserJpaEntity(userOrderDto.user()),
                userOrderDto.orderItems().stream()
                        .map(item -> OrderItemMapper.getInstance().orderItemDtoToOrderItemJpaEntity(item))
                        .toList(),
                userOrderDto.status());
    }

    public UserOrderDto userOrderJpaEntityToUserOrderDto(UserOrderJpaEntity userOrderJpaEntity) {
        if (userOrderJpaEntity == null) {
            return null;
        }

        return new UserOrderDto(
                userOrderJpaEntity.getId(),
                UserMapper.getInstance().userJpaEntityToUserDto(userOrderJpaEntity.getUser()),
                userOrderJpaEntity.getOrderItems().stream()
                        .map(item -> OrderItemMapper.getInstance().orderItemJpaEntityToOrderItemDto(item))
                        .toList(),
                null,
                userOrderJpaEntity.getStatus());
    }
}
