package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ReportRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ReportResponse;

public class ReportMapper {
    public static ReportDto fromReportRequestToReportDto(ReportRequest reportRequest) {
        return new ReportDto(
                reportRequest.id(),
                reportRequest.priority(),
                reportRequest.description(),
                mapUser(reportRequest.userId()),
                mapPC(reportRequest.pcId()));
    }

    public static ReportResponse fromReportDtoToReportResponse(ReportDto reportDto) {
        return new ReportResponse(
                reportDto.id(),
                reportDto.priority(),
                reportDto.desc(),
                UserMapper.getInstance().fromUserDtoToUserResponse(reportDto.user()),
                PCMapper.getInstance().fromPCDtoToPCResponse(reportDto.pc()));
    }

    private static UserDto mapUser(Long id) {
        return new UserDto(id, null, null, null, null, null, null, null);
    }

    private static PCDto mapPC(Long id) {
        return new PCDto(id, null, null, 0, null, null, null, null);
    }
}
