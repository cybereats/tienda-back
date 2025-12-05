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
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.findById(orderItemDto.id());
        if (orderItemDtoOptional.isPresent()) {
            throw new BusinessException("OrderItemDto already exists");
        }
        OrderItem orderItem = OrderItemMapper.getInstance().fromOrderItemDtoToOrderItem(orderItemDto);
        return orderItemRepository.save(OrderItemMapper.getInstance().fromOrderItemToOrderItemDto(orderItem));
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItemDto) {
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.findById(orderItemDto.id());
        if (orderItemDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("OrderItemDto not found");
        }
        OrderItem orderItem = OrderItemMapper.getInstance().fromOrderItemDtoToOrderItem(orderItemDto);
        return orderItemRepository.save(OrderItemMapper.getInstance().fromOrderItemToOrderItemDto(orderItem));
    }

    @Override
    public OrderItemDto getById(Long id) {
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.findById(id);
        if (orderItemDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("OrderItemDto not found");
        }
        OrderItem orderItem = OrderItemMapper.getInstance().fromOrderItemDtoToOrderItem(orderItemDtoOptional.get());
        return OrderItemMapper.getInstance().fromOrderItemToOrderItemDto(orderItem);
    }

    @Override
    public Optional<OrderItemDto> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public List<OrderItemDto> findAll() {
        List<OrderItem> orderItemList = orderItemRepository.findAll().stream()
                .map(dto -> OrderItemMapper.getInstance().fromOrderItemDtoToOrderItem(dto))
                .toList();
        return orderItemList.stream()
                .map(item -> OrderItemMapper.getInstance().fromOrderItemToOrderItemDto(item))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Optional<OrderItemDto> orderItemDtoOptional = orderItemRepository.findById(id);
        if (orderItemDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("OrderItemDto not found");
        }
        orderItemRepository.deleteById(id);
    }
}
