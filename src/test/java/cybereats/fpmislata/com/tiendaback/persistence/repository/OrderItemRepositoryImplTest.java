package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.OrderItemJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.OrderItemJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderItemRepositoryImplTest {

    @Mock
    private OrderItemJpaDao orderItemJpaDao;

    @InjectMocks
    private OrderItemRepositoryImpl orderItemRepository;

    private OrderItemDto orderItemDto;
    private OrderItemJpaEntity orderItemJpaEntity;
    private ProductDto productDto;
    private ProductJpaEntity productJpaEntity;

    @BeforeEach
    void setUp() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        productDto = new ProductDto(1L, "Product", "product", "Desc", new BigDecimal("10.0"), categoryDto);
        orderItemDto = new OrderItemDto(1L, productDto, 2);

        CategoryProductJpaEntity categoryJpaEntity = new CategoryProductJpaEntity(1L, "Category", "category");
        productJpaEntity = new ProductJpaEntity(1L, "Product", "product", "Desc", new BigDecimal("10.0"),
                categoryJpaEntity);
        orderItemJpaEntity = new OrderItemJpaEntity(1L, 2, productJpaEntity, null);
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de OrderItemDto")
        void shouldReturnPageOfOrderItemDto() {
            when(orderItemJpaDao.findAll(1, 10)).thenReturn(List.of(orderItemJpaEntity));
            when(orderItemJpaDao.count()).thenReturn(1L);

            Page<OrderItemDto> result = orderItemRepository.findAll(1, 10);

            assertAll("Verificación de página de items",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1, result.totalElements()),
                    () -> assertEquals(orderItemDto.id(), result.data().get(0).id()),
                    () -> assertNotNull(result.data().get(0).product()),
                    () -> assertEquals(productDto.id(), result.data().get(0).product().id()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {
        @Test
        @DisplayName("Debería devolver un Optional con OrderItemDto cuando existe")
        void shouldReturnOrderItemDtoWhenExists() {
            when(orderItemJpaDao.findById(1L)).thenReturn(Optional.of(orderItemJpaEntity));

            Optional<OrderItemDto> result = orderItemRepository.findById(1L);

            assertTrue(result.isPresent());
            assertAll("Verificación de datos del item",
                    () -> assertEquals(orderItemDto.id(), result.get().id()),
                    () -> assertNotNull(result.get().product()),
                    () -> assertEquals(productDto.id(), result.get().product().id()));
        }

        @Test
        @DisplayName("Debería devolver un Optional vacío cuando no existe")
        void shouldReturnEmptyWhenNotExists() {
            when(orderItemJpaDao.findById(1L)).thenReturn(Optional.empty());

            Optional<OrderItemDto> result = orderItemRepository.findById(1L);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método save")
    class SaveTests {
        @Test
        @DisplayName("Debería insertar un nuevo item cuando el ID es nulo")
        void shouldInsertWhenIdIsNull() {
            OrderItemDto inputDto = new OrderItemDto(null, productDto, 2);
            when(orderItemJpaDao.insert(any(OrderItemJpaEntity.class))).thenReturn(orderItemJpaEntity);

            OrderItemDto result = orderItemRepository.save(inputDto);

            assertNotNull(result);
            assertEquals(orderItemDto.id(), result.id());
            verify(orderItemJpaDao, times(1)).insert(any(OrderItemJpaEntity.class));
        }

        @Test
        @DisplayName("Debería actualizar un item cuando el ID no es nulo")
        void shouldUpdateWhenIdIsNotNull() {
            when(orderItemJpaDao.update(any(OrderItemJpaEntity.class))).thenReturn(orderItemJpaEntity);

            OrderItemDto result = orderItemRepository.save(orderItemDto);

            assertAll("Verificación de actualización",
                    () -> assertNotNull(result),
                    () -> assertEquals(orderItemDto.id(), result.id()));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {
        @Test
        @DisplayName("Debería llamar al DAO para eliminar")
        void shouldCallDaoToDelete() {
            doNothing().when(orderItemJpaDao).deleteById(1L);

            orderItemRepository.deleteById(1L);

            verify(orderItemJpaDao, times(1)).deleteById(1L);
        }
    }
}
