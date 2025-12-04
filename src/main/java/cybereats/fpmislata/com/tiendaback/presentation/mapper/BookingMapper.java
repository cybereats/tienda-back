package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.BookingRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.BookingResponse;

public class BookingMapper {
    private static BookingMapper INSTANCE;

    private BookingMapper() {}

    public static BookingMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingMapper();
        }
        return INSTANCE;
    }
    
    public BookingDto bookingRequestToBookingDto(BookingRequest bookingRequest) {
        if (bookingRequest == null) {
            return null;
        }

        return new BookingDto(
            bookingRequest.id(),
            bookingRequest.hours()
        );
    }

    public BookingResponse bookingDtoToBookingResponse(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return new BookingResponse(
            bookingDto.id(),
            bookingDto.hours()
        );
    }
}
