package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.PCResponse;

public class PCMapper {
    private static PCMapper INSTANCE;

    private PCMapper() {}

    public static PCMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PCMapper();
        }
        return INSTANCE;
    }

    public PCDto pcRequestToPCDto(PCRequest pcRequest) {
        if (pcRequest == null) {
            return null;
        }

        return new PCDto(
                pcRequest.id(),
                pcRequest.label(),
                pcRequest.slug(),
                pcRequest.runtime(),
                pcRequest.specs(),
                pcRequest.working_since()
        );
    }

    public PCResponse pcDtoToPCResponse(PCDto pcDto) {
        if (pcDto == null) {
            return null;
        }

        return new PCResponse(
                pcDto.id(),
                pcDto.label(),
                pcDto.slug(),
                pcDto.runtime(),
                pcDto.specs(),
                pcDto.working_since()
        );
    }
}
