package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.LogRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.LogResponse;

public class LogMapper {
    private static LogMapper INSTANCE;

    private LogMapper() {}

    public static LogMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogMapper();
        }
        return INSTANCE;
    }

    public LogDto fromLogRequestToLogDto(LogRequest logRequest) {
        if (logRequest == null) {
            return null;
        }

        return new LogDto(
                logRequest.id(),
                logRequest.info(),
                logRequest.timestamp()
        );
    }

    public LogResponse fromLogDtoToLogResponse(LogDto logDto) {
        if (logDto == null) {
            return null;
        }

        return new LogResponse(
                logDto.id(),
                logDto.info(),
                logDto.timestamp()
        );
    }
}
