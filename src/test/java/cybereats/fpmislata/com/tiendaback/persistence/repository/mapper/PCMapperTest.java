package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PCMapperTest {

    private final PCMapper mapper = PCMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de PCJpaEntity a PCDto")
    void shouldMapEntityToDto() {
        CategoryPCJpaEntity categoryEntity = new CategoryPCJpaEntity(1L, "Category", "category",
                new BigDecimal("10.00"));
        PCJpaEntity entity = new PCJpaEntity(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png",
                "AVAILABLE", categoryEntity);

        PCDto dto = mapper.fromPCJpaEntityToPCDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.id());
        assertEquals(entity.getLabel(), dto.label());
        assertEquals(entity.getSlug(), dto.slug());
        assertEquals(entity.getRuntime(), dto.runtime());
        assertEquals(entity.getSpecs(), dto.specs());
        assertEquals(entity.getWorkingSince(), dto.workingSince());
        assertEquals(entity.getImage(), dto.image());
        assertEquals(entity.getStatus(), dto.status().name());
        assertEquals(entity.getCategory().getId(), dto.categoryPCDto().id());
    }

    @Test
    @DisplayName("Debería mapear de PCDto a PCJpaEntity")
    void shouldMapDtoToEntity() {
        CategoryPCDto categoryDto = new CategoryPCDto(1L, "Category", "category", new BigDecimal("10.00"));
        PCDto dto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png", PCStatus.AVAILABLE,
                categoryDto);

        PCJpaEntity entity = mapper.fromPCDtoToPCJpaEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.id(), entity.getId());
        assertEquals(dto.label(), entity.getLabel());
        assertEquals(dto.slug(), entity.getSlug());
        assertEquals(dto.runtime(), entity.getRuntime());
        assertEquals(dto.specs(), entity.getSpecs());
        assertEquals(dto.workingSince(), entity.getWorkingSince());
        assertEquals(dto.image(), entity.getImage());
        assertEquals(dto.status().name(), entity.getStatus());
        assertEquals(dto.categoryPCDto().id(), entity.getCategory().getId());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromPCJpaEntityToPCDto(null));
        assertNull(mapper.fromPCDtoToPCJpaEntity(null));
    }
}
