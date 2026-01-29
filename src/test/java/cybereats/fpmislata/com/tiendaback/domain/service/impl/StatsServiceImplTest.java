package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.DeliveryType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.*;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsServiceImplTest {

  @Mock
  private UserOrderRepository userOrderRepository;
  @Mock
  private BookingRepository bookingRepository;
  @Mock
  private PCRepository pcRepository;
  @Mock
  private UserRepository userRepository;
  @Mock
  private ReportRepository reportRepository;

  @InjectMocks
  private StatsServiceImpl statsService;

  private UserOrderDto mockOrder;
  private BookingDto mockBooking;

  @BeforeEach
  void setUp() {
    CategoryProductDto category = new CategoryProductDto(1L, "Cat", "cat");
    ProductDto product = new ProductDto(1L, "Test", "test", "desc", new BigDecimal("10.0"), "image.png", category);
    OrderItemDto item = new OrderItemDto(1L, product, 2);
    UserDto user = new UserDto(1L, "Name", "Surname", "email", "1990-01-01", "user", "pass", null);
    mockOrder = new UserOrderDto(1L, user, List.of(item), OrderStatus.CONFIRMED, DeliveryType.PICKUP,
        LocalDateTime.now());
    mockBooking = new BookingDto(1L, 2, user, null, LocalDateTime.now());
  }

  @Test
  @DisplayName("Debería calcular los ingresos diarios correctamente")
  void shouldCalculateDailyIncome() {
    when(userOrderRepository.findAll()).thenReturn(List.of(mockOrder));

    Map<String, BigDecimal> result = statsService.getDailyIncome();

    assertEquals(0, new BigDecimal("20.0").compareTo(result.get("total")));
  }

  @Test
  @DisplayName("Debería contar las reservas diarias correctamente")
  void shouldCountDailyBookings() {
    when(bookingRepository.findAll()).thenReturn(List.of(mockBooking));

    Map<String, Long> result = statsService.getDailyBookings();

    assertEquals(1L, result.get("count"));
  }

  @Test
  @DisplayName("Debería obtener las reservas activas correctamente")
  void shouldGetActiveBookings() {
    when(pcRepository.count()).thenReturn(10L);
    when(bookingRepository.findAll()).thenReturn(List.of(mockBooking));

    Map<String, Long> result = statsService.getActiveBookings();

    assertEquals(10L, result.get("total"));
    assertEquals(1L, result.get("occupied"));
  }

  @Test
  @DisplayName("Debería generar el resumen diario correctamente")
  void shouldGenerateDailySummary() {
    when(userOrderRepository.findAll()).thenReturn(List.of(mockOrder));

    List<Map<String, Object>> result = statsService.getDailySummary();

    assertNotNull(result);
    assertEquals(24, result.size());
    int hour = mockOrder.createdAt().getHour();
    assertEquals(0, new BigDecimal("20.0").compareTo((BigDecimal) result.get(hour).get("income")));
  }

  @Test
  @DisplayName("Debería obtener ingresos mensuales correctamente")
  void shouldGetMonthlyIncome() {
    when(userOrderRepository.findAll()).thenReturn(List.of(mockOrder));

    ChartDataDto result = statsService.getMonthlyIncome();

    assertNotNull(result);
    assertFalse(result.series().isEmpty());
  }

  @Test
  @DisplayName("Debería obtener usuarios mensuales correctamente")
  void shouldGetMonthlyUsers() {
    UserDto user = new UserDto(1L, "Name", "Surname", "email", "1990-01-01", "user", "pass", null);
    Page<UserDto> userPage = new Page<>(List.of(user), 1, 10, 1L);
    when(userRepository.findAll(1, 1000)).thenReturn(userPage);

    ChartDataDto result = statsService.getMonthlyUsers();

    assertNotNull(result);
  }

  @Test
  @DisplayName("Debería obtener reportes mensuales correctamente")
  void shouldGetMonthlyReports() {
    Page<ReportDto> reportPage = new Page<>(Collections.emptyList(), 1, 10, 0L);
    when(reportRepository.findAll(1, 1000)).thenReturn(reportPage);

    ChartDataDto result = statsService.getMonthlyReports();

    assertNotNull(result);
  }

  @Test
  @DisplayName("Debería obtener ingresos vs costes correctamente")
  void shouldGetIncomeVsCosts() {
    when(userOrderRepository.findAll()).thenReturn(List.of(mockOrder));

    ChartDataDto result = statsService.getIncomeVsCosts();

    assertNotNull(result);
    assertEquals(2, result.series().size());
  }
}
