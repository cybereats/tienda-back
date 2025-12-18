package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Log;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class LogMapperTest {

  private final LogMapper mapper = LogMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de Log a LogDto")
  void shouldMapModelToDto() {
    String timestamp = Instant.now().toString();
    Log log = new Log.Builder()
        .id(1L)
        .info("Info")
        .timestamp(timestamp)
        .build();

    LogDto dto = mapper.fromLogToLogDto(log);

    assertNotNull(dto);
    assertEquals(log.getId(), dto.id());
    assertEquals(log.getInfo(), dto.info());
    assertEquals(log.getTimestamp(), dto.timestamp());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un log nulo")
  void shouldReturnNullWhenModelIsNull() {
    assertNull(mapper.fromLogToLogDto(null));
  }

  @Test
  @DisplayName("Debería mapear de LogDto a Log")
  void shouldMapDtoToModel() {
    String timestamp = Instant.now().toString();
    LogDto dto = new LogDto(1L, "Info", timestamp);

    Log log = mapper.fromLogDtoToLog(dto);

    assertNotNull(log);
    assertEquals(dto.id(), log.getId());
    assertEquals(dto.info(), log.getInfo());
    assertEquals(dto.timestamp(), log.getTimestamp());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromLogDtoToLog(null));
  }
}
