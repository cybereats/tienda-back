package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.BookingService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.BookingMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.BookingRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.BookingResponse;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import cybereats.fpmislata.com.tiendaback.security.AllowedRoles;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    @AllowedRoles(UserRole.ADMIN)
    public ResponseEntity<Page<BookingResponse>> findAllBookings(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Page<BookingDto> bookingDtoPage = bookingService.findAll(page, size);

        List<BookingResponse> bookingResponses = bookingDtoPage.data().stream()
                .map(bookingDto -> BookingMapper.getInstance().fromBookingDtoToBookingResponse(bookingDto))
                .toList();

        Page<BookingResponse> bookingPage = new Page<>(
                bookingResponses,
                bookingDtoPage.pageNumber(),
                bookingDtoPage.pageSize(),
                bookingDtoPage.totalElements());

        return new ResponseEntity<>(bookingPage, HttpStatus.OK);
    }

    @GetMapping("/my-active")
    @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
    public ResponseEntity<List<BookingResponse>> getMyActiveBookings(HttpServletRequest request) {
        Long userId = extractUserId(request);
        List<BookingResponse> bookingResponses = bookingService.findActiveByUserId(userId).stream()
                .map(bookingDto -> BookingMapper.getInstance().fromBookingDtoToBookingResponse(bookingDto))
                .toList();
        return new ResponseEntity<>(bookingResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @AllowedRoles({ UserRole.ADMIN, UserRole.CLIENT })
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse bookingResponse = BookingMapper.getInstance()
                .fromBookingDtoToBookingResponse(bookingService.getById(id));
        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }

    @PostMapping
    @AllowedRoles({ UserRole.CLIENT, UserRole.ADMIN })
    public ResponseEntity<BookingResponse> createBooking(
            HttpServletRequest request,
            @RequestBody BookingRequest bookingRequest) {
        try {
            Long userId = extractUserId(request);
            System.out.println("=== CREATE BOOKING ===");
            System.out.println("UserId: " + userId);
            System.out.println("PcId: " + bookingRequest.pcId());
            System.out.println("Hours: " + bookingRequest.hours());

            BookingDto bookingDto = BookingMapper.getInstance().fromBookingRequestToBookingDto(bookingRequest, userId);
            DtoValidator.validate(bookingDto);
            BookingDto createdBooking = bookingService.create(bookingDto);
            System.out.println("Booking created successfully: " + createdBooking.id());
            return new ResponseEntity<>(BookingMapper.getInstance().fromBookingDtoToBookingResponse(createdBooking),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating booking: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @PutMapping("/{id}")
    @AllowedRoles(UserRole.CLIENT)
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable("id") Long id,
            @RequestBody BookingRequest bookingRequest) {
        if (!id.equals(bookingRequest.id())) {
            throw new IllegalArgumentException("ID in path and request body must match");
        }
        BookingDto bookingDto = BookingMapper.getInstance().fromBookingRequestToBookingDto(bookingRequest);
        DtoValidator.validate(bookingDto);
        BookingDto updatedBooking = bookingService.update(bookingDto);
        return new ResponseEntity<>(BookingMapper.getInstance().fromBookingDtoToBookingResponse(updatedBooking),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @AllowedRoles({ UserRole.ADMIN, UserRole.CLIENT })
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Long extractUserId(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            return JwtUtil.extractUserId(token);
        }
        throw new IllegalArgumentException("Invalid authorization header");
    }
}
