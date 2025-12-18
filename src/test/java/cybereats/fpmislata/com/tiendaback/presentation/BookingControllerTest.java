package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.BookingService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.BookingRequest;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto userDto;
    private PCDto pcDto;
    private BookingDto bookingDto;
    private String adminToken;
    private String clientToken;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1L, "Ismael", "Surname", "isma@test.com", "2000-01-01", "isma", "pass", UserRole.CLIENT);
        CategoryPCDto categoryDto = new CategoryPCDto(1L, "Gaming", "gaming", new BigDecimal("50.0"));
        pcDto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2023-01-01", "image.jpg", categoryDto);
        bookingDto = new BookingDto(1L, 5, userDto, pcDto, LocalDateTime.now());

        User adminUser = new User.Builder()
                .id(1L)
                .username("admin")
                .role(UserRole.ADMIN)
                .build();
        adminToken = JwtUtil.generateToken(adminUser);

        User clientUser = new User.Builder()
                .id(2L)
                .username("client")
                .role(UserRole.CLIENT)
                .build();
        clientToken = JwtUtil.generateToken(clientUser);
    }

    @Nested
    @DisplayName("Tests para el método findAllBookings")
    class FindAllBookingsTests {

        @Test
        @DisplayName("Debería devolver 200 y una página de reservas cuando los parámetros son válidos")
        void shouldReturnOkAndPage() throws Exception {
            Page<BookingDto> page = new Page<>(List.of(bookingDto), 1, 10, 1L);
            when(bookingService.findAll(1, 10)).thenReturn(page);

            mockMvc.perform(get("/api/bookings?page=1&size=10")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data[0].id").value(bookingDto.id()))
                    .andExpect(jsonPath("$.data[0].hours").value(bookingDto.hours()))
                    .andExpect(jsonPath("$.pageNumber").value(1))
                    .andExpect(jsonPath("$.totalElements").value(1));
        }

        @Test
        @DisplayName("Debería devolver 200 y una página vacía cuando no hay reservas")
        void shouldReturnOkAndEmptyPage() throws Exception {
            Page<BookingDto> emptyPage = new Page<>(List.of(), 1, 10, 0L);
            when(bookingService.findAll(1, 10)).thenReturn(emptyPage);

            mockMvc.perform(get("/api/bookings?page=1&size=10")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data").isEmpty())
                    .andExpect(jsonPath("$.totalElements").value(0));
        }
    }

    @Nested
    @DisplayName("Tests para el método getBookingById")
    class GetBookingByIdTests {

        @Test
        @DisplayName("Debería devolver 200 cuando el ID existe")
        void shouldReturnOkWhenExists() throws Exception {
            when(bookingService.getById(1L)).thenReturn(bookingDto);

            mockMvc.perform(get("/api/bookings/1")
                    .header("Authorization", "Bearer " + clientToken))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(bookingDto.id()))
                    .andExpect(jsonPath("$.hours").value(bookingDto.hours()));
        }

        @Test
        @DisplayName("Debería devolver 404 cuando el ID no existe")
        void shouldReturnNotFoundWhenDoesNotExist() throws Exception {
            when(bookingService.getById(1L)).thenThrow(new ResourceNotFoundException("Booking not found"));

            mockMvc.perform(get("/api/bookings/1")
                    .header("Authorization", "Bearer " + clientToken))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Tests para el método createBooking")
    class CreateBookingTests {

        @Test
        @DisplayName("Debería devolver 201 cuando la petición es válida")
        void shouldReturnCreatedWhenValid() throws Exception {
            BookingRequest request = new BookingRequest(null, 5, 1L, 1L);
            when(bookingService.create(any(BookingDto.class))).thenReturn(bookingDto);

            mockMvc.perform(post("/api/bookings")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(bookingDto.id()));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando falla la validación (horas < 1)")
        void shouldReturnBadRequestWhenInvalid() throws Exception {
            BookingRequest request = new BookingRequest(null, 0, 1L, 1L);

            mockMvc.perform(post("/api/bookings")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería devolver 400 cuando el JSON es inválido")
        void shouldReturnBadRequestWhenJsonIsInvalid() throws Exception {
            String invalidJson = "{ \"hours\": \"invalid\" }";

            mockMvc.perform(post("/api/bookings")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidJson))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Tests para el método updateBooking")
    class UpdateBookingTests {

        @Test
        @DisplayName("Debería devolver 200 cuando la petición es válida")
        void shouldReturnOkWhenValid() throws Exception {
            BookingRequest request = new BookingRequest(1L, 10, 1L, 1L);
            when(bookingService.update(any(BookingDto.class))).thenReturn(bookingDto);

            mockMvc.perform(put("/api/bookings/1")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(bookingDto.id()));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando los IDs no coinciden")
        void shouldReturnBadRequestWhenIdMismatch() throws Exception {
            BookingRequest request = new BookingRequest(2L, 10, 1L, 1L);

            mockMvc.perform(put("/api/bookings/1")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería devolver 404 cuando se intenta actualizar una reserva inexistente")
        void shouldReturnNotFoundWhenBookingDoesNotExist() throws Exception {
            BookingRequest request = new BookingRequest(1L, 10, 1L, 1L);
            when(bookingService.update(any(BookingDto.class)))
                    .thenThrow(new ResourceNotFoundException("Booking not found"));

            mockMvc.perform(put("/api/bookings/1")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteBooking")
    class DeleteBookingTests {

        @Test
        @DisplayName("Debería devolver 204 cuando el borrado es exitoso")
        void shouldReturnNoContent() throws Exception {
            doNothing().when(bookingService).deleteById(1L);

            mockMvc.perform(delete("/api/bookings/1")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Debería devolver 404 cuando el ID no existe")
        void shouldReturnNotFoundWhenDoesNotExist() throws Exception {
            doThrow(new ResourceNotFoundException("Booking not found")).when(bookingService).deleteById(1L);

            mockMvc.perform(delete("/api/bookings/1")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isNotFound());
        }
    }
}
