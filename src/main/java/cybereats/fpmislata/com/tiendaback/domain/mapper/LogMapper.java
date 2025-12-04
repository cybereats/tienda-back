package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Log;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;

public class LogMapper {
    private static LogMapper INSTANCE;

    private LogMapper() {}

    public static LogMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogMapper();
        }
        return INSTANCE;
    }

    public LogDto logToLogDto(Log log) {
        if (log == null) {
            return null;
        }

        return new LogDto(
                log.getId(), log.getInfo(), log.getTimestamp()
        );
    }

    public Log logDtoToLog(LogDto logDto) {
        if (logDto == null) {
            return null;
        }

        return new Log.Builder()
                .id(logDto.id())
                .info(logDto.info())
                .timestamp(logDto.timestamp())
                .build();
    }
}
