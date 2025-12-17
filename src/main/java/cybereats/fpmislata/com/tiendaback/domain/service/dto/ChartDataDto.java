package cybereats.fpmislata.com.tiendaback.domain.service.dto;

import java.util.List;

public record ChartDataDto(
    List<SeriesData> series,
    List<String> categories,
    List<String> colors) {
  public record SeriesData(
      String name,
      List<Integer> data) {
  }
}