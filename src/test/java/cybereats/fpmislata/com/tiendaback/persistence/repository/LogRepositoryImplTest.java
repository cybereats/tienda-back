package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.LogJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.LogJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogRepositoryImplTest {

  @Mock
  private LogJpaDao logJpaDao;

  @InjectMocks
  private LogRepositoryImpl logRepository;

  private LogJpaEntity logJpaEntity;
  private LogDto logDto;
  private String timestamp;

  @BeforeEach
  void setUp() {
    timestamp = Instant.now().toString();
    logJpaEntity = new LogJpaEntity(1L, "Info", timestamp);
    logDto = new LogDto(1L, "Info", timestamp);
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de DTOs")
    void shouldReturnPage() {
      when(logJpaDao.findAll(1, 10)).thenReturn(List.of(logJpaEntity));
      when(logJpaDao.count()).thenReturn(1L);

      Page<LogDto> result = logRepository.findAll(1, 10);

      assertNotNull(result);
      assertEquals(1, result.data().size());
      assertEquals(logDto.id(), result.data().get(0).id());
      assertEquals(1L, result.totalElements());
    }
  }

  @Nested
  @DisplayName("Tests para el método findById")
  class FindByIdTests {
    @Test
    @DisplayName("Debería devolver Optional con DTO cuando existe")
    void shouldReturnOptionalWithDtoWhenExists() {
      when(logJpaDao.findById(1L)).thenReturn(Optional.of(logJpaEntity));

      Optional<LogDto> result = logRepository.findById(1L);

      assertTrue(result.isPresent());
      assertEquals(logDto.id(), result.get().id());
    }

    @Test
    @DisplayName("Debería devolver Optional vacío cuando no existe")
    void shouldReturnEmptyOptionalWhenDoesNotExist() {
      when(logJpaDao.findById(1L)).thenReturn(Optional.empty());

      Optional<LogDto> result = logRepository.findById(1L);

      assertFalse(result.isPresent());
    }
  }

  @Nested
  @DisplayName("Tests para el método save")
  class SaveTests {
    @Test
    @DisplayName("Debería llamar a insert cuando el ID es nulo")
    void shouldCallInsertWhenIdIsNull() {
      LogDto newDto = new LogDto(null, "New", timestamp);
      LogJpaEntity savedEntity = new LogJpaEntity(2L, "New", timestamp);

      when(logJpaDao.insert(any(LogJpaEntity.class))).thenReturn(savedEntity);

      LogDto result = logRepository.save(newDto);

      assertNotNull(result.id());
      assertEquals(2L, result.id());
      verify(logJpaDao).insert(any(LogJpaEntity.class));
    }

    @Test
    @DisplayName("Debería llamar a update cuando el ID no es nulo")
    void shouldCallUpdateWhenIdIsNotNull() {
      when(logJpaDao.update(any(LogJpaEntity.class))).thenReturn(logJpaEntity);

      LogDto result = logRepository.save(logDto);

      assertEquals(logDto.id(), result.id());
      verify(logJpaDao).update(any(LogJpaEntity.class));
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  class DeleteTests {
    @Test
    @DisplayName("Debería llamar a deleteById del DAO")
    void shouldCallDaoDeleteById() {
      doNothing().when(logJpaDao).deleteById(1L);

      logRepository.deleteById(1L);

      verify(logJpaDao).deleteById(1L);
    }
  }
}
