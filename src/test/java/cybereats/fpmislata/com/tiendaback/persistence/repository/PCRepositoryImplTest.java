package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.PCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
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
class PCRepositoryImplTest {

    @Mock
    private PCJpaDao pcJpaDao;

    @InjectMocks
    private PCRepositoryImpl pcRepository;

    private PCJpaEntity pcJpaEntity;
    private PCDto pcDto;

    @BeforeEach
    void setUp() {
        CategoryPCJpaEntity categoryEntity = new CategoryPCJpaEntity(2L, "Gaming", "gaming", new BigDecimal("5.00"));
        pcJpaEntity = new PCJpaEntity(1L, "PC Gamer 1", "pc-gamer-1", 12, "Specs", "2023-01-01", "image.jpg",
                categoryEntity);

        CategoryPCDto categoryDto = new CategoryPCDto(2L, "Gaming", "gaming", new BigDecimal("5.00"));
        pcDto = new PCDto(1L, "PC Gamer 1", "pc-gamer-1", 12, "Specs", "2023-01-01", "image.jpg", categoryDto);
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver una página de PCs")
        void shouldReturnPageOfPCs() {
            when(pcJpaDao.findAll(1, 10)).thenReturn(List.of(pcJpaEntity));
            when(pcJpaDao.count()).thenReturn(1L);

            Page<PCDto> result = pcRepository.findAll(1, 10);

            assertAll("Verificación de página",
                    () -> assertNotNull(result),
                    () -> assertEquals(1, result.data().size()),
                    () -> assertEquals(1L, result.totalElements()),
                    () -> assertEquals(pcDto.slug(), result.data().get(0).slug()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {
        @Test
        @DisplayName("Debería devolver un PC por ID")
        void shouldReturnPCById() {
            when(pcJpaDao.findById(1L)).thenReturn(Optional.of(pcJpaEntity));

            Optional<PCDto> result = pcRepository.findById(1L);

            assertTrue(result.isPresent());
            assertEquals(pcDto.slug(), result.get().slug());
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
        void shouldReturnEmptyWhenIdDoesNotExist() {
            when(pcJpaDao.findById(999L)).thenReturn(Optional.empty());
            assertTrue(pcRepository.findById(999L).isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método findBySlug")
    class FindBySlugTests {
        @Test
        @DisplayName("Debería devolver un PC por SLUG")
        void shouldReturnPCBySlug() {
            when(pcJpaDao.findBySlug("pc-gamer-1")).thenReturn(Optional.of(pcJpaEntity));

            Optional<PCDto> result = pcRepository.findBySlug("pc-gamer-1");

            assertTrue(result.isPresent());
            assertEquals(pcDto.id(), result.get().id());
        }
    }

    @Nested
    @DisplayName("Tests para el método save")
    class SaveTests {
        @Test
        @DisplayName("Debería insertar un nuevo PC")
        void shouldInsertNewPC() {
            PCDto newPcDto = new PCDto(null, "New PC", "new-pc", 10, "Specs", "2023-01-01", "new.jpg",
                    pcDto.categoryPCDto());
            when(pcJpaDao.insert(any(PCJpaEntity.class))).thenReturn(pcJpaEntity);

            PCDto result = pcRepository.save(newPcDto);

            assertNotNull(result);
            verify(pcJpaDao, times(1)).insert(any(PCJpaEntity.class));
        }

        @Test
        @DisplayName("Debería actualizar un PC")
        void shouldUpdatePC() {
            when(pcJpaDao.update(any(PCJpaEntity.class))).thenReturn(pcJpaEntity);

            PCDto result = pcRepository.save(pcDto);

            assertNotNull(result);
            verify(pcJpaDao, times(1)).update(any(PCJpaEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteBySlug")
    class DeleteBySlugTests {
        @Test
        @DisplayName("Debería eliminar por SLUG")
        void shouldDeleteBySlug() {
            pcRepository.deleteBySlug("pc-gamer-1");
            verify(pcJpaDao, times(1)).deleteBySlug("pc-gamer-1");
        }
    }

    @Nested
    @DisplayName("Tests para el método count")
    class CountTests {
        @Test
        @DisplayName("Debería devolver el conteo total")
        void shouldReturnCount() {
            when(pcJpaDao.count()).thenReturn(10L);
            assertEquals(10L, pcRepository.count());
        }
    }
}
