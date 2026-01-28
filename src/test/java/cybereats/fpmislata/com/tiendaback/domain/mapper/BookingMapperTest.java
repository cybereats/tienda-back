package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Booking;
import cybereats.fpmislata.com.tiendaback.domain.model.PC;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingMapperTest {

    private final BookingMapper mapper = BookingMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de Booking a BookingDto")
    void shouldMapToDto() {
        User user = new User.Builder().id(1L).name("Name").build();
        PC pc = new PC.Builder().id(1L).label("PC 1").build();
        Booking booking = new Booking.Builder()
                .id(1L)
                .hours(2)
                .user(user)
                .pc(pc)
                .createdAt(LocalDateTime.now())
                .build();

        BookingDto dto = mapper.fromBookingToBookingDto(booking);

        assertNotNull(dto);
        assertEquals(booking.getId(), dto.id());
        assertEquals(booking.getHours(), dto.hours());
        assertEquals(booking.getUser().getId(), dto.user().id());
        assertEquals(booking.getPc().getId(), dto.pc().id());
        assertEquals(booking.getCreatedAt(), dto.createdAt());
    }

    @Test
    @DisplayName("Debería mapear de BookingDto a Booking")
    void shouldMapToDomain() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                UserRole.CLIENT);
        CategoryPCDto categoryDto = new CategoryPCDto(1L, "Category", "category", new BigDecimal("10.00"));
        PCDto pcDto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png",
                cybereats.fpmislata.com.tiendaback.domain.model.PCStatus.AVAILABLE, categoryDto);
        BookingDto dto = new BookingDto(1L, 2, userDto, pcDto, LocalDateTime.now());

        Booking booking = mapper.fromBookingDtoToBooking(dto);

        assertNotNull(booking);
        assertEquals(dto.id(), booking.getId());
        assertEquals(dto.hours(), booking.getHours());
        assertEquals(dto.user().id(), booking.getUser().getId());
        assertEquals(dto.pc().id(), booking.getPc().getId());
        assertEquals(dto.createdAt(), booking.getCreatedAt());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromBookingToBookingDto(null));
        assertNull(mapper.fromBookingDtoToBooking(null));
    }
}
