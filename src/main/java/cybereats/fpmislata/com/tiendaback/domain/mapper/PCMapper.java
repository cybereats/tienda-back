package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.PC;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;

public class PCMapper {
    private static PCMapper INSTANCE;

    private PCMapper() {
    }

    public static PCMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PCMapper();
        }
        return INSTANCE;
    }

    public PCDto fromPCToPCDto(PC pc) {
        if (pc == null) {
            return null;
        }

        return new PCDto(
                pc.getId(),
                pc.getLabel(),
                pc.getSlug(),
                pc.getRuntime(),
                pc.getSpecs(),
                pc.getWorkingSince(),
                CategoryPCMapper.getInstance().fromCategoryPCToCategoryPCDto(pc.getCategoryPC()));
    }

    public PC fromPCDtoToPC(PCDto pcDto) {
        if (pcDto == null) {
            return null;
        }

        return new PC.Builder()
                .id(pcDto.id())
                .label(pcDto.label())
                .slug(pcDto.slug())
                .runtime(pcDto.runtime())
                .specs(pcDto.specs())
                .workingSince(pcDto.workingSince())
                .build();
    }
}
