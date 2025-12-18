package cybereats.fpmislata.com.tiendaback.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.User;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.ProductService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ProductRequest;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    private ProductDto productDto;
    private ProductRequest productRequest;
    private String adminToken;
    private String clientToken;

    @BeforeEach
    void setUp() {
        CategoryProductDto catDto = new CategoryProductDto(1L, "Hamburguesas", "hamburguesas");
        productDto = new ProductDto(1L, "Teclado Mecánico", "teclado-mecanico", "Description", new BigDecimal("49.90"),
                catDto);

        CategoryProductRequest catReq = new CategoryProductRequest(1L, "Hamburguesas", "hamburguesas");
        productRequest = new ProductRequest(1L, "Teclado Mecánico", "teclado-mecanico", "Description",
                new BigDecimal("49.90"), catReq);

        User adminUser = new User.Builder().id(1L).role(UserRole.ADMIN).build();
        User clientUser = new User.Builder().id(2L).role(UserRole.CLIENT).build();

        adminToken = JwtUtil.generateToken(adminUser);
        clientToken = JwtUtil.generateToken(clientUser);
    }

    @Nested
    @DisplayName("GET /api/products")
    class GetAllProductsTests {

        @Test
        @DisplayName("Debería devolver una página de productos")
        void shouldReturnPageOfProducts() throws Exception {
            Page<ProductDto> page = new Page<>(List.of(productDto), 1, 10, 1L);
            when(productService.findAll(anyInt(), anyInt())).thenReturn(page);

            mockMvc.perform(get("/api/products")
                    .param("page", "1")
                    .param("size", "10"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data[0].slug").value("teclado-mecanico"))
                    .andExpect(jsonPath("$.totalElements").value(1));
        }
    }

    @Nested
    @DisplayName("GET /api/products/{slug}")
    class GetProductBySlugTests {

        @Test
        @DisplayName("Debería devolver un producto por su slug")
        void shouldReturnProductBySlug() throws Exception {
            when(productService.getBySlug("teclado-mecanico")).thenReturn(productDto);

            mockMvc.perform(get("/api/products/teclado-mecanico"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.slug").value("teclado-mecanico"))
                    .andExpect(jsonPath("$.label").value("Teclado Mecánico"));
        }

        @Test
        @DisplayName("Debería devolver 404 cuando el producto no existe")
        void shouldReturn404WhenNotFound() throws Exception {
            when(productService.getBySlug("non-existent"))
                    .thenThrow(new ResourceNotFoundException("Product not found"));

            mockMvc.perform(get("/api/products/non-existent"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /api/products")
    class CreateProductTests {

        @Test
        @DisplayName("Debería crear un producto cuando es ADMIN")
        void shouldCreateProductWhenAdmin() throws Exception {
            when(productService.create(any(ProductDto.class))).thenReturn(productDto);

            mockMvc.perform(post("/api/products")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.slug").value("teclado-mecanico"));
        }

        @Test
        @DisplayName("Debería devolver 403 cuando es CLIENT")
        void shouldReturn403WhenClient() throws Exception {
            mockMvc.perform(post("/api/products")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                    .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("Debería devolver 400 cuando los datos son inválidos")
        void shouldReturn400WhenInvalidData() throws Exception {
            ProductRequest invalidRequest = new ProductRequest(null, null, "", null, null, null);

            mockMvc.perform(post("/api/products")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("PUT /api/products/{slug}")
    class UpdateProductTests {

        @Test
        @DisplayName("Debería actualizar un producto cuando es ADMIN")
        void shouldUpdateProductWhenAdmin() throws Exception {
            when(productService.update(any(ProductDto.class))).thenReturn(productDto);

            mockMvc.perform(put("/api/products/teclado-mecanico")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.slug").value("teclado-mecanico"));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando los slugs no coinciden")
        void shouldReturn400WhenSlugsMismatch() throws Exception {
            mockMvc.perform(put("/api/products/different-slug")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("DELETE /api/products/{slug}")
    class DeleteProductTests {

        @Test
        @DisplayName("Debería eliminar un producto cuando es ADMIN")
        void shouldDeleteProductWhenAdmin() throws Exception {
            mockMvc.perform(delete("/api/products/teclado-mecanico")
                    .header("Authorization", "Bearer " + adminToken))
                    .andExpect(status().isNoContent());

            verify(productService, times(1)).deleteBySlug("teclado-mecanico");
        }

        @Test
        @DisplayName("Debería devolver 403 cuando es CLIENT")
        void shouldReturn403WhenClient() throws Exception {
            mockMvc.perform(delete("/api/products/teclado-mecanico")
                    .header("Authorization", "Bearer " + clientToken))
                    .andExpect(status().isForbidden());
        }
    }
}
