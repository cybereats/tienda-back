package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.BookingDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.BookingJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingMapperTest {

    private final BookingMapper mapper = BookingMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de BookingJpaEntity a BookingDto")
    void shouldMapEntityToDto() {
        UserJpaEntity userEntity = new UserJpaEntity(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user",
                "pass", UserRole.CLIENT);
        CategoryPCJpaEntity categoryEntity = new CategoryPCJpaEntity(1L, "Category", "category",
                new BigDecimal("10.00"));
        PCJpaEntity pcEntity = new PCJpaEntity(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png",
                categoryEntity);
        BookingJpaEntity entity = new BookingJpaEntity(1L, 2, userEntity, pcEntity, LocalDateTime.now());

        BookingDto dto = mapper.fromBookingJpaEntityToBookingDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.id());
        assertEquals(entity.getHours(), dto.hours());
        assertEquals(entity.getUserJpaEntity().getId(), dto.user().id());
        assertEquals(entity.getPcJpaEntity().getId(), dto.pc().id());
        assertEquals(entity.getCreatedAt(), dto.createdAt());
    }

    @Test
    @DisplayName("Debería mapear de BookingDto a BookingJpaEntity")
    void shouldMapDtoToEntity() {
        UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                UserRole.CLIENT);
        CategoryPCDto categoryDto = new CategoryPCDto(1L, "Category", "category", new BigDecimal("10.00"));
        PCDto pcDto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png", categoryDto);
        BookingDto dto = new BookingDto(1L, 2, userDto, pcDto, LocalDateTime.now());

        BookingJpaEntity entity = mapper.fromBookingDtoToBookingJpaEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.hours(), entity.getHours());
        assertEquals(dto.user().id(), entity.getUserJpaEntity().getId());
        assertEquals(dto.pc().id(), entity.getPcJpaEntity().getId());
        assertEquals(dto.createdAt(), entity.getCreatedAt());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromBookingJpaEntityToBookingDto(null));
        assertNull(mapper.fromBookingDtoToBookingJpaEntity(null));
    }
}
