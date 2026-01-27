package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cybereats.fpmislata.com.tiendaback.domain.repository.BookingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository pcRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private List<BookingDto> bookings;
    private List<UserDto> userDtos;
    private List<PCDto> pcDtos;

    @BeforeEach
    void setUp() {
        userDtos = List.of(
                new UserDto(1L, "name", "surnama1", "email1", "Date1", "username1", "password1", UserRole.CLIENT),
                new UserDto(2L, "name", "surnama2", "email2", "Date2", "username2", "password2", UserRole.CLIENT));

        CategoryPCDto categoryDto = new CategoryPCDto(1L, "category", "slug", java.math.BigDecimal.TEN);

        pcDtos = List.of(
                new PCDto(1L, "label", "slug", 10, "specs", "2023-01-01", "image", PCStatus.AVAILABLE, categoryDto),
                new PCDto(2L, "label", "slug", 10, "specs", "2023-01-01", "image", PCStatus.AVAILABLE, categoryDto));

        bookings = List.of(
                new BookingDto(1L, 2, userDtos.get(0), pcDtos.get(0), null),
                new BookingDto(2L, 4, userDtos.get(1), pcDtos.get(1), null),
                new BookingDto(3L, 6, userDtos.get(0), pcDtos.get(0), null),
                new BookingDto(4L, 8, userDtos.get(1), pcDtos.get(1), null),
                new BookingDto(5L, 10, userDtos.get(0), pcDtos.get(0), null),
                new BookingDto(6L, 12, userDtos.get(1), pcDtos.get(1), null));
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {

        @Test
        @DisplayName("Debería devolver una página de BookingDto cuando los parámetros son válidos")
        void shouldReturnPageOfBookingDto() {
            int page = 1;
            int size = 10;
            Page<BookingDto> expectedPage = new Page<>(bookings, page, size, bookings.size());

            when(bookingRepository.findAll(page, size)).thenReturn(expectedPage);

            Page<BookingDto> result = bookingService.findAll(page, size);

            assertAll("Page metadata and data",
                    () -> assertNotNull(result),
                    () -> assertEquals(bookings, result.data()),
                    () -> assertEquals(page, result.pageNumber()),
                    () -> assertEquals(size, result.pageSize()),
                    () -> assertEquals(bookings.size(), result.totalElements()),
                    () -> assertEquals(bookings.get(0).user().username(), result.data().get(0).user().username()),
                    () -> assertEquals(bookings.get(0).pc().label(), result.data().get(0).pc().label()));
        }

        @Test
        @DisplayName("Debería devolver una página vacía cuando no hay reservas")
        void shouldReturnEmptyPage() {
            int page = 1;
            int size = 10;
            Page<BookingDto> expectedPage = new Page<>(List.of(), page, size, 0);

            when(bookingRepository.findAll(page, size)).thenReturn(expectedPage);

            Page<BookingDto> result = bookingService.findAll(page, size);

            assertAll("Empty page metadata",
                    () -> assertNotNull(result),
                    () -> assertEquals(0, result.data().size()),
                    () -> assertEquals(0, result.totalElements()));
        }

        @Test
        @DisplayName("Debería lanzar BusinessException cuando el número de página es inválido (menor a 1)")
        void shouldThrowExceptionWhenPageNumberIsInvalid() {
            int page = 0;
            int size = 10;

            assertThrows(BusinessException.class,
                    () -> bookingService.findAll(page, size));
        }

        @Test
        @DisplayName("Debería lanzar BusinessException cuando el tamaño de página es inválido (menor o igual a 0)")
        void shouldThrowExceptionWhenPageSizeIsInvalid() {
            int page = 1;
            int size = 0;

            assertThrows(BusinessException.class,
                    () -> bookingService.findAll(page, size));
        }
    }

    @Nested
    @DisplayName("Tests para el método getById")
    class GetByIdTests {

        @Test
        @DisplayName("Debería devolver el BookingDto cuando el ID existe")
        void shouldReturnBookingDtoWhenExists() {
            Long id = 1L;
            BookingDto bookingDto = bookings.get(0);
            when(bookingRepository.findById(id)).thenReturn(Optional.of(bookingDto));

            BookingDto result = bookingService.getById(id);

            assertAll("Valid booking retrieval",
                    () -> assertNotNull(result),
                    () -> assertEquals(bookingDto, result),
                    () -> assertEquals("username1", result.user().username()),
                    () -> assertEquals("label", result.pc().label()));
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando el ID no existe")
        void shouldThrowResourceNotFoundExceptionWhenDoesNotExist() {
            Long id = 999L;
            when(bookingRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> bookingService.getById(id));
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException cuando el ID es nulo")
        void shouldThrowResourceNotFoundExceptionWhenIdIsNull() {
            when(bookingRepository.findById(null)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> bookingService.getById(null));
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {

        @Test
        @DisplayName("Debería devolver un Optional con BookingDto cuando el ID existe")
        void shouldReturnOptionalWithBookingDtoWhenExists() {
            Long id = 1L;
            BookingDto bookingDto = bookings.get(0);
            when(bookingRepository.findById(id)).thenReturn(Optional.of(bookingDto));

            Optional<BookingDto> result = bookingService.findById(id);

            assertAll("Found booking",
                    () -> assertTrue(result.isPresent()),
                    () -> assertEquals(bookingDto, result.get()),
                    () -> assertEquals("username1", result.get().user().username()),
                    () -> assertEquals("label", result.get().pc().label()));
        }

        @Test
        @DisplayName("Debería devolver un Optional vacío cuando el ID no existe")
        void shouldReturnEmptyOptionalWhenDoesNotExist() {
            Long id = 999L;
            when(bookingRepository.findById(id)).thenReturn(Optional.empty());

            Optional<BookingDto> result = bookingService.findById(id);

            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Debería devolver un Optional vacío cuando el ID es nulo")
        void shouldReturnEmptyOptionalWhenIdIsNull() {
            when(bookingRepository.findById(null)).thenReturn(Optional.empty());

            Optional<BookingDto> result = bookingService.findById(null);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para el método create")
    class CreateTests {

        @Test
        @DisplayName("Debería crear y devolver la reserva cuando no existe previamente")
        void shouldCreateBooking() {
            BookingDto bookingDto = bookings.get(0);
            when(bookingRepository.findById(bookingDto.id())).thenReturn(Optional.empty());
            when(bookingRepository.save(bookingDto)).thenReturn(bookingDto);

            BookingDto result = bookingService.create(bookingDto);

            assertAll("Create booking success",
                    () -> assertNotNull(result),
                    () -> assertEquals(bookingDto, result));
            verify(pcRepository, times(1)).save(argThat(pc -> pc.status() == PCStatus.OCCUPIED));
        }

        @Test
        @DisplayName("Debería lanzar BusinessException cuando se intenta crear una reserva que ya existe")
        void shouldThrowBusinessExceptionWhenBookingAlreadyExists() {
            BookingDto bookingDto = bookings.get(0);
            when(bookingRepository.findById(bookingDto.id())).thenReturn(Optional.of(bookingDto));

            assertThrows(BusinessException.class,
                    () -> bookingService.create(bookingDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {

        @Test
        @DisplayName("Debería actualizar y devolver la reserva cuando existe")
        void shouldUpdateBooking() {
            BookingDto bookingDto = bookings.get(0);
            when(bookingRepository.findById(bookingDto.id())).thenReturn(Optional.of(bookingDto));
            when(bookingRepository.save(bookingDto)).thenReturn(bookingDto);

            BookingDto result = bookingService.update(bookingDto);

            assertAll("Update booking success",
                    () -> assertNotNull(result),
                    () -> assertEquals(bookingDto, result));
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException al intentar actualizar una reserva que no existe")
        void shouldThrowResourceNotFoundExceptionWhenBookingDoesNotExist() {
            BookingDto bookingDto = bookings.get(0);
            when(bookingRepository.findById(bookingDto.id())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> bookingService.update(bookingDto));
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteById")
    class DeleteTests {

        @Test
        @DisplayName("Debería borrar la reserva correctamente cuando el ID existe")
        void shouldDeleteBooking() {
            Long id = 1L;
            BookingDto bookingDto = bookings.get(0);
            when(bookingRepository.findById(id)).thenReturn(Optional.of(bookingDto));

            bookingService.deleteById(id);

            verify(bookingRepository, times(1)).deleteById(id);
        }

        @Test
        @DisplayName("Debería lanzar ResourceNotFoundException al intentar borrar una reserva que no existe")
        void shouldThrowResourceNotFoundExceptionWhenBookingDoesNotExist() {
            Long id = 999L;
            when(bookingRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> bookingService.deleteById(id));
        }
    }
}
