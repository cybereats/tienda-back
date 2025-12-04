package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.CategoryPC;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;

public class CategoryPCMapper {
    private static CategoryPCMapper INSTANCE;

    private CategoryPCMapper() {}

    public static CategoryPCMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryPCMapper();
        }
        return INSTANCE;
    }

    public CategoryPCDto categoryPCToCategoryPCDto(CategoryPC categoryPC) {
        if (categoryPC == null) {
            return null;
        }

        return new CategoryPCDto(
                categoryPC.getId(), categoryPC.getLabel(), categoryPC.getPrice(), categoryPC.getPc_list()
        );
    }

    public CategoryPC categoryPCDtoToCategoryPC(CategoryPCDto categoryPCDto) {
        if (categoryPCDto == null) {
            return null;
        }

        return new CategoryPC.Builder()
                .id(categoryPCDto.id())
                .label(categoryPCDto.label())
                .price(categoryPCDto.price())
                .pc_list(categoryPCDto.pc_list())
                .build();
    }
}
