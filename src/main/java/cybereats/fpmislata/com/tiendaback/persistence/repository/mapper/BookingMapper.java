package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;

public class BookingMapper {
    private static BookingMapper INSTANCE;

    private BookingMapper() {}

    public static BookingMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingMapper();
        }
        return INSTANCE;
    }

    public BookingDto bookingJpaEntityToBookingDto(BookingJpaEntity bookingJpaEntity) {
        if (bookingJpaEntity == null) {
            return null;
        }

        return new BookingDto(
            bookingJpaEntity.getId(),
            bookingJpaEntity.getHours()
        );
    }

    public BookingJpaEntity bookingDtoToBookingJpaEntity(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return new BookingJpaEntity(
            bookingDto.id(),
            bookingDto.hours()
        );
    }
}
