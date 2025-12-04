package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.repository.UserOrderRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.UserOrderService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.domain.mapper.UserOrderMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.UserOrder;

public class UserOrderServiceImpl implements UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @Override
    public List<UserOrderDto> getAll() {
        List<UserOrder> userOrderList = userOrderRepository.getAll().stream()
                .map(dto -> UserOrderMapper.getInstance().userOrderDtoToUserOrder(dto))
                .toList();
        return userOrderList.stream()
                .map(order -> UserOrderMapper.getInstance().userOrderToUserOrderDto(order))
                .toList();
    }

    @Override
    public Optional<UserOrderDto> getUserOrderById(Long id) {
        Optional<UserOrder> userOrder = userOrderRepository.getUserOrderById(id)
                .map(dto -> UserOrderMapper.getInstance().userOrderDtoToUserOrder(dto));
        if (userOrder.isEmpty()) {
            throw new ResourceNotFoundException("UserOrder not found");
        }
        return userOrder.map(order -> UserOrderMapper.getInstance().userOrderToUserOrderDto(order));
    }

    @Override
    public UserOrderDto insert(UserOrderDto userOrderDto) {
        Optional<UserOrderDto> userOrderDtoOptional = userOrderRepository.getUserOrderById(userOrderDto.id());
        if (userOrderDtoOptional.isPresent()) {
            throw new BusinessException("UserOrder already exists");
        }
        UserOrder userOrder = UserOrderMapper.getInstance().userOrderDtoToUserOrder(userOrderDto);
        return userOrderRepository.save(UserOrderMapper.getInstance().userOrderToUserOrderDto(userOrder));
    }

    @Override
    public UserOrderDto update(UserOrderDto userOrderDto) {
        Optional<UserOrderDto> userOrderDtoOptional = userOrderRepository.getUserOrderById(userOrderDto.id());
        if (userOrderDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("UserOrder not found");
        }
        UserOrder userOrder = UserOrderMapper.getInstance().userOrderDtoToUserOrder(userOrderDto);
        return userOrderRepository.save(UserOrderMapper.getInstance().userOrderToUserOrderDto(userOrder));
    }

    @Override
    public void deleteById(Long id) {
        Optional<UserOrderDto> userOrderDtoOptional = userOrderRepository.getUserOrderById(id);
        if (userOrderDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("UserOrder not found");
        }
        userOrderRepository.deleteById(id);
    }
}
