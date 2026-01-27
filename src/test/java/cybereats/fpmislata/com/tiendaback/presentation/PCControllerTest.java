package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.PCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryPCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PCRequest;
import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PCController.class)
class PCControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PCService pcService;

    private PCDto pcDto;
    private PCRequest pcRequest;

    @BeforeEach
    void setUp() {
        CategoryPCDto categoryDto = new CategoryPCDto(2L, "Gaming", "gaming", new BigDecimal("50.00"));
        pcDto = new PCDto(1L, "PC Gamer 1", "pc-gamer-1", 12, "Specs", "2023-01-01", "image.jpg", PCStatus.AVAILABLE,
                categoryDto);

        CategoryPCRequest categoryRequest = new CategoryPCRequest(2L, "Gaming", "gaming", new BigDecimal("50.00"));
        pcRequest = new PCRequest(1L, "PC Gamer 1", "pc-gamer-1", 12, "Specs", "2023-01-01", "image.jpg", "AVAILABLE",
                categoryRequest);
    }

    @Nested
    @DisplayName("GET /api/pcs")
    class GetAllPCsTests {
        @Test
        @DisplayName("Debería devolver una página de PCs")
        void shouldReturnPageOfPCs() throws Exception {
            Page<PCDto> page = new Page<>(List.of(pcDto), 1, 10, 1L);
            when(pcService.findAll(1, 10)).thenReturn(page);

            mockMvc.perform(get("/api/pcs")
                    .param("page", "1")
                    .param("size", "10"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data[0].slug").value("pc-gamer-1"))
                    .andExpect(jsonPath("$.totalElements").value(1));
        }
    }

    @Nested
    @DisplayName("GET /api/pcs/all")
    class GetAllPCsWithoutPaginationTests {
        @Test
        @DisplayName("Debería devolver la lista de todos los PCs")
        void shouldReturnListOfAllPCs() throws Exception {
            when(pcService.findAll()).thenReturn(List.of(pcDto));

            mockMvc.perform(get("/api/pcs/all"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].slug").value("pc-gamer-1"))
                    .andExpect(jsonPath("$.length()").value(1));
        }
    }

    @Nested
    @DisplayName("GET /api/pcs/{slug}")
    class GetPCBySlugTests {
        @Test
        @DisplayName("Debería devolver el PC por slug")
        void shouldReturnPCBySlug() throws Exception {
            when(pcService.getBySlug("pc-gamer-1")).thenReturn(pcDto);

            mockMvc.perform(get("/api/pcs/pc-gamer-1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.slug").value("pc-gamer-1"));
        }
    }

    @Nested
    @DisplayName("POST /api/pcs")
    class CreatePCTests {
        @Test
        @DisplayName("Debería crear un nuevo PC")
        void shouldCreatePC() throws Exception {
            when(pcService.create(any(PCDto.class))).thenReturn(pcDto);

            mockMvc.perform(post("/api/pcs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pcRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.slug").value("pc-gamer-1"));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando el cuerpo está vacío")
        void shouldReturn400WhenBodyIsEmpty() throws Exception {
            mockMvc.perform(post("/api/pcs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("PUT /api/pcs/{slug}")
    class UpdatePCTests {
        @Test
        @DisplayName("Debería actualizar un PC")
        void shouldUpdatePC() throws Exception {
            when(pcService.update(any(PCDto.class))).thenReturn(pcDto);

            mockMvc.perform(put("/api/pcs/pc-gamer-1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pcRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.slug").value("pc-gamer-1"));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando los slugs no coinciden")
        void shouldReturn400WhenSlugsMismatch() throws Exception {
            mockMvc.perform(put("/api/pcs/different-slug")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pcRequest)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("DELETE /api/pcs/{slug}")
    class DeletePCTests {
        @Test
        @DisplayName("Debería eliminar un PC")
        void shouldDeletePC() throws Exception {
            mockMvc.perform(delete("/api/pcs/pc-gamer-1"))
                    .andExpect(status().isNoContent());
        }
    }
}
