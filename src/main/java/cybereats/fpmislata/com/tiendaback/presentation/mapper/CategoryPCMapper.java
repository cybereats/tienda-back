package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryPCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CategoryPCResponse;

public class CategoryPCMapper {
    private static CategoryPCMapper INSTANCE;

    private CategoryPCMapper() {}

    public static CategoryPCMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryPCMapper();
        }
        return INSTANCE;
    }

    public CategoryPCDto categoryPCRequestToCategoryPCDto(CategoryPCRequest categoryPCRequest) {
        if (categoryPCRequest == null) {
            return null;
        }

        return new CategoryPCDto(
                categoryPCRequest.id(),
                categoryPCRequest.label(),
                categoryPCRequest.price(),
                null
        );
    }

    public CategoryPCResponse categoryPCDtoToCategoryPCResponse(CategoryPCDto categoryPCDto) {
        if (categoryPCDto == null) {
            return null;
        }

        return new CategoryPCResponse(
                categoryPCDto.id(),
                categoryPCDto.label(),
                categoryPCDto.price(),
                categoryPCDto.pc_list().stream().map(pc -> PCMapper.getInstance().pcDtoToPCResponse(pc)).toList()
        );
    }
}
