package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.CategoryPC;
import cybereats.fpmislata.com.tiendaback.domain.model.PC;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PCMapperTest {

    private final PCMapper mapper = PCMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de PC a PCDto")
    void shouldMapToDto() {
        CategoryPC category = new CategoryPC.Builder().id(1L).label("Category").build();
        PC pc = new PC.Builder()
                .id(1L)
                .label("PC 1")
                .slug("pc-1")
                .runtime(100)
                .specs("Specs")
                .workingSince("2020-01-01")
                .image("image.png")
                .status(PCStatus.AVAILABLE)
                .categoryPC(category)
                .build();

        PCDto dto = mapper.fromPCToPCDto(pc);

        assertNotNull(dto);
        assertEquals(pc.getId(), dto.id());
        assertEquals(pc.getLabel(), dto.label());
        assertEquals(pc.getSlug(), dto.slug());
        assertEquals(pc.getRuntime(), dto.runtime());
        assertEquals(pc.getSpecs(), dto.specs());
        assertEquals(pc.getWorkingSince(), dto.workingSince());
        assertEquals(pc.getImage(), dto.image());
        assertEquals(pc.getStatus(), dto.status());
        assertEquals(pc.getCategoryPC().getId(), dto.categoryPCDto().id());
    }

    @Test
    @DisplayName("Debería mapear de PCDto a PC")
    void shouldMapToDomain() {
        CategoryPCDto categoryDto = new CategoryPCDto(1L, "Category", "category", new BigDecimal("10.00"));
        PCDto dto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png", PCStatus.AVAILABLE,
                categoryDto);

        PC pc = mapper.fromPCDtoToPC(dto);

        assertNotNull(pc);
        assertEquals(dto.id(), pc.getId());
        assertEquals(dto.label(), pc.getLabel());
        assertEquals(dto.slug(), pc.getSlug());
        assertEquals(dto.runtime(), pc.getRuntime());
        assertEquals(dto.specs(), pc.getSpecs());
        assertEquals(dto.workingSince(), pc.getWorkingSince());
        assertEquals(dto.image(), pc.getImage());
        assertEquals(dto.status(), pc.getStatus());
        assertEquals(dto.categoryPCDto().id(), pc.getCategoryPC().getId());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromPCToPCDto(null));
        assertNull(mapper.fromPCDtoToPC(null));
    }
}
