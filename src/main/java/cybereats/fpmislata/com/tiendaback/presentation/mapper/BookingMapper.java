package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.BookingRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.BookingResponse;

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

    public BookingDto fromBookingRequestToBookingDto(BookingRequest bookingRequest) {
        if (bookingRequest == null) {
            return null;
        }

        return new BookingDto(
                bookingRequest.id(),
                bookingRequest.hours(),
                mapUser(bookingRequest.userId()),
                mapPC(bookingRequest.pcId()),
                null);
    }

    public BookingResponse fromBookingDtoToBookingResponse(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return new BookingResponse(
                bookingDto.id(),
                bookingDto.hours(),
                UserMapper.getInstance().fromUserDtoToUserResponse(bookingDto.user()),
                PCMapper.getInstance().fromPCDtoToPCResponse(bookingDto.pc()));
    }

    public UserDto mapUser(Long id) {
        return new UserDto(id, null, null, null, null, null, null, null);
    }

    public PCDto mapPC(Long id) {
        return new PCDto(id, null, null, 0, null, null, null, null);
    }

}
