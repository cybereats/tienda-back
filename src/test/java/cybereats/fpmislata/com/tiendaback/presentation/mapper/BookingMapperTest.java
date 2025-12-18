package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.BookingRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.BookingResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingMapperTest {

    private final BookingMapper mapper = BookingMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de BookingRequest a BookingDto")
    void shouldMapRequestToDto() {
        BookingRequest request = new BookingRequest(1L, 2, 10L, 20L);

        BookingDto dto = mapper.fromBookingRequestToBookingDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.hours(), dto.hours());
        assertEquals(request.userId(), dto.user().id());
        assertEquals(request.pcId(), dto.pc().id());
    }

    @Test
    @DisplayName("Debería mapear de BookingDto a BookingResponse")
    void shouldMapDtoToResponse() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                UserRole.CLIENT);
        CategoryPCDto categoryPCDto = new CategoryPCDto(1L, "Category", "category", BigDecimal.valueOf(100));
        PCDto pcDto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png", categoryPCDto);
        BookingDto dto = new BookingDto(1L, 2, userDto, pcDto, LocalDateTime.now());

        BookingResponse response = mapper.fromBookingDtoToBookingResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.hours(), response.hours());
        assertEquals(dto.user().id(), response.user().id());
        assertEquals(dto.pc().id(), response.pc().id());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromBookingRequestToBookingDto(null));
        assertNull(mapper.fromBookingDtoToBookingResponse(null));
    }
}
