package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.service.StatsService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ChartDataDto;
import cybereats.fpmislata.com.tiendaback.security.AuthInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatsController.class)
public class StatsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StatsService statsService;

  @MockitoBean
  private AuthInterceptor authInterceptor;

  @BeforeEach
  void setUp() throws Exception {
    when(authInterceptor.preHandle(any(), any(), any())).thenReturn(true);
  }

  @Nested
  @DisplayName("Tests para los KPIs diarios")
  class KpiTests {

    @Test
    @DisplayName("Debería devolver los ingresos diarios")
    void shouldReturnDailyIncome() throws Exception {
      when(statsService.getDailyIncome()).thenReturn(Map.of("total", new BigDecimal("100.50")));

      mockMvc.perform(get("/api/stats/daily-income"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.total").value(100.50));
    }

    @Test
    @DisplayName("Debería devolver las reservas diarias")
    void shouldReturnDailyBookings() throws Exception {
      when(statsService.getDailyBookings()).thenReturn(Map.of("count", 10L));

      mockMvc.perform(get("/api/stats/daily-bookings"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.count").value(10));
    }

    @Test
    @DisplayName("Debería devolver las reservas activas")
    void shouldReturnActiveBookings() throws Exception {
      when(statsService.getActiveBookings()).thenReturn(Map.of("total", 50L, "occupied", 20L));

      mockMvc.perform(get("/api/stats/active-bookings"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.total").value(50))
          .andExpect(jsonPath("$.occupied").value(20));
    }
  }

  @Nested
  @DisplayName("Tests para los gráficos y resúmenes")
  class ChartTests {

    @Test
    @DisplayName("Debería devolver el resumen diario por horas")
    void shouldReturnDailySummary() throws Exception {
      List<Map<String, Object>> summary = List.of(Map.of("hour", 10, "income", 50.0));
      when(statsService.getDailySummary()).thenReturn(summary);

      mockMvc.perform(get("/api/stats/daily-summary"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].hour").value(10))
          .andExpect(jsonPath("$[0].income").value(50.0));
    }

    @Test
    @DisplayName("Debería devolver los ingresos mensuales")
    void shouldReturnMonthlyIncome() throws Exception {
      ChartDataDto dto = new ChartDataDto(
          List.of(new ChartDataDto.SeriesData("Ventas", List.of(1000))),
          List.of("Jan"),
          List.of("#000"));
      when(statsService.getMonthlyIncome()).thenReturn(dto);

      mockMvc.perform(get("/api/stats/monthly-income"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.series[0].name").value("Ventas"))
          .andExpect(jsonPath("$.series[0].data[0]").value(1000));
    }

    @Test
    @DisplayName("Debería devolver la comparativa de ingresos vs costes")
    void shouldReturnIncomeVsCosts() throws Exception {
      ChartDataDto dto = new ChartDataDto(
          List.of(new ChartDataDto.SeriesData("Ingresos", List.of(1000))),
          List.of("Jan"),
          List.of("#000"));
      when(statsService.getIncomeVsCosts()).thenReturn(dto);

      mockMvc.perform(get("/api/stats/income-vs-costs"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.series[0].name").value("Ingresos"));
    }
  }
}
