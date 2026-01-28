package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.StatsService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ChartDataDto;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.ChartDataMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ChartDataResponse;
import cybereats.fpmislata.com.tiendaback.security.AllowedRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@AllowedRoles(UserRole.ADMIN)
@RequestMapping("/api/stats")
public class StatsController {

     private final StatsService statsService;

     public StatsController(StatsService statsService) {
          this.statsService = statsService;
     }

     @GetMapping("/daily-income")
     public ResponseEntity<Map<String, BigDecimal>> getDailyIncome() {
          return ResponseEntity.ok(statsService.getDailyIncome());

     }

     @GetMapping("/daily-bookings")
     public ResponseEntity<Map<String, Long>> getDailyBookings() {
          return ResponseEntity.ok(statsService.getDailyBookings());

     }

     @GetMapping("/active-bookings")
     public ResponseEntity<Map<String, Long>> getActiveBookings() {
          return ResponseEntity.ok(statsService.getActiveBookings());

     }

     @GetMapping("/daily-summary")
     public ResponseEntity<List<Map<String, Object>>> getDailySummary() {
          return ResponseEntity.ok(statsService.getDailySummary());

     }

     @GetMapping("/monthly-income")
     public ResponseEntity<ChartDataResponse> getMonthlyIncome() {
          ChartDataDto dto = statsService.getMonthlyIncome();
          return ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

     }

     @GetMapping("/monthly-users")
     public ResponseEntity<ChartDataResponse> getMonthlyUsers() {
          ChartDataDto dto = statsService.getMonthlyUsers();
          return ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

     }

     @GetMapping("/monthly-reports")
     public ResponseEntity<ChartDataResponse> getMonthlyReports() {
          ChartDataDto dto = statsService.getMonthlyReports();
          return ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

     }

     @GetMapping("/income-vs-costs")
     public ResponseEntity<ChartDataResponse> getIncomeVsCosts() {
          ChartDataDto dto = statsService.getIncomeVsCosts();
          return ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

     }
}
