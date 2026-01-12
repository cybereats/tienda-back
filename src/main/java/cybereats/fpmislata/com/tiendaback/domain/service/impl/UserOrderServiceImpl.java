package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserOrderRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.UserOrderService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.domain.mapper.UserOrderMapper;
import cybereats.fpmislata.com.tiendaback.domain.mapper.UserMapper;
import cybereats.fpmislata.com.tiendaback.domain.mapper.OrderItemMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.UserOrder;
import jakarta.transaction.Transactional;

public class UserOrderServiceImpl implements UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @Override
    public Page<UserOrderDto> findAll(int page, int size) {
        Page<UserOrderDto> userOrderDtoPage = userOrderRepository.findAll(page, size);
        List<UserOrderDto> content = userOrderDtoPage.data().stream()
                .map(dto -> UserOrderMapper.getInstance().fromUserOrderDtoToUserOrder(dto))
                .map(order -> UserOrderMapper.getInstance().fromUserOrderToUserOrderDto(order))
                .toList();
        return new Page<>(content, page, size, userOrderDtoPage.totalElements());
    }

    @Override
    public Page<UserOrderDto> search(String text, String status, String date, int page, int size) {
        return userOrderRepository.search(text, status, date, page, size);
    }

    @Override
    public UserOrderDto getById(Long id) {
        UserOrderDto userOrderDto = userOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserOrder not found"));
        UserOrder userOrder = UserOrderMapper.getInstance().fromUserOrderDtoToUserOrder(userOrderDto);
        return UserOrderMapper.getInstance().fromUserOrderToUserOrderDto(userOrder);
    }

    @Override
    public Optional<UserOrderDto> findById(Long id) {
        return userOrderRepository.findById(id);
    }

    @Override
    @Transactional
    public UserOrderDto insert(UserOrderDto userOrderDto) {
        Optional<UserOrderDto> userOrderDtoOptional = userOrderRepository.findById(userOrderDto.id());
        if (userOrderDtoOptional.isPresent()) {
            throw new BusinessException("UserOrder already exists");
        }
        UserOrder userOrder = UserOrderMapper.getInstance().fromUserOrderDtoToUserOrder(userOrderDto);
        return userOrderRepository.save(UserOrderMapper.getInstance().fromUserOrderToUserOrderDto(userOrder));
    }

    @Override
    @Transactional
    public UserOrderDto update(UserOrderDto userOrderDto) {
        UserOrderDto existingUserOrderDto = userOrderRepository.findById(userOrderDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("UserOrder not found"));

        UserOrder existingUserOrder = UserOrderMapper.getInstance().fromUserOrderDtoToUserOrder(existingUserOrderDto);

        UserOrder.Builder builder = new UserOrder.Builder()
                .id(existingUserOrder.getId())
                .user(userOrderDto.user() != null
                        ? UserMapper.getInstance().fromUserDtoToUser(userOrderDto.user())
                        : existingUserOrder.getUser())
                .orderItems(userOrderDto.orderItems() != null
                        ? userOrderDto.orderItems().stream()
                                .map(itemDto -> OrderItemMapper.getInstance().fromOrderItemDtoToOrderItem(itemDto))
                                .toList()
                        : existingUserOrder.getOrderItems())
                .status(userOrderDto.status() != null ? userOrderDto.status() : existingUserOrder.getStatus())
                .createdAt(existingUserOrder.getCreatedAt());

        UserOrder updatedUserOrder = builder.build();
        return userOrderRepository.save(UserOrderMapper.getInstance().fromUserOrderToUserOrderDto(updatedUserOrder));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<UserOrderDto> userOrderDtoOptional = userOrderRepository.findById(id);
        if (userOrderDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("UserOrder not found");
        }
        userOrderRepository.deleteById(id);
    }
}
