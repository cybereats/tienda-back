package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.OrderItemRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
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
class OrderItemServiceImplTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    private OrderItemDto orderItemDto;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        productDto = new ProductDto(1L, "Product", "product", "Desc", new BigDecimal("10.0"), categoryDto);
        orderItemDto = new OrderItemDto(1L, productDto, 2);
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {
        @Test
        @DisplayName("Debería insertar un nuevo item cuando no existe")
        void shouldInsertWhenNotExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());
            when(orderItemRepository.save(any(OrderItemDto.class))).thenReturn(orderItemDto);

            OrderItemDto result = orderItemService.insert(orderItemDto);

            assertAll("Verificación de inserción",
                    () -> assertNotNull(result),
                    () -> assertEquals(orderItemDto.id(), result.id()),
                    () -> assertNotNull(result.product()),
                    () -> assertEquals(productDto.id(), result.product().id()));
        }

        @Test
        @DisplayName("Debería lanzar BusinessException cuando ya existe")
        void shouldThrowExceptionWhenAlreadyExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItemDto));

            assertThrows(BusinessException.class, () -> orderItemService.insert(orderItemDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {
        @Test
        @DisplayName("Debería actualizar cuando existe")
        void shouldUpdateWhenExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItemDto));
            when(orderItemRepository.save(any(OrderItemDto.class))).thenReturn(orderItemDto);

            OrderItemDto result = orderItemService.update(orderItemDto);

            assertAll("Verificación de actualización",
                    () -> assertNotNull(result),
                    () -> assertEquals(orderItemDto.id(), result.id()));
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> orderItemService.update(orderItemDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método getById")
    class GetByIdTests {
        @Test
        @DisplayName("Debería devolver el item cuando existe")
        void shouldReturnItemWhenExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItemDto));

            OrderItemDto result = orderItemService.getById(1L);

            assertAll("Verificación de obtención de item",
                    () -> assertNotNull(result),
                    () -> assertEquals(1L, result.id()),
                    () -> assertNotNull(result.product()),
                    () -> assertEquals(productDto.id(), result.product().id()));
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> orderItemService.getById(1L));
        }
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de OrderItemDto")
        void shouldReturnPageOfOrderItemDto() {
            Page<OrderItemDto> repoPage = new Page<>(List.of(orderItemDto), 1, 10, 1L);
            when(orderItemRepository.findAll(1, 10)).thenReturn(repoPage);

            Page<OrderItemDto> result = orderItemService.findAll(1, 10);

            assertNotNull(result);
            assertEquals(1, result.data().size());
            assertEquals(1L, result.totalElements());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {
        @Test
        @DisplayName("Debería eliminar cuando existe")
        void shouldDeleteWhenExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItemDto));
            doNothing().when(orderItemRepository).deleteById(1L);

            orderItemService.deleteById(1L);

            verify(orderItemRepository, times(1)).deleteById(1L);
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> orderItemService.deleteById(1L));
        }
    }
}
