package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.ReportRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private ReportDto reportDto;

    @BeforeEach
    void setUp() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "Email", "BornDate", "Username", "Password", null);
        PCDto pcDto = new PCDto(1L, "Label", "Slug", 10, "Specs", "2023-01-01", "Image", null);
        reportDto = new ReportDto(1L, "1", "Description", "Subject", "OPEN", "2025-01-01", userDto, pcDto);
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de reportes")
        void shouldReturnPageOfReports() {
            Page<ReportDto> page = new Page<>(List.of(reportDto), 1, 10, 1L);
            when(reportRepository.findAll(1, 10)).thenReturn(page);

            Page<ReportDto> result = reportService.findAll(1, 10);

            assertAll("Verificación de página",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1L, result.totalElements()));
        }
    }

    @Nested
    @DisplayName("Tests para el método insert")
    class InsertTests {
        @Test
        @DisplayName("Debería crear un nuevo reporte con valores por defecto")
        void shouldInsertReportWithDefaults() {
            ReportDto inputDto = new ReportDto(null, "1", "Desc", "Subject", null, null, reportDto.user(),
                    reportDto.pc());
            when(reportRepository.findById(null)).thenReturn(Optional.empty());
            when(reportRepository.save(any(ReportDto.class))).thenReturn(reportDto);

            ReportDto result = reportService.insert(inputDto);

            assertNotNull(result);
            verify(reportRepository, times(1)).save(any(ReportDto.class));
        }

        @Test
        @DisplayName("Debería lanzar BusinessException si ya existe")
        void shouldThrowExceptionIfExists() {
            when(reportRepository.findById(1L)).thenReturn(Optional.of(reportDto));
            assertThrows(BusinessException.class, () -> reportService.insert(reportDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {
        @Test
        @DisplayName("Debería actualizar un reporte existente")
        void shouldUpdateExistingReport() {
            when(reportRepository.findById(1L)).thenReturn(Optional.of(reportDto));
            when(reportRepository.save(any(ReportDto.class))).thenReturn(reportDto);

            ReportDto result = reportService.update(reportDto);

            assertNotNull(result);
            verify(reportRepository, times(1)).save(any(ReportDto.class));
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException si no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(reportRepository.findById(1L)).thenReturn(Optional.empty());
            assertThrows(ResourceNotFoundException.class, () -> reportService.update(reportDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {
        @Test
        @DisplayName("Debería eliminar un reporte")
        void shouldDeleteReport() {
            when(reportRepository.findById(1L)).thenReturn(Optional.of(reportDto));
            reportService.deleteById(1L);
            verify(reportRepository, times(1)).deleteById(1L);
        }
    }
}
