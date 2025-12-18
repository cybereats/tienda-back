package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import cybereats.fpmislata.com.tiendaback.domain.repository.BookingRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.ReportRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserOrderRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.StatsService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ChartDataDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserOrderDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class StatsServiceImpl implements StatsService {

        private final UserOrderRepository userOrderRepository;
        private final BookingRepository bookingRepository;
        private final PCRepository pcRepository;
        private final UserRepository userRepository;
        private final ReportRepository reportRepository;

        public StatsServiceImpl(UserOrderRepository userOrderRepository, BookingRepository bookingRepository,
                        PCRepository pcRepository, UserRepository userRepository, ReportRepository reportRepository) {
                this.userOrderRepository = userOrderRepository;
                this.bookingRepository = bookingRepository;
                this.pcRepository = pcRepository;
                this.userRepository = userRepository;
                this.reportRepository = reportRepository;
        }

        @Override
        public Map<String, BigDecimal> getDailyIncome() {
                LocalDate today = LocalDate.now();
                List<UserOrderDto> orders = userOrderRepository.findAll();

                BigDecimal total = orders.stream()
                                .filter(order -> order.createdAt() != null
                                                && order.createdAt().toLocalDate().equals(today))
                                .filter(order -> order.status() == OrderStatus.CONFIRMED
                                                || order.status() == OrderStatus.SHIPPED
                                                || order.status() == OrderStatus.DELIVERED)
                                .flatMap(order -> order.orderItems().stream())
                                .map(item -> item.product().price().multiply(BigDecimal.valueOf(item.quantity())))
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                return Map.of("total", total);
        }

        @Override
        public Map<String, Long> getDailyBookings() {
                LocalDate today = LocalDate.now();
                List<BookingDto> bookings = bookingRepository.findAll();

                long count = bookings.stream()
                                .filter(booking -> booking.createdAt() != null
                                                && booking.createdAt().toLocalDate().equals(today))
                                .count();

                return Map.of("count", count);
        }

        @Override
        public Map<String, Long> getActiveBookings() {
                LocalDateTime now = LocalDateTime.now();
                long totalPcs = pcRepository.count();
                List<BookingDto> bookings = bookingRepository.findAll();

                long occupied = bookings.stream()
                                .filter(booking -> booking.createdAt() != null)
                                .filter(booking -> booking.createdAt().plusHours(booking.hours()).isAfter(now))
                                .count();

                Map<String, Long> result = new HashMap<>();
                result.put("total", totalPcs);
                result.put("occupied", occupied);
                return result;
        }

        @Override
        public List<Map<String, Object>> getDailySummary() {
                LocalDate today = LocalDate.now();
                List<UserOrderDto> orders = userOrderRepository.findAll();

                Map<Integer, BigDecimal> hourlyIncome = new HashMap<>();
                for (int i = 0; i < 24; i++) {
                        hourlyIncome.put(i, BigDecimal.ZERO);
                }

                orders.stream()
                                .filter(order -> order.createdAt() != null
                                                && order.createdAt().toLocalDate().equals(today))
                                .filter(order -> order.status() == OrderStatus.CONFIRMED
                                                || order.status() == OrderStatus.SHIPPED
                                                || order.status() == OrderStatus.DELIVERED)
                                .forEach(order -> {
                                        int hour = order.createdAt().getHour();
                                        BigDecimal orderTotal = order.orderItems().stream()
                                                        .map(item -> item.product().price()
                                                                        .multiply(BigDecimal.valueOf(item.quantity())))
                                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                        hourlyIncome.put(hour, hourlyIncome.get(hour).add(orderTotal));
                                });

                List<Map<String, Object>> result = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                        Map<String, Object> entry = new HashMap<>();
                        entry.put("hour", i);
                        entry.put("income", hourlyIncome.get(i));
                        result.add(entry);
                }
                return result;
        }

        @Override
        public ChartDataDto getMonthlyIncome() {
                List<UserOrderDto> orders = userOrderRepository.findAll();
                Map<Integer, BigDecimal> monthlySales = new HashMap<>();
                for (int i = 1; i <= 12; i++) {
                        monthlySales.put(i, BigDecimal.ZERO);
                }

                orders.stream()
                                .filter(order -> order.createdAt() != null)
                                .filter(order -> order.status() == OrderStatus.CONFIRMED
                                                || order.status() == OrderStatus.SHIPPED
                                                || order.status() == OrderStatus.DELIVERED)
                                .forEach(order -> {
                                        int month = order.createdAt().getMonthValue();
                                        BigDecimal orderTotal = order.orderItems().stream()
                                                        .map(item -> item.product().price()
                                                                        .multiply(BigDecimal.valueOf(item.quantity())))
                                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                        monthlySales.put(month, monthlySales.get(month).add(orderTotal));
                                });

                List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream()
                                .map(month -> monthlySales.get(month).intValue())
                                .toList();

                var series = Arrays.asList(
                                new ChartDataDto.SeriesData("Ventas", data));
                var categories = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep");
                var colors = Arrays.asList("#7600A8");
                return new ChartDataDto(series, categories, colors);
        }

        @Override
        public ChartDataDto getMonthlyUsers() {
                List<UserDto> users = userRepository.findAll(1, 1000).data();
                int totalUsers = users.size();
                List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream()
                                .map(month -> totalUsers / 9)
                                .toList();

                var series = Arrays.asList(
                                new ChartDataDto.SeriesData("Usuarios", data));
                var categories = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep");
                var colors = Arrays.asList("#7600A8");
                return new ChartDataDto(series, categories, colors);
        }

        @Override
        public ChartDataDto getMonthlyReports() {
                List<ReportDto> reports = reportRepository.findAll(1, 1000).data();
                int totalReports = reports.size();
                List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream()
                                .map(month -> totalReports / 9)
                                .toList();

                var series = Arrays.asList(
                                new ChartDataDto.SeriesData("Reportes", data));
                var categories = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep");
                var colors = Arrays.asList("#7600A8");
                return new ChartDataDto(series, categories, colors);
        }

        @Override
        public ChartDataDto getIncomeVsCosts() {
                List<UserOrderDto> orders = userOrderRepository.findAll();
                Map<Integer, BigDecimal> monthlyIncome = new HashMap<>();
                Map<Integer, Long> monthlyOrders = new HashMap<>();
                for (int i = 1; i <= 12; i++) {
                        monthlyIncome.put(i, BigDecimal.ZERO);
                        monthlyOrders.put(i, 0L);
                }

                orders.stream()
                                .filter(order -> order.createdAt() != null)
                                .filter(order -> order.status() == OrderStatus.CONFIRMED
                                                || order.status() == OrderStatus.SHIPPED
                                                || order.status() == OrderStatus.DELIVERED)
                                .forEach(order -> {
                                        int month = order.createdAt().getMonthValue();
                                        BigDecimal orderTotal = order.orderItems().stream()
                                                        .map(item -> item.product().price()
                                                                        .multiply(BigDecimal.valueOf(item.quantity())))
                                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                        monthlyIncome.put(month, monthlyIncome.get(month).add(orderTotal));
                                        monthlyOrders.put(month, monthlyOrders.get(month) + 1);
                                });

                List<Integer> incomeData = Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream()
                                .map(month -> monthlyIncome.get(month).intValue())
                                .toList();
                List<Integer> costData = Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream()
                                .map(month -> monthlyOrders.get(month).intValue() * 10)
                                .toList();

                var series = Arrays.asList(
                                new ChartDataDto.SeriesData("Ingresos", incomeData),
                                new ChartDataDto.SeriesData("Costos", costData));
                var categories = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul");
                var colors = Arrays.asList("#00f514ff", "#cc0000ff");
                return new ChartDataDto(series, categories, colors);
        }
}
