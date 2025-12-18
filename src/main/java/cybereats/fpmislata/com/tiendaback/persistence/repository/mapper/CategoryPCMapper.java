package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;

public class CategoryPCMapper {
    private static CategoryPCMapper INSTANCE;

    private CategoryPCMapper() {
    }

    public static CategoryPCMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryPCMapper();
        }
        return INSTANCE;
    }

    public CategoryPCDto fromCategoryPCJpaEntityToCategoryPCDto(CategoryPCJpaEntity categoryPCJpaEntity) {
        if (categoryPCJpaEntity == null) {
            return null;
        }

        return new CategoryPCDto(
                categoryPCJpaEntity.getId(),
                categoryPCJpaEntity.getLabel(),
                categoryPCJpaEntity.getSlug(),
                categoryPCJpaEntity.getPrice());
    }

    public CategoryPCJpaEntity fromCategoryPCDtoToCategoryPCJpaEntity(CategoryPCDto categoryPCDto) {
        if (categoryPCDto == null) {
            return null;
        }

        return new CategoryPCJpaEntity(
                categoryPCDto.id(),
                categoryPCDto.label(),
                categoryPCDto.slug(),
                categoryPCDto.price());
    }
}
