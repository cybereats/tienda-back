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
import jakarta.transaction.Transactional;

public class UserOrderServiceImpl implements UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @Override
    public List<UserOrderDto> findAll() {
        List<UserOrder> userOrderList = userOrderRepository.findAll().stream()
                .map(dto -> UserOrderMapper.getInstance().fromUserOrderDtoToUserOrder(dto))
                .toList();
        return userOrderList.stream()
                .map(order -> UserOrderMapper.getInstance().fromUserOrderToUserOrderDto(order))
                .toList();
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
        Optional<UserOrderDto> userOrderDtoOptional = userOrderRepository.findById(userOrderDto.id());
        if (userOrderDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("UserOrder not found");
        }
        UserOrder userOrder = UserOrderMapper.getInstance().fromUserOrderDtoToUserOrder(userOrderDto);
        return userOrderRepository.save(UserOrderMapper.getInstance().fromUserOrderToUserOrderDto(userOrder));
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
