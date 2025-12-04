package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;

public class ReportMapper {
    public static ReportJpaEntity toReportJpaEntity(ReportDto reportDto) {
        return new ReportJpaEntity(
                reportDto.id(),
                reportDto.priority(),
                reportDto.desc(),
                reportDto.user(),
                reportDto.pc());
    }

    public static ReportDto toReportDto(ReportJpaEntity reportJpaEntity) {
        return new ReportDto(
                reportJpaEntity.getId(),
                reportJpaEntity.getPriority(),
                reportJpaEntity.getDesc(),
                reportJpaEntity.getUser(),
                reportJpaEntity.getPc());
    }
}
