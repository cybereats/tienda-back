package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;

import java.util.Optional;

public interface BookingService {
    Page<BookingDto> getAll(int page, int size);
    BookingDto getById(Long id);
    Optional<BookingDto> findById(Long id);
    BookingDto create(BookingDto bookingDto);
    BookingDto update(BookingDto bookingDto);
    void deleteById(Long id);
}
