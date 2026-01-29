package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.UserOrderJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserOrderJpaEntity;
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
class UserOrderRepositoryImplTest {

    @Mock
    private UserOrderJpaDao userOrderJpaDao;

    @InjectMocks
    private UserOrderRepositoryImpl userOrderRepository;

    private UserOrderJpaEntity userOrderJpaEntity;
    private UserOrderDto userOrderDto;

    @BeforeEach
    void setUp() {
        UserJpaEntity userEntity = new UserJpaEntity();
        userEntity.setId(1L);
        userOrderJpaEntity = new UserOrderJpaEntity(1L, userEntity, List.of(), OrderStatus.CONFIRMED,
                DeliveryType.PICKUP, LocalDateTime.parse("2025-01-15T10:00:00"));

        UserDto userDto = new UserDto(1L, "Name", "Surname", "Email", "BornDate", "Username", "Password",
                UserRole.CLIENT);
        userOrderDto = new UserOrderDto(1L, userDto, List.of(), OrderStatus.CONFIRMED,
                DeliveryType.PICKUP, LocalDateTime.parse("2025-01-15T10:00:00"));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver una página de pedidos")
        void shouldReturnPageOfOrders() {
            when(userOrderJpaDao.findAll(1, 10)).thenReturn(List.of(userOrderJpaEntity));
            when(userOrderJpaDao.count()).thenReturn(1L);

            Page<UserOrderDto> result = userOrderRepository.findAll(1, 10);

            assertAll("Verificación de página",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1L, result.totalElements()),
                    () -> assertEquals(OrderStatus.CONFIRMED, result.data().get(0).status()));
        }

        @Test
        @DisplayName("Debería devolver todos los pedidos (sin paginación)")
        void shouldReturnAllOrders() {
            when(userOrderJpaDao.findAll()).thenReturn(List.of(userOrderJpaEntity));

            List<UserOrderDto> result = userOrderRepository.findAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(userOrderJpaDao, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver un pedido por ID")
        void shouldReturnOrderById() {
            when(userOrderJpaDao.findById(1L)).thenReturn(Optional.of(userOrderJpaEntity));

            Optional<UserOrderDto> result = userOrderRepository.findById(1L);

            assertTrue(result.isPresent());
            assertEquals(OrderStatus.CONFIRMED, result.get().status());
        }
    }

    @Nested
    @DisplayName("Tests para el método save")
    class SaveTests {

        @Test
        @DisplayName("Debería insertar un nuevo pedido")
        void shouldInsertOrder() {
            UserOrderDto newOrderDto = new UserOrderDto(null, userOrderDto.user(), List.of(), OrderStatus.CONFIRMED,
                    DeliveryType.PICKUP, LocalDateTime.parse("2025-01-15T10:00:00"));
            when(userOrderJpaDao.insert(any(UserOrderJpaEntity.class))).thenReturn(userOrderJpaEntity);

            UserOrderDto result = userOrderRepository.save(newOrderDto);

            assertNotNull(result);
            verify(userOrderJpaDao, times(1)).insert(any(UserOrderJpaEntity.class));
        }

        @Test
        @DisplayName("Debería actualizar un pedido existente")
        void shouldUpdateOrder() {
            when(userOrderJpaDao.update(any(UserOrderJpaEntity.class))).thenReturn(userOrderJpaEntity);

            UserOrderDto result = userOrderRepository.save(userOrderDto);

            assertNotNull(result);
            verify(userOrderJpaDao, times(1)).update(any(UserOrderJpaEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {

        @Test
        @DisplayName("Debería eliminar un pedido")
        void shouldDeleteOrder() {
            userOrderRepository.deleteById(1L);
            verify(userOrderJpaDao, times(1)).deleteById(1L);
        }
    }
}
