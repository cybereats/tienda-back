package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.repository.ReportRepository;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ReportJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.ReportMapper;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;

import java.util.List;
import java.util.Optional;

public class ReportRepositoryImpl implements ReportRepository {

    private final ReportJpaDao reportJpaDao;

    public ReportRepositoryImpl(ReportJpaDao reportJpaDao) {
        this.reportJpaDao = reportJpaDao;
    }

    @Override
    public ReportDto save(ReportDto reportDto) {
        if (reportDto.id() == null) {
            ReportJpaEntity reportJpaEntity = ReportMapper.toReportJpaEntity(reportDto);
            return ReportMapper.toReportDto(reportJpaDao.insert(reportJpaEntity));
        } else {
            ReportJpaEntity reportJpaEntity = ReportMapper.toReportJpaEntity(reportDto);
            return ReportMapper.toReportDto(reportJpaDao.update(reportJpaEntity));
        }
    }

    @Override
    public List<ReportDto> getAll() {
        return reportJpaDao.getAll().stream().map(ReportMapper::toReportDto).toList();
    }

    @Override
    public List<ReportDto> getByUserId(Long userId) {
        return reportJpaDao.getByUserId(userId).stream().map(ReportMapper::toReportDto).toList();
    }

    @Override
    public List<ReportDto> getByPcId(Long pcId) {
        return reportJpaDao.getByPcId(pcId).stream().map(ReportMapper::toReportDto).toList();
    }

    @Override
    public Optional<ReportDto> getById(Long id) {
        Optional<ReportJpaEntity> reportJpaEntity = reportJpaDao.getById(id);
        return reportJpaEntity.map(ReportMapper::toReportDto);
    }

    @Override
    public void deleteById(Long id) {
        reportJpaDao.deleteById(id);
    }
}
