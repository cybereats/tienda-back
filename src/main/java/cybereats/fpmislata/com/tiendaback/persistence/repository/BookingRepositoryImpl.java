package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.BookingRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.BookingJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.BookingMapper;

import java.util.List;
import java.util.Optional;

public class BookingRepositoryImpl implements BookingRepository {
    private final BookingJpaDao bookingJpaDao;

    public BookingRepositoryImpl(BookingJpaDao bookingJpaDao) {
        this.bookingJpaDao = bookingJpaDao;
    }

    @Override
    public Page<BookingDto> findAll(int page, int size) {
        List<BookingDto> content = bookingJpaDao.findAll(page, size).stream()
                .map(bookingJpaEntity -> BookingMapper.getInstance().fromBookingJpaEntityToBookingDto(bookingJpaEntity))
                .toList();
        long totalElements = bookingJpaDao.count();
        return new Page<>(content, page, size, totalElements);
    }

    @Override
    public List<BookingDto> findAll() {
        return bookingJpaDao.findAll().stream()
                .map(bookingJpaEntity -> BookingMapper.getInstance().fromBookingJpaEntityToBookingDto(bookingJpaEntity))
                .toList();
    }

    @Override
    public Optional<BookingDto> findById(Long id) {
        return bookingJpaDao.findById(id)
                .map(bookingJpaEntity -> BookingMapper.getInstance().fromBookingJpaEntityToBookingDto(bookingJpaEntity));
    }

    @Override
    public BookingDto save(BookingDto bookingDto) {
        BookingJpaEntity bookingJpaEntity = BookingMapper.getInstance().fromBookingDtoToBookingJpaEntity(bookingDto);

        if(bookingDto.id() == null) {
            return BookingMapper.getInstance().fromBookingJpaEntityToBookingDto(bookingJpaDao.insert(bookingJpaEntity));
        }

        return BookingMapper.getInstance().fromBookingJpaEntityToBookingDto(bookingJpaDao.update(bookingJpaEntity));
    }

    @Override
    public void deleteById(Long id) {
        bookingJpaDao.deleteById(id);
    }
}
