package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Booking;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;

public class BookingMapper {
    private static BookingMapper INSTANCE;

    private BookingMapper() {}

    public static BookingMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingMapper();
        }
        return INSTANCE;
    }

    public BookingDto bookingToBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        return new BookingDto(
                booking.getId(), booking.getHours()
        );
    }

    public Booking bookingDtoToBooking(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return new Booking.Builder()
                .id(bookingDto.id())
                .hours(bookingDto.hours())
                .build();
    }
}
