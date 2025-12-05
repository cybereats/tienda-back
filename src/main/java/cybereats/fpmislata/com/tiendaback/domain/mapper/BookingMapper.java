package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Booking;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;

public class BookingMapper {
    private static BookingMapper INSTANCE;

    private BookingMapper() {
    }

    public static BookingMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingMapper();
        }
        return INSTANCE;
    }

    public BookingDto fromBookingToBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        return new BookingDto(
                booking.getId(),
                booking.getHours(),
                UserMapper.getInstance().fromUserToUserDto(booking.getUser()),
                PCMapper.getInstance().fromPCToPCDto(booking.getPc()));
    }

    public Booking fromBookingDtoToBooking(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return new Booking.Builder()
                .id(bookingDto.id())
                .hours(bookingDto.hours())
                .user(UserMapper.getInstance().fromUserDtoToUser(bookingDto.user()))
                .pc(PCMapper.getInstance().fromPCDtoToPC(bookingDto.pc()))
                .build();
    }
}
