package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryPCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.PCResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PCMapperTest {

    private final PCMapper mapper = PCMapper.getInstance();

    @Test
    @DisplayName("Debería mapear de PCRequest a PCDto")
    void shouldMapRequestToDto() {
        CategoryPCRequest categoryRequest = new CategoryPCRequest(1L, "Category", "category", new BigDecimal("10.00"));
        PCRequest request = new PCRequest(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png", "AVAILABLE",
                categoryRequest);

        PCDto dto = mapper.fromPCRequestToPCDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.label(), dto.label());
        assertEquals(request.slug(), dto.slug());
        assertEquals(request.runtime(), dto.runtime());
        assertEquals(request.specs(), dto.specs());
        assertEquals(request.workingSince(), dto.workingSince());
        assertEquals(request.image(), dto.image());
        assertEquals(request.categoryPCRequest().id(), dto.categoryPCDto().id());
    }

    @Test
    @DisplayName("Debería mapear de PCDto a PCResponse")
    void shouldMapDtoToResponse() {
        CategoryPCDto categoryDto = new CategoryPCDto(1L, "Category", "category", new BigDecimal("10.00"));
        PCDto dto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png",
                cybereats.fpmislata.com.tiendaback.domain.model.PCStatus.AVAILABLE, categoryDto);

        PCResponse response = mapper.fromPCDtoToPCResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.label(), response.label());
        assertEquals(dto.slug(), response.slug());
        assertEquals(dto.runtime(), response.runtime());
        assertEquals(dto.specs(), response.specs());
        assertEquals(dto.workingSince(), response.workingSince());
        assertEquals(dto.image(), response.image());
        assertEquals(dto.categoryPCDto().id(), response.categoryPCResponse().id());
    }

    @Test
    @DisplayName("Debería devolver null si el input es null")
    void shouldReturnNullIfInputIsNull() {
        assertNull(mapper.fromPCRequestToPCDto(null));
        assertNull(mapper.fromPCDtoToPCResponse(null));
    }
}
