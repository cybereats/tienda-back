package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ChartDataDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ChartDataResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChartDataMapperTest {

  private final ChartDataMapper mapper = ChartDataMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de ChartDataDto a ChartDataResponse")
  void shouldMapDtoToResponse() {
    var series = List.of(new ChartDataDto.SeriesData("Income", List.of(100, 200)));
    var categories = List.of("Jan", "Feb");
    var colors = List.of("#FF0000");
    ChartDataDto dto = new ChartDataDto(series, categories, colors);

    ChartDataResponse response = mapper.fromChartDataDtoToChartDataResponse(dto);

    assertNotNull(response);
    assertEquals(1, response.series().size());
    assertEquals("Income", response.series().get(0).name());
    assertEquals(List.of(100, 200), response.series().get(0).data());
    assertEquals(categories, response.categories());
    assertEquals(colors, response.colors());
  }
}
