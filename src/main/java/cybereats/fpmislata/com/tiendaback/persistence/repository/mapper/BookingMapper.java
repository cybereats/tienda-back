package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;

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

    public BookingDto fromBookingJpaEntityToBookingDto(BookingJpaEntity bookingJpaEntity) {
        if (bookingJpaEntity == null) {
            return null;
        }

        return new BookingDto(
                bookingJpaEntity.getId(),
                bookingJpaEntity.getHours(),
                UserMapper.getInstance().fromUserJpaEntityToUserDto(bookingJpaEntity.getUserJpaEntity()),
                PCMapper.getInstance().fromPCJpaEntityToPCDto(bookingJpaEntity.getPcJpaEntity()));
    }

    public BookingJpaEntity fromBookingDtoToBookingJpaEntity(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return new BookingJpaEntity(
                bookingDto.id(),
                bookingDto.hours(),
                UserMapper.getInstance().fromUserDtoToUserJpaEntity(bookingDto.user()),
                PCMapper.getInstance().fromPCDtoToPCJpaEntity(bookingDto.pc()));
    }
}
