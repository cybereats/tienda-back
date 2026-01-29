package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<BookingDto> findAll();

    Page<BookingDto> findAll(int page, int size);

    Optional<BookingDto> findById(Long id);

    BookingDto save(BookingDto bookingDto);

    void deleteById(Long id);

    boolean hasActiveBooking(Long userId);

    List<BookingDto> findActiveByUserId(Long userId);
}
