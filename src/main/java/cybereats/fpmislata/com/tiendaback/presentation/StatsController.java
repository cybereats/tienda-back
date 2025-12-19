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
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

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

//        Map<String, BigDecimal> dailyIncome = Map.of("total", new BigDecimal("1250.50"));
//        return ResponseEntity.ok(dailyIncome);
    }

    @GetMapping("/daily-bookings")
    public ResponseEntity<Map<String, Long>> getDailyBookings() {
         return ResponseEntity.ok(statsService.getDailyBookings());

//        Map<String, Long> dailyBookings = Map.of("count", 24L);
//        return ResponseEntity.ok(dailyBookings);
    }

    @GetMapping("/active-bookings")
    public ResponseEntity<Map<String, Long>> getActiveBookings() {
         return ResponseEntity.ok(statsService.getActiveBookings());

//        Map<String, Long> activeBookings = Map.of(
//                "total", 200L,
//                "occupied", 156L);
//        return ResponseEntity.ok(activeBookings);
    }

    @GetMapping("/daily-summary")
    public ResponseEntity<List<Map<String, Object>>> getDailySummary() {
         return ResponseEntity.ok(statsService.getDailySummary());

//        List<Map<String, Object>> dailySummary = new ArrayList<>();
//        for (int hour = 0; hour < 24; hour++) {
//            double income = Math.random() * 100 + 20;
//            int bookings = (int) (Math.random() * 5);
//            dailySummary.add(Map.of(
//                    "hour", hour,
//                    "income", BigDecimal.valueOf(income).setScale(2, RoundingMode.HALF_UP),
//                    "bookings", bookings));
//        }
//        return ResponseEntity.ok(dailySummary);
    }

    @GetMapping("/monthly-income")
    public ResponseEntity<ChartDataResponse> getMonthlyIncome() {
         ChartDataDto dto = statsService.getMonthlyIncome();
         return
         ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

//        var series = List.of(
//                new ChartDataResponse.SeriesData("Ingresos",
//                        List.of(4500, 5200, 4800, 6100, 5500, 6700, 7200, 6900, 8100)));
//        var categories = List.of("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep");
//        var colors = List.of("#FF5733");
//        var response = new ChartDataResponse(series, categories, colors);
//        return ResponseEntity.ok(response);
    }

    @GetMapping("/monthly-users")
    public ResponseEntity<ChartDataResponse> getMonthlyUsers() {
         ChartDataDto dto = statsService.getMonthlyUsers();
         return
         ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

//        var series = List.of(
//                new ChartDataResponse.SeriesData("Usuarios", List.of(12, 18, 25, 32, 28, 45, 52, 48, 61)));
//        var categories = List.of("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep");
//        var colors = List.of("#7600A8");
//        var response = new ChartDataResponse(series, categories, colors);
//        return ResponseEntity.ok(response);
    }

    @GetMapping("/monthly-reports")
    public ResponseEntity<ChartDataResponse> getMonthlyReports() {
         ChartDataDto dto = statsService.getMonthlyReports();
         return
         ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

//        var series = List.of(
//                new ChartDataResponse.SeriesData("Reportes", List.of(8, 12, 15, 9, 18, 22, 16, 25, 20)));
//        var categories = List.of("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep");
//        var colors = List.of("#33FF57");
//        var response = new ChartDataResponse(series, categories, colors);
//        return ResponseEntity.ok(response);
    }

    @GetMapping("/income-vs-costs")
    public ResponseEntity<ChartDataResponse> getIncomeVsCosts() {
         ChartDataDto dto = statsService.getIncomeVsCosts();
         return
         ResponseEntity.ok(ChartDataMapper.getInstance().fromChartDataDtoToChartDataResponse(dto));

//        var series = List.of(
//                new ChartDataResponse.SeriesData("Ingresos",
//                        List.of(4500, 5200, 4800, 6100, 5500, 6700, 7200, 6900, 8100)),
//                new ChartDataResponse.SeriesData("Costos",
//                        List.of(3200, 3800, 3600, 4200, 4100, 4800, 5100, 4900, 5800)));
//        var categories = List.of("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep");
//        var colors = List.of("#FF5733", "#3357FF");
//        var response = new ChartDataResponse(series, categories, colors);
//        return ResponseEntity.ok(response);
    }
}
