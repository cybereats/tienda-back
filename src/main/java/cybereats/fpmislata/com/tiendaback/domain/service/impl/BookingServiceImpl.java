package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.mapper.BookingMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.BookingRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.BookingService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;

import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Page<BookingDto> getAll(int page, int size) {
        Page<BookingDto> bookingDtoPage = bookingRepository.findAll(page, size);

        return new Page<>(
                bookingDtoPage.data(),
                bookingDtoPage.pageNumber(),
                bookingDtoPage.pageSize(),
                bookingDtoPage.totalElements()
        );
    }

    @Override
    public BookingDto getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public Optional<BookingDto> findById(Long id) {
        return Optional.ofNullable(bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found")));
    }

    @Override
    public BookingDto create(BookingDto bookingDto) {
        return bookingRepository.save(bookingDto);
    }

    @Override
    public BookingDto update(BookingDto bookingDto) {
        return bookingRepository.save(bookingDto);
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
