package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;

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
}
