package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import java.util.List;

public record ChartDataResponse(
    List<SeriesData> series,
    List<String> categories,
    List<String> colors) {
  public record SeriesData(
      String name,
      List<Integer> data) {
  }
}