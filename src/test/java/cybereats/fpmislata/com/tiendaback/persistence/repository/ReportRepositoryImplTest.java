package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ReportJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
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
class ReportRepositoryImplTest {

    @Mock
    private ReportJpaDao reportJpaDao;

    @InjectMocks
    private ReportRepositoryImpl reportRepository;

    private ReportJpaEntity reportJpaEntity;
    private ReportDto reportDto;

    @BeforeEach
    void setUp() {
        UserJpaEntity userEntity = new UserJpaEntity();
        userEntity.setId(1L);
        PCJpaEntity pcEntity = new PCJpaEntity(1L, "Label", "Slug", 10, "Specs", "2023-01-01", "Image", "AVAILABLE",
                null);

        reportJpaEntity = new ReportJpaEntity(1L, "1", "Description", "Subject", "OPEN", "2025-01-01", userEntity,
                pcEntity);

        UserDto userDto = new UserDto(1L, "Name", "Surname", "Email", "BornDate", "Username", "Password", null);
        PCDto pcDto = new PCDto(1L, "Label", "Slug", 10, "Specs", "2023-01-01", "Image", PCStatus.AVAILABLE, null);
        reportDto = new ReportDto(1L, "1", "Description", "Subject", "OPEN", "2025-01-01", userDto, pcDto);
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de reportes")
        void shouldReturnPageOfReports() {
            when(reportJpaDao.findAll(1, 10)).thenReturn(List.of(reportJpaEntity));
            when(reportJpaDao.count()).thenReturn(1L);

            Page<ReportDto> result = reportRepository.findAll(1, 10);

            assertAll("Verificación de página",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1L, result.totalElements()),
                    () -> assertEquals("Subject", result.data().get(0).subject()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {
        @Test
        @DisplayName("Debería devolver un reporte por ID")
        void shouldReturnReportById() {
            when(reportJpaDao.findById(1L)).thenReturn(Optional.of(reportJpaEntity));

            Optional<ReportDto> result = reportRepository.findById(1L);

            assertTrue(result.isPresent());
            assertEquals("Subject", result.get().subject());
        }
    }

    @Nested
    @DisplayName("Tests para el método save")
    class SaveTests {
        @Test
        @DisplayName("Debería insertar un nuevo reporte")
        void shouldInsertReport() {
            ReportDto newReportDto = new ReportDto(null, "1", "Desc", "Sub", "OPEN", "2025-01-01", reportDto.user(),
                    reportDto.pc());
            when(reportJpaDao.insert(any(ReportJpaEntity.class))).thenReturn(reportJpaEntity);

            ReportDto result = reportRepository.save(newReportDto);

            assertNotNull(result);
            verify(reportJpaDao, times(1)).insert(any(ReportJpaEntity.class));
        }

        @Test
        @DisplayName("Debería actualizar un reporte")
        void shouldUpdateReport() {
            when(reportJpaDao.update(any(ReportJpaEntity.class))).thenReturn(reportJpaEntity);

            ReportDto result = reportRepository.save(reportDto);

            assertNotNull(result);
            verify(reportJpaDao, times(1)).update(any(ReportJpaEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests para métodos de búsqueda personalizados")
    class CustomSearchTests {
        @Test
        @DisplayName("Debería buscar por ID de usuario")
        void shouldFindByUserId() {
            when(reportJpaDao.findByUserId(1L)).thenReturn(List.of(reportJpaEntity));
            List<ReportDto> result = reportRepository.findByUserId(1L);
            assertFalse(result.isEmpty());
            verify(reportJpaDao, times(1)).findByUserId(1L);
        }
    }
}
