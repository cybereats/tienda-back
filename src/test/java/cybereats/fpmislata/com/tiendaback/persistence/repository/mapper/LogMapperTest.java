package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.LogJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class LogMapperTest {

  private final LogMapper mapper = LogMapper.getInstance();

  @Test
  @DisplayName("Debería mapear de LogJpaEntity a LogDto")
  void shouldMapEntityToDto() {
    String timestamp = Instant.now().toString();
    LogJpaEntity entity = new LogJpaEntity(1L, "Info", timestamp);

    LogDto dto = mapper.fromLogJpaEntityToLogDto(entity);

    assertNotNull(dto);
    assertEquals(entity.getId(), dto.id());
    assertEquals(entity.getInfo(), dto.info());
    assertEquals(entity.getTimestamp(), dto.timestamp());
  }

  @Test
  @DisplayName("Debería devolver null al mapear una entidad nula")
  void shouldReturnNullWhenEntityIsNull() {
    assertNull(mapper.fromLogJpaEntityToLogDto(null));
  }

  @Test
  @DisplayName("Debería mapear de LogDto a LogJpaEntity")
  void shouldMapDtoToEntity() {
    String timestamp = Instant.now().toString();
    LogDto dto = new LogDto(1L, "Info", timestamp);

    LogJpaEntity entity = mapper.fromLogDtoToLogJpaEntity(dto);

    assertNotNull(entity);
    assertEquals(dto.id(), entity.getId());
    assertEquals(dto.info(), entity.getInfo());
    assertEquals(dto.timestamp(), entity.getTimestamp());
  }

  @Test
  @DisplayName("Debería devolver null al mapear un dto nulo")
  void shouldReturnNullWhenDtoIsNull() {
    assertNull(mapper.fromLogDtoToLogJpaEntity(null));
  }
}
