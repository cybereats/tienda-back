package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.BookingRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository;
import cybereats.fpmislata.com.tiendaback.domain.repository.UserRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.BookingService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final PCRepository pcRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
            PCRepository pcRepository,
            UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.pcRepository = pcRepository;
        this.userRepository = userRepository;
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
        PCDto pcDto = pcRepository.findById(bookingDto.pc().id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró el ordenador seleccionado. Es posible que haya sido eliminado."));

        if (pcDto.status() == PCStatus.OCCUPIED) {
            throw new BusinessException(
                    "Este ordenador ya está reservado por otro usuario. Por favor, elige otro equipo disponible.");
        } else if (pcDto.status() == PCStatus.MAINTENANCE) {
            throw new BusinessException("Este ordenador está en mantenimiento y no puede reservarse en este momento.");
        } else if (pcDto.status() != PCStatus.AVAILABLE) {
            throw new BusinessException("Este ordenador no está disponible para reservar.");
        }

        UserDto userDto = userRepository.findById(bookingDto.user().id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se pudo verificar tu cuenta de usuario. Por favor, cierra sesión e inicia de nuevo."));

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

        BookingDto completeBookingDto = new BookingDto(
                null,
                bookingDto.hours(),
                userDto,
                pcDto,
                null);

        DtoValidator.validate(completeBookingDto);

        return bookingRepository.save(completeBookingDto);
    }

    @Override
    @Transactional
    public BookingDto update(BookingDto bookingDto) {
        Optional<BookingDto> bookingDtoOptional = bookingRepository.findById(bookingDto.id());
        if (bookingDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Booking not found");
        }
        DtoValidator.validate(bookingDto);
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

    @Override
    public List<BookingDto> findActiveByUserId(Long userId) {
        return bookingRepository.findActiveByUserId(userId);
    }

    @Override
    public long getNextId() {
        return bookingRepository.getMaxId() + 1;
    }
}
