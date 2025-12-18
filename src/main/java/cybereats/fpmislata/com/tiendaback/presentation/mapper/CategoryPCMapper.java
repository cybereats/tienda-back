package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryPCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CategoryPCResponse;

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

    public CategoryPCDto fromCategoryPCRequestToCategoryPCDto(CategoryPCRequest categoryPCRequest) {
        if (categoryPCRequest == null) {
            return null;
        }

        return new CategoryPCDto(
                categoryPCRequest.id(),
                categoryPCRequest.label(),
                categoryPCRequest.slug(),
                categoryPCRequest.price());
    }

    public CategoryPCResponse fromCategoryPCDtoToCategoryPCResponse(CategoryPCDto categoryPCDto) {
        if (categoryPCDto == null) {
            return null;
        }

        return new CategoryPCResponse(
                categoryPCDto.id(),
                categoryPCDto.label(),
                categoryPCDto.slug(),
                categoryPCDto.price());
    }
}
