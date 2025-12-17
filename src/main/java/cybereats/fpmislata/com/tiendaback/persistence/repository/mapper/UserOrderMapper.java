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

    public UserOrderJpaEntity fromUserOrderDtoToUserOrderJpaEntity(UserOrderDto userOrderDto) {
        if (userOrderDto == null) {
            return null;
        }

        return new UserOrderJpaEntity(
                userOrderDto.id(),
                UserMapper.getInstance().fromUserDtoToUserJpaEntity(userOrderDto.user()),
                userOrderDto.orderItems().stream()
                        .map(item -> OrderItemMapper.getInstance().fromOrderItemDtoToOrderItemJpaEntity(item))
                        .toList(),
                userOrderDto.status(),
                userOrderDto.createdAt());
    }

    public UserOrderDto fromUserOrderJpaEntityToUserOrderDto(UserOrderJpaEntity userOrderJpaEntity) {
        if (userOrderJpaEntity == null) {
            return null;
        }

        return new UserOrderDto(
                userOrderJpaEntity.getId(),
                UserMapper.getInstance().fromUserJpaEntityToUserDto(userOrderJpaEntity.getUser()),
                userOrderJpaEntity.getOrderItems().stream()
                        .map(item -> OrderItemMapper.getInstance().fromOrderItemJpaEntityToOrderItemDto(item))
                        .toList(),
                userOrderJpaEntity.getStatus(),
                userOrderJpaEntity.getCreatedAt());
    }
}
