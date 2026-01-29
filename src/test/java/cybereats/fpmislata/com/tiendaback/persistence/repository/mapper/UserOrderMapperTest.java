package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserOrderMapperTest {

        private final UserOrderMapper mapper = UserOrderMapper.getInstance();

        @Test
        @DisplayName("Debería mapear de UserOrderJpaEntity a UserOrderDto")
        void shouldMapEntityToDto() {
                UserJpaEntity userEntity = new UserJpaEntity(1L, "Name", "Surname", "email@test.com", "1990-01-01",
                                "user",
                                "pass", UserRole.CLIENT);
                CategoryProductJpaEntity categoryEntity = new CategoryProductJpaEntity(1L, "Category", "category");
                ProductJpaEntity productEntity = new ProductJpaEntity(1L, "Product 1", "product-1", "Description",
                                new BigDecimal("10.00"), "image.png", categoryEntity);
                OrderItemJpaEntity orderItemEntity = new OrderItemJpaEntity(1L, 2, productEntity, null);
                UserOrderJpaEntity entity = new UserOrderJpaEntity(1L, userEntity, List.of(orderItemEntity),
                                OrderStatus.CONFIRMED, DeliveryType.PICKUP, LocalDateTime.now());

                UserOrderDto dto = mapper.fromUserOrderJpaEntityToUserOrderDto(entity);

                assertNotNull(dto);
                assertEquals(entity.getId(), dto.id());
                assertEquals(entity.getUser().getId(), dto.user().id());
                assertEquals(entity.getOrderItems().size(), dto.orderItems().size());
                assertEquals(entity.getStatus(), dto.status());
                assertEquals(entity.getCreatedAt(), dto.createdAt());
        }

        @Test
        @DisplayName("Debería mapear de UserOrderDto a UserOrderJpaEntity")
        void shouldMapDtoToEntity() {
                UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                                UserRole.CLIENT);
                CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
                ProductDto productDto = new ProductDto(1L, "Product 1", "product-1", "Description",
                                new BigDecimal("10.00"), "image.png",
                                categoryDto);
                OrderItemDto orderItemDto = new OrderItemDto(1L, productDto, 2);
                UserOrderDto dto = new UserOrderDto(1L, userDto, List.of(orderItemDto), OrderStatus.CONFIRMED,
                                DeliveryType.PICKUP, LocalDateTime.now());

                UserOrderJpaEntity entity = mapper.fromUserOrderDtoToUserOrderJpaEntity(dto);

                assertNotNull(entity);
                assertEquals(dto.id(), entity.getId());
                assertEquals(dto.user().id(), entity.getUser().getId());
                assertEquals(dto.orderItems().size(), entity.getOrderItems().size());
                assertEquals(dto.status(), entity.getStatus());
                assertEquals(dto.createdAt(), entity.getCreatedAt());
        }

        @Test
        @DisplayName("Debería devolver null si el input es null")
        void shouldReturnNullIfInputIsNull() {
                assertNull(mapper.fromUserOrderJpaEntityToUserOrderDto(null));
                assertNull(mapper.fromUserOrderDtoToUserOrderJpaEntity(null));
        }
}
