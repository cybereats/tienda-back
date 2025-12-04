package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.mapper.OrderItemMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderItem;
import cybereats.fpmislata.com.tiendaback.domain.repository.OrderItemRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.OrderItemService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItemDto insert(OrderItemDto orderItemDto) {
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.getOrderItemById(orderItemDto.id());
        if (orderItemDtoOptional.isPresent()) {
            throw new BusinessException("OrderItemDto already exists");
        }
        OrderItem orderItem = OrderItemMapper.getInstance().orderItemDtoToOrderItem(orderItemDto);
        return orderItemRepository.save(OrderItemMapper.getInstance().orderItemToOrderItemDto(orderItem));
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItemDto) {
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.getOrderItemById(orderItemDto.id());
        if (orderItemDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("OrderItemDto not found");
        }
        OrderItem orderItem = OrderItemMapper.getInstance().orderItemDtoToOrderItem(orderItemDto);
        return orderItemRepository.save(OrderItemMapper.getInstance().orderItemToOrderItemDto(orderItem));
    }

    @Override
    public Optional<OrderItemDto> getOrderItemById(Long id) {
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.getOrderItemById(id);
        if (orderItemDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("OrderItemDto not found");
        }
        OrderItem orderItem = OrderItemMapper.getInstance().orderItemDtoToOrderItem(orderItemDtoOptional.get());
        return Optional.of(OrderItemMapper.getInstance().orderItemToOrderItemDto(orderItem));
    }

    @Override
    public List<OrderItemDto> getAll() {
        List<OrderItem> orderItemList = orderItemRepository.getAll().stream()
                .map(dto -> OrderItemMapper.getInstance().orderItemDtoToOrderItem(dto))
                .toList();
        return orderItemList.stream()
                .map(item -> OrderItemMapper.getInstance().orderItemToOrderItemDto(item))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.getOrderItemById(id);
        if (orderItemDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("OrderItemDto not found");
        }
        orderItemRepository.deleteById(id);
    }
}
