package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;

public class ReportMapper {
    public static ReportJpaEntity fromReportDtotoReportJpaEntity(ReportDto reportDto) {
        return new ReportJpaEntity(
                reportDto.id(),
                reportDto.priority(),
                reportDto.description(),
                reportDto.subject(),
                reportDto.status(),
                reportDto.createdAt(),
                UserMapper.getInstance().fromUserDtoToUserJpaEntity(reportDto.user()),
                PCMapper.getInstance().fromPCDtoToPCJpaEntity(reportDto.pc()));
    }

    public static ReportDto fromReportJpaEntitytoReportDto(ReportJpaEntity reportJpaEntity) {
        return new ReportDto(
                reportJpaEntity.getId(),
                reportJpaEntity.getPriority(),
                reportJpaEntity.getDescription(),
                reportJpaEntity.getSubject(),
                reportJpaEntity.getStatus(),
                reportJpaEntity.getCreatedAt(),
                UserMapper.getInstance().fromUserJpaEntityToUserDto(reportJpaEntity.getUser()),
                PCMapper.getInstance().fromPCJpaEntityToPCDto(reportJpaEntity.getPc()));
    }
}
