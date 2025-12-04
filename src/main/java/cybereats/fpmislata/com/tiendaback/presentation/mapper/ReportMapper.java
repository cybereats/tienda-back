package cybereats.fpmislata.com.tiendaback.presentation.mapper;

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
                mapUser(reportRequest.user_id()),
                mapPc(reportRequest.pc_id()));
    }

    public static ReportResponse fromReportDtoToReportResponse(ReportDto reportDto) {
        return new ReportResponse(
                reportDto.id(),
                reportDto.priority(),
                reportDto.desc(),
                UserMapper.fromDtoToUserResponse(reportDto.user()),
                PcMapper.fromPcDtoToPcResponse(reportDto.pc()));
    }

    public static ReportRequest fromReportDtoToReportRequest(ReportDto reportDto) {
        return new ReportRequest(
                reportDto.id(),
                reportDto.priority(),
                reportDto.desc(),
                UserMapper.fromDtoToUserResponse(reportDto.user()),
                PcMapper.fromPcDtoToPcResponse(reportDto.pc()));
    }

    public static ReportDto fromReportResponseToReportDto(ReportResponse reportResponse) {
        return new ReportDto(
                reportResponse.id(),
                reportResponse.priority(),
                reportResponse.desc(),
                UserMapper.fromUserResponseToDto(reportResponse.user()),
                PcMapper.fromPcResponseToDto(reportResponse.pc()));
    }

    private static UserDto mapUser(Long id) {
        return new UserDto(id, null, null, null, null, null);
    }

    private static PcDto mapPc(Long id) {
        return new PcDto(id, null, null, null, null, null);
    }
}
