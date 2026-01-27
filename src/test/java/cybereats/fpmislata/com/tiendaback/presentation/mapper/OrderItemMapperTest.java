package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.OrderItemDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.OrderItemRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.OrderItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemMapperTest {

    @Test
    @DisplayName("Debería mapear de OrderItemDto a OrderItemRequest")
    void shouldMapDtoToRequest() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto productDto = new ProductDto(1L, "Product", "product", "Description", new BigDecimal("10.00"),
                "image.png",
                categoryDto);
        OrderItemDto dto = new OrderItemDto(1L, productDto, 5);

        OrderItemRequest request = OrderItemMapper.fromOrderItemDtoToOrderItemRequest(dto);

        assertNotNull(request);
        assertEquals(dto.id(), request.id());
        assertEquals(dto.product().id(), request.productId());
        assertEquals(dto.quantity(), request.quantity());
    }

    @Test
    @DisplayName("Debería mapear de OrderItemRequest a OrderItemDto")
    void shouldMapRequestToDto() {
        OrderItemRequest request = new OrderItemRequest(1L, 10L, 5);

        OrderItemDto dto = OrderItemMapper.fromOrderItemRequestToOrderItemDto(request);

        assertNotNull(dto);
        assertEquals(request.id(), dto.id());
        assertEquals(request.productId(), dto.product().id());
        assertEquals(request.quantity(), dto.quantity());
    }

    @Test
    @DisplayName("Debería mapear de OrderItemDto a OrderItemResponse")
    void shouldMapDtoToResponse() {
        CategoryProductDto categoryDto = new CategoryProductDto(1L, "Category", "category");
        ProductDto productDto = new ProductDto(1L, "Product", "product", "Description", new BigDecimal("10.00"),
                "image.png",
                categoryDto);
        OrderItemDto dto = new OrderItemDto(1L, productDto, 5);

        OrderItemResponse response = OrderItemMapper.fromOrderItemDtoToOrderItemResponse(dto);

        assertNotNull(response);
        assertEquals(dto.id(), response.id());
        assertEquals(dto.product().id(), response.product().id());
        assertEquals(dto.quantity(), response.quantity());
    }
}
