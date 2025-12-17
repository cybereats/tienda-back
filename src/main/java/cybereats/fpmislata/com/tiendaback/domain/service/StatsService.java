package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ChartDataDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StatsService {
    Map<String, BigDecimal> getDailyIncome();

    Map<String, Long> getDailyBookings();

    Map<String, Long> getActiveBookings();

    List<Map<String, Object>> getDailySummary();

    ChartDataDto getMonthlyIncome();

    ChartDataDto getMonthlyUsers();

    ChartDataDto getMonthlyReports();

    ChartDataDto getIncomeVsCosts();
}
