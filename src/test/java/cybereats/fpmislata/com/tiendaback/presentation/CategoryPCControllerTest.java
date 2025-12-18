package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.CategoryPCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryPCRequest;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryPCController.class)
public class CategoryPCControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CategoryPCService categoryPCService;

  @Autowired
  private ObjectMapper objectMapper;

  private CategoryPCDto categoryPCDto;

  @BeforeEach
  void setUp() {
    categoryPCDto = new CategoryPCDto(1L, "Gaming", "gaming", new BigDecimal("50.0"));
  }

  @Nested
  @DisplayName("Tests para el método findAllCategoryPCs")
  class FindAllCategoryPCsTests {

    @Test
    @DisplayName("Debería devolver 200 y una página de categorías cuando los parámetros son válidos")
    void shouldReturnOkAndPage() throws Exception {
      Page<CategoryPCDto> page = new Page<>(List.of(categoryPCDto), 1, 10, 1L);
      when(categoryPCService.findAll(1, 10)).thenReturn(page);

      mockMvc.perform(get("/api/categories-pc?page=1&size=10"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data[0].id").value(categoryPCDto.id()))
          .andExpect(jsonPath("$.data[0].label").value(categoryPCDto.label()))
          .andExpect(jsonPath("$.pageNumber").value(1))
          .andExpect(jsonPath("$.totalElements").value(1));
    }
  }

  @Nested
  @DisplayName("Tests para el método getCategoryPCById")
  class GetCategoryPCByIdTests {

    @Test
    @DisplayName("Debería devolver 200 cuando el ID existe")
    void shouldReturnOkWhenExists() throws Exception {
      when(categoryPCService.getById(1L)).thenReturn(categoryPCDto);

      mockMvc.perform(get("/api/categories-pc/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(categoryPCDto.id()))
          .andExpect(jsonPath("$.label").value(categoryPCDto.label()));
    }

    @Test
    @DisplayName("Debería devolver 404 cuando el ID no existe")
    void shouldReturnNotFoundWhenDoesNotExist() throws Exception {
      when(categoryPCService.getById(1L)).thenThrow(new ResourceNotFoundException("CategoryPC not found"));

      mockMvc.perform(get("/api/categories-pc/1"))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("Tests para el método getCategoryPCBySlug")
  class GetCategoryPCBySlugTests {

    @Test
    @DisplayName("Debería devolver 200 cuando el slug existe")
    void shouldReturnOkWhenExists() throws Exception {
      when(categoryPCService.getBySlug("gaming")).thenReturn(categoryPCDto);

      mockMvc.perform(get("/api/categories-pc/gaming"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.slug").value("gaming"));
    }
  }

  @Nested
  @DisplayName("Tests para el método createCategoryPC")
  class CreateCategoryPCTests {

    @Test
    @DisplayName("Debería devolver 201 cuando la petición es válida")
    void shouldReturnCreatedWhenValid() throws Exception {
      CategoryPCRequest request = new CategoryPCRequest(null, "Work", "work", new BigDecimal("30.0"));
      CategoryPCDto createdDto = new CategoryPCDto(2L, "Work", "work", new BigDecimal("30.0"));
      when(categoryPCService.create(any(CategoryPCDto.class))).thenReturn(createdDto);

      mockMvc.perform(post("/api/categories-pc")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(2L))
          .andExpect(jsonPath("$.label").value("Work"));
    }
  }

  @Nested
  @DisplayName("Tests para el método updateCategoryPC")
  class UpdateCategoryPCTests {

    @Test
    @DisplayName("Debería devolver 200 cuando la petición es válida")
    void shouldReturnOkWhenValid() throws Exception {
      CategoryPCRequest request = new CategoryPCRequest(1L, "Updated Gaming", "gaming", new BigDecimal("60.0"));
      when(categoryPCService.update(any(CategoryPCDto.class))).thenReturn(categoryPCDto);

      mockMvc.perform(put("/api/categories-pc/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Debería devolver 400 cuando los IDs no coinciden")
    void shouldReturnBadRequestWhenIdMismatch() throws Exception {
      CategoryPCRequest request = new CategoryPCRequest(2L, "Mismatch", "mismatch", new BigDecimal("60.0"));

      mockMvc.perform(put("/api/categories-pc/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteCategoryPC")
  class DeleteCategoryPCTests {

    @Test
    @DisplayName("Debería devolver 204 cuando el borrado es exitoso")
    void shouldReturnNoContent() throws Exception {
      doNothing().when(categoryPCService).deleteById(1L);

      mockMvc.perform(delete("/api/categories-pc/1"))
          .andExpect(status().isNoContent());
    }
  }
}
