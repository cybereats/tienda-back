package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.CategoryProductService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryProductRequest;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryProductController.class)
public class CategoryProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CategoryProductService categoryProductService;

  @Autowired
  private ObjectMapper objectMapper;

  private CategoryProductDto categoryProductDto;
  private String adminToken;

  @BeforeEach
  void setUp() {
    categoryProductDto = new CategoryProductDto(1L, "Food", "food");

    User adminUser = new User.Builder()
        .id(1L)
        .username("admin")
        .role(UserRole.ADMIN)
        .build();
    adminToken = JwtUtil.generateToken(adminUser);
  }

  @Nested
  @DisplayName("Tests para el método findAllCategoryProducts")
  class FindAllCategoryProductsTests {

    @Test
    @DisplayName("Debería devolver 200 y una página de categorías")
    void shouldReturnOkAndPage() throws Exception {
      Page<CategoryProductDto> page = new Page<>(List.of(categoryProductDto), 1, 10, 1L);
      when(categoryProductService.findAll(1, 10)).thenReturn(page);

      mockMvc.perform(get("/api/category-products?page=1&size=10"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data[0].id").value(categoryProductDto.id()))
          .andExpect(jsonPath("$.data[0].label").value(categoryProductDto.label()))
          .andExpect(jsonPath("$.pageNumber").value(1))
          .andExpect(jsonPath("$.totalElements").value(1));
    }
  }

  @Nested
  @DisplayName("Tests para el método getCategoryProductBySlug")
  class GetCategoryProductBySlugTests {

    @Test
    @DisplayName("Debería devolver 200 cuando el slug existe")
    void shouldReturnOkWhenExists() throws Exception {
      when(categoryProductService.getBySlug("food")).thenReturn(categoryProductDto);

      mockMvc.perform(get("/api/category-products/food"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.slug").value("food"))
          .andExpect(jsonPath("$.label").value("Food"));
    }

    @Test
    @DisplayName("Debería devolver 404 cuando el slug no existe")
    void shouldReturnNotFoundWhenDoesNotExist() throws Exception {
      when(categoryProductService.getBySlug("unknown"))
          .thenThrow(new ResourceNotFoundException("CategoryProduct not found"));

      mockMvc.perform(get("/api/category-products/unknown"))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("Tests para el método createCategoryProduct")
  class CreateCategoryProductTests {

    @Test
    @DisplayName("Debería devolver 201 cuando la petición es válida y tiene token admin")
    void shouldReturnCreatedWhenValidAndAdmin() throws Exception {
      CategoryProductRequest request = new CategoryProductRequest(null, "Drinks", "drinks");
      CategoryProductDto createdDto = new CategoryProductDto(2L, "Drinks", "drinks");
      when(categoryProductService.insert(any(CategoryProductDto.class))).thenReturn(createdDto);

      mockMvc.perform(post("/api/category-products")
          .header("Authorization", "Bearer " + adminToken)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(2L))
          .andExpect(jsonPath("$.label").value("Drinks"));
    }

    @Test
    @DisplayName("Debería devolver 401 cuando no tiene token")
    void shouldReturnUnauthorizedWhenNoToken() throws Exception {
      CategoryProductRequest request = new CategoryProductRequest(null, "Drinks", "drinks");

      mockMvc.perform(post("/api/category-products")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isUnauthorized());
    }
  }

  @Nested
  @DisplayName("Tests para el método updateCategoryProduct")
  class UpdateCategoryProductTests {

    @Test
    @DisplayName("Debería devolver 200 cuando la petición es válida y tiene token admin")
    void shouldReturnOkWhenValidAndAdmin() throws Exception {
      CategoryProductRequest request = new CategoryProductRequest(1L, "Updated Food", "food");
      when(categoryProductService.update(any(CategoryProductDto.class))).thenReturn(categoryProductDto);

      mockMvc.perform(put("/api/category-products/food")
          .header("Authorization", "Bearer " + adminToken)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Debería devolver 400 cuando los slugs no coinciden")
    void shouldReturnBadRequestWhenSlugMismatch() throws Exception {
      CategoryProductRequest request = new CategoryProductRequest(1L, "Mismatch", "mismatch");

      mockMvc.perform(put("/api/category-products/food")
          .header("Authorization", "Bearer " + adminToken)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("Tests para el método deleteCategoryProduct")
  class DeleteCategoryProductTests {

    @Test
    @DisplayName("Debería devolver 204 cuando el borrado es exitoso y tiene token admin")
    void shouldReturnNoContentWhenAdmin() throws Exception {
      doNothing().when(categoryProductService).deleteBySlug("food");

      mockMvc.perform(delete("/api/category-products/food")
          .header("Authorization", "Bearer " + adminToken))
          .andExpect(status().isNoContent());
    }
  }
}
