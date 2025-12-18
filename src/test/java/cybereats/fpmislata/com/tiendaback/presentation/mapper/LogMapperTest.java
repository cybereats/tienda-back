package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.LogRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.LogResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class LogMapperTest {

  private final LogMapper mapper = LogMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de LogRequest a LogDto")
  void shouldMapRequestToDto() {
    String timestamp = Instant.now().toString();
    LogRequest request = new LogRequest(1L, "Info", timestamp);

    LogDto dto = mapper.fromLogRequestToLogDto(request);

    assertNotNull(dto);
    assertEquals(request.id(), dto.id());
    assertEquals(request.info(), dto.info());
    assertEquals(request.timestamp(), dto.timestamp());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un request nulo")
  void shouldReturnNullWhenRequestIsNull() {
    assertNull(mapper.fromLogRequestToLogDto(null));
  }

  @Test
  @DisplayName("Debería mapear de LogDto a LogResponse")
  void shouldMapDtoToResponse() {
    String timestamp = Instant.now().toString();
    LogDto dto = new LogDto(1L, "Info", timestamp);

    LogResponse response = mapper.fromLogDtoToLogResponse(dto);

    assertNotNull(response);
    assertEquals(dto.id(), response.id());
    assertEquals(dto.info(), response.info());
    assertEquals(dto.timestamp(), response.timestamp());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromLogDtoToLogResponse(null));
  }
}
