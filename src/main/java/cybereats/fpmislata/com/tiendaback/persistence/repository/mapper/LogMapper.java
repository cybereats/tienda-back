package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.LogJpaEntity;

public class LogMapper {
    private static LogMapper INSTANCE;

    private LogMapper() {}

    public static LogMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogMapper();
        }
        return INSTANCE;
    }

    public LogDto logJpaEntityToLogDto(LogJpaEntity logJpaEntity) {
        if (logJpaEntity == null) {
            return null;
        }

        return new LogDto(
                logJpaEntity.getId(),
                logJpaEntity.getInfo(),
                logJpaEntity.getTimestamp()
        );
    }

    public LogJpaEntity logDtoToLogJpaEntity(LogDto logDto) {
        if (logDto == null) {
            return null;
        }

        return new LogJpaEntity(
                logDto.id(),
                logDto.info(),
                logDto.timestamp()
        );
    }
}
