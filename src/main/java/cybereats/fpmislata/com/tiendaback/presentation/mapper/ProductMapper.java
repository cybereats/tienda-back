package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ProductResponse;

public class ProductMapper {
    private static ProductMapper INSTANCE;

    private ProductMapper() {
    }

    public static ProductMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductMapper();
        }
        return INSTANCE;
    }

    public ProductDto fromProductRequestToProductDto(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }

        return new ProductDto(
                productRequest.id(),
                productRequest.label(),
                productRequest.slug(),
                productRequest.description(),
                productRequest.price(),
                CategoryProductMapper.getInstance()
                        .fromCategoryProductRequestToCategoryProductDto(productRequest.category()));
    }

    public ProductResponse fromProductDtoToProductResponse(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return new ProductResponse(
                productDto.id(),
                productDto.label(),
                productDto.slug(),
                productDto.description(),
                productDto.price(),
                CategoryProductMapper.getInstance()
                        .fromCategoryProductDtoToCategoryProductResponse(productDto.category()));
    }
}
