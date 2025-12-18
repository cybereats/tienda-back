package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.LogRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogServiceImplTest {

  @Mock
  private LogRepository logRepository;

  @InjectMocks
  private LogServiceImpl logService;

  private LogDto logDto;
  private String timestamp;

  @BeforeEach
  void setUp() {
    timestamp = Instant.now().toString();
    logDto = new LogDto(1L, "Info", timestamp);
  }

  @Nested
  @DisplayName("Tests para el método findAll")
  class FindAllTests {
    @Test
    @DisplayName("Debería devolver una página de logs")
    void shouldReturnPage() {
      Page<LogDto> page = new Page<>(List.of(logDto), 1, 10, 1L);
      when(logRepository.findAll(1, 10)).thenReturn(page);

      Page<LogDto> result = logService.findAll(1, 10);

      assertNotNull(result);
      assertEquals(1, result.data().size());
      assertEquals(logDto, result.data().get(0));
    }
  }

  @Nested
  @DisplayName("Tests para el método getById")
  class GetByIdTests {
    @Test
    @DisplayName("Debería devolver el log cuando existe")
    void shouldReturnLogWhenExists() {
      when(logRepository.findById(1L)).thenReturn(Optional.of(logDto));

      LogDto result = logService.getById(1L);

      assertEquals(logDto, result);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(logRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> logService.getById(1L));
    }
  }

  @Nested
  @DisplayName("Tests para el método create")
  class CreateTests {
    @Test
    @DisplayName("Debería crear el log cuando no existe")
    void shouldCreateWhenDoesNotExist() {
      when(logRepository.findById(1L)).thenReturn(Optional.empty());
      when(logRepository.save(logDto)).thenReturn(logDto);

      LogDto result = logService.create(logDto);

      assertEquals(logDto, result);
      verify(logRepository).save(logDto);
    }

    @Test
    @DisplayName("Debería lanzar BusinessException cuando ya existe")
    void shouldThrowExceptionWhenAlreadyExists() {
      when(logRepository.findById(1L)).thenReturn(Optional.of(logDto));

      assertThrows(BusinessException.class, () -> logService.create(logDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método update")
  class UpdateTests {
    @Test
    @DisplayName("Debería actualizar el log cuando existe")
    void shouldUpdateWhenExists() {
      when(logRepository.findById(1L)).thenReturn(Optional.of(logDto));
      when(logRepository.save(logDto)).thenReturn(logDto);

      LogDto result = logService.update(logDto);

      assertEquals(logDto, result);
      verify(logRepository).save(logDto);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(logRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> logService.update(logDto));
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteById")
  class DeleteTests {
    @Test
    @DisplayName("Debería borrar el log cuando existe")
    void shouldDeleteWhenExists() {
      when(logRepository.findById(1L)).thenReturn(Optional.of(logDto));
      doNothing().when(logRepository).deleteById(1L);

      logService.deleteById(1L);

      verify(logRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
    void shouldThrowExceptionWhenDoesNotExist() {
      when(logRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> logService.deleteById(1L));
    }
  }
}
