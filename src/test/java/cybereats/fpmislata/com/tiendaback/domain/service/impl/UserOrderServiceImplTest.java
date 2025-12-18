package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserOrderRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserOrderServiceImplTest {

    @Mock
    private UserOrderRepository userOrderRepository;

    @InjectMocks
    private UserOrderServiceImpl userOrderService;

    private UserOrderDto userOrderDto;

    @BeforeEach
    void setUp() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "Email", "BornDate", "Username", "Password",
                UserRole.CLIENT);
        userOrderDto = new UserOrderDto(1L, userDto, List.of(), OrderStatus.CONFIRMED,
                LocalDateTime.parse("2025-01-15T10:00:00"));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver una página de pedidos")
        void shouldReturnPageOfOrders() {
            when(userOrderRepository.findAll(1, 10)).thenReturn(new Page<>(List.of(userOrderDto), 1, 10, 1L));

            Page<UserOrderDto> result = userOrderService.findAll(1, 10);

            assertNotNull(result);
            assertEquals(1, result.data().size());
            verify(userOrderRepository, times(1)).findAll(1, 10);
        }
    }

    @Nested
    @DisplayName("Tests para el método getById")
    class GetByIdTests {

        @Test
        @DisplayName("Debería devolver un pedido por ID")
        void shouldReturnOrderById() {
            when(userOrderRepository.findById(1L)).thenReturn(Optional.of(userOrderDto));

            UserOrderDto result = userOrderService.getById(1L);

            assertNotNull(result);
            assertEquals(OrderStatus.CONFIRMED, result.status());
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando el ID no existe")
        void shouldThrowExceptionWhenIdDoesNotExist() {
            when(userOrderRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> userOrderService.getById(1L));
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {

        @Test
        @DisplayName("Debería insertar un nuevo pedido")
        void shouldInsertOrder() {
            when(userOrderRepository.findById(userOrderDto.id())).thenReturn(Optional.empty());
            when(userOrderRepository.save(any(UserOrderDto.class))).thenReturn(userOrderDto);

            UserOrderDto result = userOrderService.insert(userOrderDto);

            assertNotNull(result);
            verify(userOrderRepository, times(1)).save(any(UserOrderDto.class));
        }

        @Test
        @DisplayName("Debería lanzar BusinessException si el pedido ya existe")
        void shouldThrowExceptionIfOrderAlreadyExists() {
            when(userOrderRepository.findById(userOrderDto.id())).thenReturn(Optional.of(userOrderDto));

            assertThrows(BusinessException.class, () -> userOrderService.insert(userOrderDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {

        @Test
        @DisplayName("Debería actualizar un pedido existente")
        void shouldUpdateOrder() {
            when(userOrderRepository.findById(userOrderDto.id())).thenReturn(Optional.of(userOrderDto));
            when(userOrderRepository.save(any(UserOrderDto.class))).thenReturn(userOrderDto);

            UserOrderDto result = userOrderService.update(userOrderDto);

            assertNotNull(result);
            verify(userOrderRepository, times(1)).save(any(UserOrderDto.class));
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException si el pedido no existe")
        void shouldThrowExceptionIfOrderDoesNotExist() {
            when(userOrderRepository.findById(userOrderDto.id())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> userOrderService.update(userOrderDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {

        @Test
        @DisplayName("Debería eliminar un pedido")
        void shouldDeleteOrder() {
            when(userOrderRepository.findById(1L)).thenReturn(Optional.of(userOrderDto));

            userOrderService.deleteById(1L);

            verify(userOrderRepository, times(1)).deleteById(1L);
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException si el pedido no existe")
        void shouldThrowExceptionIfOrderDoesNotExist() {
            when(userOrderRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> userOrderService.deleteById(1L));
        }
    }
}
