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
            ReportJpaEntity reportJpaEntity = ReportMapper.fromReportDtotoReportJpaEntity(reportDto);
            return ReportMapper.fromReportJpaEntitytoReportDto(reportJpaDao.insert(reportJpaEntity));
        } else {
            ReportJpaEntity reportJpaEntity = ReportMapper.fromReportDtotoReportJpaEntity(reportDto);
            return ReportMapper.fromReportJpaEntitytoReportDto(reportJpaDao.update(reportJpaEntity));
        }
    }

    @Override
    public List<ReportDto> findAll(int page, int size) {
        return reportJpaDao.findAll(page, size).stream().map(ReportMapper::fromReportJpaEntitytoReportDto).toList();
    }

    @Override
    public List<ReportDto> findByUserId(Long userId) {
        return reportJpaDao.findByUserId(userId).stream().map(ReportMapper::fromReportJpaEntitytoReportDto).toList();
    }

    @Override
    public List<ReportDto> findByPCId(Long pcId) {
        return reportJpaDao.findByPCId(pcId).stream().map(ReportMapper::fromReportJpaEntitytoReportDto).toList();
    }

    @Override
    public Optional<ReportDto> findById(Long id) {
        Optional<ReportJpaEntity> reportJpaEntity = reportJpaDao.findById(id);
        return reportJpaEntity.map(ReportMapper::fromReportJpaEntitytoReportDto);
    }

    @Override
    public void deleteById(Long id) {
        reportJpaDao.deleteById(id);
    }
}
