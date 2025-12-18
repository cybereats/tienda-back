package cybereats.fpmislata.com.tiendaback.persistence.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.BookingJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para BookingRepositoryImpl")
class BookingRepositoryImplTest {

    @Mock
    private BookingJpaDao bookingJpaDao;

    @InjectMocks
    private BookingRepositoryImpl bookingRepository;

    private List<BookingJpaEntity> bookingJpaEntities;
    private List<BookingDto> bookingDtos;

    private List<UserJpaEntity> userJpaEntities;
    private List<UserDto> userDtos;
    private UserDto userDto;

    private List<PCJpaEntity> pcJpaEntities;
    private List<PCDto> pcDtos;
    private PCDto pcDto;

    @BeforeEach
    void setUp() {

        userJpaEntities = List.of(
                new UserJpaEntity(1L, "name", "surnama1", "email1", "Date1", "username1", "password1", UserRole.CLIENT),
                new UserJpaEntity(2L, "name", "surnama2", "email2", "Date2", "username2", "password2",
                        UserRole.CLIENT));

        userDtos = List.of(
                new UserDto(1L, "name", "surnama1", "email1", "Date1", "username1", "password1", UserRole.CLIENT),
                new UserDto(2L, "name", "surnama2", "email2", "Date2", "username2", "password2", UserRole.CLIENT));

        userDto = userDtos.get(0);

        CategoryPCJpaEntity categoryJpa = new CategoryPCJpaEntity(1L, "category", "slug", BigDecimal.TEN);
        CategoryPCDto categoryDto = new CategoryPCDto(1L, "category", "slug", BigDecimal.TEN);

        pcJpaEntities = List.of(
                new PCJpaEntity(1L, "label", "slug", 10, "specs", "2023-01-01", "image", categoryJpa),
                new PCJpaEntity(2L, "label", "slug", 10, "specs", "2023-01-01", "image", categoryJpa));

        pcDtos = List.of(
                new PCDto(1L, "label", "slug", 10, "specs", "2023-01-01", "image", categoryDto),
                new PCDto(2L, "label", "slug", 10, "specs", "2023-01-01", "image", categoryDto));

        pcDto = pcDtos.get(0);

        bookingJpaEntities = List.of(
                new BookingJpaEntity(1L, 2, userJpaEntities.get(0), pcJpaEntities.get(0), null),
                new BookingJpaEntity(2L, 4, userJpaEntities.get(1), pcJpaEntities.get(1), null));

        bookingDtos = List.of(
                new BookingDto(1L, 2, userDtos.get(0), pcDtos.get(0), null),
                new BookingDto(2L, 4, userDtos.get(1), pcDtos.get(1), null));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver una página de BookingDto cuando se solicita paginación")
        void shouldReturnPageOfBookingDto() {
            int page = 1;
            int size = 10;
            when(bookingJpaDao.findAll(page, size)).thenReturn(bookingJpaEntities);
            when(bookingJpaDao.count()).thenReturn((long) bookingJpaEntities.size());

            Page<BookingDto> result = bookingRepository.findAll(page, size);

            assertAll("Verificación de la página devuelta",
                    () -> assertNotNull(result),
                    () -> assertEquals(bookingJpaEntities.size(), result.data().size()),
                    () -> assertEquals(page, result.pageNumber()),
                    () -> assertEquals(size, result.pageSize()),
                    () -> assertEquals(bookingJpaEntities.size(), result.totalElements()));
        }

        @Test
        @DisplayName("Debería devolver una lista de BookingDto cuando se solicitan todas las reservas")
        void shouldReturnListOfBookingDto() {
            when(bookingJpaDao.findAll()).thenReturn(bookingJpaEntities);

            List<BookingDto> result = bookingRepository.findAll();

            assertAll("Verificación de la lista devuelta",
                    () -> assertNotNull(result),
                    () -> assertEquals(bookingJpaEntities.size(), result.size()));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver un Optional con BookingDto cuando el ID existe")
        void shouldReturnOptionalWithBookingDtoWhenExists() {
            Long id = 1L;
            when(bookingJpaDao.findById(id)).thenReturn(Optional.of(bookingJpaEntities.get(0)));

            Optional<BookingDto> result = bookingRepository.findById(id);

            assertAll("Verificación del Optional devuelto",
                    () -> assertTrue(result.isPresent()),
                    () -> assertEquals(id, result.get().id()));
        }

        @Test
        @DisplayName("Debería devolver un Optional vacío cuando el ID no existe")
        void shouldReturnEmptyOptionalWhenDoesNotExist() {
            Long id = 999L;
            when(bookingJpaDao.findById(id)).thenReturn(Optional.empty());

            Optional<BookingDto> result = bookingRepository.findById(id);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método save")
    class SaveTests {

        @Test
        @DisplayName("Debería insertar la reserva cuando el ID es nulo")
        void shouldInsertBookingWhenIdIsNull() {
            BookingDto bookingDtoToSave = new BookingDto(null, 2, userDto, pcDto, null);
            BookingJpaEntity savedJpaEntity = bookingJpaEntities.get(0);

            when(bookingJpaDao.insert(any(BookingJpaEntity.class))).thenReturn(savedJpaEntity);

            BookingDto result = bookingRepository.save(bookingDtoToSave);

            assertAll("Verificación de la inserción",
                    () -> assertNotNull(result),
                    () -> assertEquals(savedJpaEntity.getId(), result.id()),
                    () -> verify(bookingJpaDao, times(1)).insert(any(BookingJpaEntity.class)),
                    () -> verify(bookingJpaDao, never()).update(any(BookingJpaEntity.class)));
        }

        @Test
        @DisplayName("Debería actualizar la reserva cuando el ID no es nulo")
        void shouldUpdateBookingWhenIdIsNotNull() {
            BookingDto bookingDtoToUpdate = bookingDtos.get(0);
            BookingJpaEntity updatedJpaEntity = bookingJpaEntities.get(0);

            when(bookingJpaDao.update(any(BookingJpaEntity.class))).thenReturn(updatedJpaEntity);

            BookingDto result = bookingRepository.save(bookingDtoToUpdate);

            assertAll("Verificación de la actualización",
                    () -> assertNotNull(result),
                    () -> assertEquals(updatedJpaEntity.getId(), result.id()),
                    () -> verify(bookingJpaDao, times(1)).update(any(BookingJpaEntity.class)),
                    () -> verify(bookingJpaDao, never()).insert(any(BookingJpaEntity.class)));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {

        @Test
        @DisplayName("Debería llamar al repositorio para borrar la reserva por ID")
        void shouldDeleteBooking() {
            Long id = 1L;
            doNothing().when(bookingJpaDao).deleteById(id);

            bookingRepository.deleteById(id);

            verify(bookingJpaDao, times(1)).deleteById(id);
        }
    }
}
