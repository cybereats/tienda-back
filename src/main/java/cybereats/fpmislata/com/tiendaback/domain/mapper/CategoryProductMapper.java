package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.CategoryProduct;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;

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

    public CategoryProductDto fromCategoryProductToCategoryProductDto(CategoryProduct categoryProduct) {
        if (categoryProduct == null) {
            return null;
        }

        return new CategoryProductDto(
                categoryProduct.getId(),
                categoryProduct.getName(),
                categoryProduct.getSlug());
    }

    public CategoryProduct fromCategoryProductDtoToCategoryProduct(CategoryProductDto categoryProductDto) {
        if (categoryProductDto == null) {
            return null;
        }

        return new CategoryProduct.Builder()
                .id(categoryProductDto.id())
                .name(categoryProductDto.label())
                .slug(categoryProductDto.slug())
                .build();
    }
}
