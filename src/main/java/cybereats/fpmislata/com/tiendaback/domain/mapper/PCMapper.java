package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.PC;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;

public class PCMapper {
    private static PCMapper INSTANCE;

    private PCMapper() {}

    public static PCMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PCMapper();
        }
        return INSTANCE;
    }

    public PCDto pcToPCDto(PC pc) {
        if (pc == null) {
            return null;
        }

        return new PCDto(
                pc.getId(), pc.getLabel(), pc.getSlug(), pc.getRuntime(), pc.getSpecs(), pc.getWorking_since()
        );
    }

    public PC pcDtoToPC(PCDto pcDto) {
        if (pcDto == null) {
            return null;
        }

        return new PC.Builder()
                .id(pcDto.id())
                .label(pcDto.label())
                .slug(pcDto.slug())
                .runtime(pcDto.runtime())
                .specs(pcDto.specs())
                .working_since(pcDto.working_since())
                .build();
    }
}
