package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PCServiceImplTest {

    @Mock
    private PCRepository pcRepository;

    @InjectMocks
    private PCServiceImpl pcService;

    private PCDto pcDto;

    @BeforeEach
    void setUp() {
        CategoryPCDto categoryDto = new CategoryPCDto(2L, "Gaming", "gaming", new BigDecimal("5.00"));
        pcDto = new PCDto(1L, "PC Gamer 1", "pc-gamer-1", 12, "Specs", "2023-01-01", "image.jpg", categoryDto);
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de PCs")
        void shouldReturnPageOfPCs() {
            Page<PCDto> page = new Page<>(List.of(pcDto), 1, 10, 1L);
            when(pcRepository.findAll(1, 10)).thenReturn(page);

            Page<PCDto> result = pcService.findAll(1, 10);

            assertAll("Verificación de página",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1L, result.totalElements()));
        }
    }

    @Nested
    @DisplayName("Tests para el método getBySlug")
    class GetBySlugTests {
        @Test
        @DisplayName("Debería devolver el PC cuando existe")
        void shouldReturnPCWhenExists() {
            when(pcRepository.findBySlug("pc-gamer-1")).thenReturn(Optional.of(pcDto));

            PCDto result = pcService.getBySlug("pc-gamer-1");

            assertNotNull(result);
            assertEquals("pc-gamer-1", result.slug());
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(pcRepository.findBySlug("non-existent")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> pcService.getBySlug("non-existent"));
        }
    }

    @Nested
    @DisplayName("Tests para el método create")
    class CreateTests {
        @Test
        @DisplayName("Debería crear un nuevo PC")
        void shouldCreateNewPC() {
            when(pcRepository.findBySlug(pcDto.slug())).thenReturn(Optional.empty());
            when(pcRepository.save(pcDto)).thenReturn(pcDto);

            PCDto result = pcService.create(pcDto);

            assertNotNull(result);
            verify(pcRepository, times(1)).save(pcDto);
        }

        @Test
        @DisplayName("Debería lanzar BusinessException si el PC ya existe")
        void shouldThrowExceptionIfPCExists() {
            when(pcRepository.findBySlug(pcDto.slug())).thenReturn(Optional.of(pcDto));

            assertThrows(BusinessException.class, () -> pcService.create(pcDto));
            verify(pcRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {
        @Test
        @DisplayName("Debería actualizar un PC existente")
        void shouldUpdateExistingPC() {
            when(pcRepository.findBySlug(pcDto.slug())).thenReturn(Optional.of(pcDto));
            when(pcRepository.save(pcDto)).thenReturn(pcDto);

            PCDto result = pcService.update(pcDto);

            assertNotNull(result);
            verify(pcRepository, times(1)).save(pcDto);
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException si el PC no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(pcRepository.findBySlug(pcDto.slug())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> pcService.update(pcDto));
            verify(pcRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteBySlug")
    class DeleteTests {
        @Test
        @DisplayName("Debería eliminar un PC por SLUG")
        void shouldDeleteBySlug() {
            when(pcRepository.findBySlug("pc-gamer-1")).thenReturn(Optional.of(pcDto));

            pcService.deleteBySlug("pc-gamer-1");

            verify(pcRepository, times(1)).deleteBySlug("pc-gamer-1");
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException si el PC no existe")
        void shouldThrowExceptionWhenNotExists() {
            when(pcRepository.findBySlug("non-existent")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> pcService.deleteBySlug("non-existent"));
            verify(pcRepository, never()).deleteBySlug(any());
        }
    }
}
