package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.ReportService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ReportRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
@SuppressWarnings("null")
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReportService reportService;

    private ReportDto reportDto;
    private ReportRequest reportRequest;

    @BeforeEach
    void setUp() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "Email", "BornDate", "Username", "Password", null);
        PCDto pcDto = new PCDto(1L, "Label", "Slug", 10, "Specs", "2023-01-01", "Image", null);
        reportDto = new ReportDto(1L, "1", "Description", "Subject", "OPEN", "2025-01-01", userDto, pcDto);

        reportRequest = new ReportRequest(1L, 1L, 1L, "Description", "Subject", "OPEN", "2025-01-01", "1");
    }

    @Nested
    @DisplayName("GET /api/reports")
    class GetAllReportsTests {
        @Test
        @DisplayName("Debería devolver una página de reportes")
        void shouldReturnPageOfReports() throws Exception {
            Page<ReportDto> page = new Page<>(List.of(reportDto), 1, 10, 1L);
            when(reportService.findAll(1, 10)).thenReturn(page);

            mockMvc.perform(get("/api/reports")
                    .param("page", "1")
                    .param("size", "10"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data[0].subject").value("Subject"))
                    .andExpect(jsonPath("$.totalElements").value(1));
        }
    }

    @Nested
    @DisplayName("GET /api/reports/{id}")
    class GetReportByIdTests {
        @Test
        @DisplayName("Debería devolver el reporte por ID")
        void shouldReturnReportById() throws Exception {
            when(reportService.findById(1L)).thenReturn(Optional.of(reportDto));

            mockMvc.perform(get("/api/reports/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.subject").value("Subject"));
        }
    }

    @Nested
    @DisplayName("POST /api/reports")
    class CreateReportTests {
        @Test
        @DisplayName("Debería crear un nuevo reporte")
        void shouldCreateReport() throws Exception {
            when(reportService.insert(any(ReportDto.class))).thenReturn(reportDto);

            mockMvc.perform(post("/api/reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(reportRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.subject").value("Subject"));
        }
    }

    @Nested
    @DisplayName("PUT /api/reports/{id}")
    class UpdateReportTests {
        @Test
        @DisplayName("Debería actualizar un reporte")
        void shouldUpdateReport() throws Exception {
            when(reportService.update(any(ReportDto.class))).thenReturn(reportDto);

            mockMvc.perform(put("/api/reports/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(reportRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.subject").value("Subject"));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando los IDs no coinciden")
        void shouldReturn400WhenIdMismatch() throws Exception {
            mockMvc.perform(put("/api/reports/999")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(reportRequest)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("DELETE /api/reports/{id}")
    class DeleteReportTests {
        @Test
        @DisplayName("Debería eliminar un reporte")
        void shouldDeleteReport() throws Exception {
            mockMvc.perform(delete("/api/reports/1"))
                    .andExpect(status().isNoContent());

            verify(reportService, times(1)).deleteById(1L);
        }
    }
}
