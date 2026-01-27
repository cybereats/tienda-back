package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.BookingRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.BookingService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final PCRepository pcRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
            PCRepository pcRepository) {
        this.bookingRepository = bookingRepository;
        this.pcRepository = pcRepository;
    }

    @Override
    public Page<BookingDto> findAll(int page, int size) {
        if (page < 1) {
            throw new BusinessException("Page number cannot be less than 1");
        }
        if (size <= 0) {
            throw new BusinessException("Page size must be greater than 0");
        }

        Page<BookingDto> bookingDtoPage = bookingRepository.findAll(page, size);

        return new Page<>(
                bookingDtoPage.data(),
                bookingDtoPage.pageNumber(),
                bookingDtoPage.pageSize(),
                bookingDtoPage.totalElements());
    }

    @Override
    public BookingDto getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    @Override
    public Optional<BookingDto> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    @Transactional
    public BookingDto create(BookingDto bookingDto) {
        Optional<BookingDto> bookingDtoOptional = bookingRepository.findById(bookingDto.id());
        if (bookingDtoOptional.isPresent()) {
            throw new BusinessException("Booking already exists");
        }

        PCDto pcDto = bookingDto.pc();
        PCDto updatedPcDto = new PCDto(
                pcDto.id(),
                pcDto.label(),
                pcDto.slug(),
                pcDto.runtime(),
                pcDto.specs(),
                pcDto.workingSince(),
                pcDto.image(),
                PCStatus.OCCUPIED,
                pcDto.categoryPCDto());
        pcRepository.save(updatedPcDto);

        return bookingRepository.save(bookingDto);
    }

    @Override
    @Transactional
    public BookingDto update(BookingDto bookingDto) {
        Optional<BookingDto> bookingDtoOptional = bookingRepository.findById(bookingDto.id());
        if (bookingDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Booking not found");
        }
        return bookingRepository.save(bookingDto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<BookingDto> bookingDtoOptional = bookingRepository.findById(id);
        if (bookingDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }
}
