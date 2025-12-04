package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
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

    public CategoryProductDto categoryProductJpaEntityToCategoryProductDto(
            CategoryProductJpaEntity categoryProductJpaEntity) {
        if (categoryProductJpaEntity == null) {
            return null;
        }

        return new CategoryProductDto(
                categoryProductJpaEntity.getId(),
                categoryProductJpaEntity.getName(),
                categoryProductJpaEntity.getSlug());
    }

    public CategoryProductJpaEntity categoryProductDtoToCategoryProductJpaEntity(
            CategoryProductDto categoryProductDto) {
        if (categoryProductDto == null) {
            return null;
        }

        return new CategoryProductJpaEntity(
                categoryProductDto.id(),
                categoryProductDto.name(),
                categoryProductDto.slug());
    }

    public CategoryProductResponse categoryProductDtoToCategoryProductResponse(
            CategoryProductDto categoryProductDto) {
        if (categoryProductDto == null) {
            return null;
        }

        return new CategoryProductResponse(
                categoryProductDto.id(),
                categoryProductDto.name(),
                categoryProductDto.slug());
    }

    public CategoryProductDto categoryProductRequestToCategoryProductDto(
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
