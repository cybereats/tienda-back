package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CategoryProductResponse;

public class CategoryProductMapper {
    private static CategoryProductMapper INSTANCE;

    private CategoryProductMapper() {
    }

    public static CategoryProductMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryProductMapper();
        }
        return INSTANCE;
    }

    public CategoryProductResponse fromCategoryProductDtoToCategoryProductResponse(
            CategoryProductDto categoryProductDto) {
        if (categoryProductDto == null) {
            return null;
        }

        return new CategoryProductResponse(
                categoryProductDto.id(),
                categoryProductDto.label(),
                categoryProductDto.slug());
    }

    public CategoryProductDto fromCategoryProductRequestToCategoryProductDto(
            CategoryProductRequest categoryProductRequest) {
        if (categoryProductRequest == null) {
            return null;
        }

        return new CategoryProductDto(
                categoryProductRequest.id(),
                categoryProductRequest.label(),
                categoryProductRequest.slug());
    }
}
