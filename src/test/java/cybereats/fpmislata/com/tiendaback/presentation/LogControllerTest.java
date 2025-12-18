package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.LogService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.LogRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LogController.class)
public class LogControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private LogService logService;

  @Autowired
  private ObjectMapper objectMapper;

  private LogDto logDto;
  private String timestamp;

  @BeforeEach
  void setUp() {
    timestamp = Instant.now().toString();
    logDto = new LogDto(1L, "System start", timestamp);
  }

  @Nested
  @DisplayName("Tests para el método findAllLogs")
  class FindAllLogsTests {

    @Test
    @DisplayName("Debería devolver 200 y una página de logs")
    void shouldReturnOkAndPage() throws Exception {
      Page<LogDto> page = new Page<>(List.of(logDto), 1, 10, 1L);
      when(logService.findAll(1, 10)).thenReturn(page);

      mockMvc.perform(get("/api/logs?page=1&size=10"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data[0].id").value(logDto.id()))
          .andExpect(jsonPath("$.data[0].info").value(logDto.info()))
          .andExpect(jsonPath("$.pageNumber").value(1))
          .andExpect(jsonPath("$.totalElements").value(1));
    }
  }

  @Nested
  @DisplayName("Tests para el método getLogById")
  class GetLogByIdTests {

    @Test
    @DisplayName("Debería devolver 200 cuando el ID existe")
    void shouldReturnOkWhenExists() throws Exception {
      when(logService.getById(1L)).thenReturn(logDto);

      mockMvc.perform(get("/api/logs/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1L))
          .andExpect(jsonPath("$.info").value("System start"));
    }

    @Test
    @DisplayName("Debería devolver 404 cuando el ID no existe")
    void shouldReturnNotFoundWhenDoesNotExist() throws Exception {
      when(logService.getById(999L)).thenThrow(new ResourceNotFoundException("Log not found"));

      mockMvc.perform(get("/api/logs/999"))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("Tests para el método createLog")
  class CreateLogTests {

    @Test
    @DisplayName("Debería devolver 201 cuando la petición es válida")
    void shouldReturnCreatedWhenValid() throws Exception {
      LogRequest request = new LogRequest(null, "New entry", timestamp);
      LogDto createdDto = new LogDto(2L, "New entry", timestamp);
      when(logService.create(any(LogDto.class))).thenReturn(createdDto);

      mockMvc.perform(post("/api/logs")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(2L))
          .andExpect(jsonPath("$.info").value("New entry"));
    }

    @Test
    @DisplayName("Debería devolver 400 cuando la petición es inválida (info nula)")
    void shouldReturnBadRequestWhenInvalid() throws Exception {
      LogRequest request = new LogRequest(null, null, timestamp);

      mockMvc.perform(post("/api/logs")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("Tests para el método updateLog")
  class UpdateLogTests {

    @Test
    @DisplayName("Debería devolver 200 cuando la petición es válida")
    void shouldReturnOkWhenValid() throws Exception {
      LogRequest request = new LogRequest(1L, "Updated entry", timestamp);
      when(logService.update(any(LogDto.class))).thenReturn(logDto);

      mockMvc.perform(put("/api/logs/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Debería devolver 400 cuando los IDs no coinciden")
    void shouldReturnBadRequestWhenIdMismatch() throws Exception {
      LogRequest request = new LogRequest(2L, "Mismatch", timestamp);

      mockMvc.perform(put("/api/logs/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteLog")
  class DeleteLogTests {

    @Test
    @DisplayName("Debería devolver 204 cuando el borrado es exitoso")
    void shouldReturnNoContent() throws Exception {
      doNothing().when(logService).deleteById(1L);

      mockMvc.perform(delete("/api/logs/1"))
          .andExpect(status().isNoContent());
    }
  }
}
