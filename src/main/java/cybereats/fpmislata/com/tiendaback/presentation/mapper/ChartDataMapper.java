package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ChartDataDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ChartDataResponse;

public class ChartDataMapper {

  private static ChartDataMapper instance;

  private ChartDataMapper() {
  }

  public static ChartDataMapper getInstance() {
    if (instance == null) {
      instance = new ChartDataMapper();
    }
    return instance;
  }

  public ChartDataResponse fromChartDataDtoToChartDataResponse(ChartDataDto dto) {
    var series = dto.series().stream()
        .map(s -> new ChartDataResponse.SeriesData(s.name(), s.data()))
        .toList();
    return new ChartDataResponse(series, dto.categories(), dto.colors());
  }
}