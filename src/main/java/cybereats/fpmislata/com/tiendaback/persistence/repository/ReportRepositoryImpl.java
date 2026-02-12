package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.ReportStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.ReportRepository;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ReportJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.ReportMapper;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<ReportDto> findAll(int page, int size) {
        List<ReportDto> content = reportJpaDao.findAll(page, size).stream()
                .map(ReportMapper::fromReportJpaEntitytoReportDto)
                .toList();
        long totalElements = reportJpaDao.count();
        return new Page<>(content, page, size, totalElements);
    }

    @Override
    public Map<String, Long> countReportsByStatus() {
        return reportJpaDao.countReportsByStatus().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]));
    }

    @Override
    public Page<ReportDto> search(String text, String status, String date, int page, int size) {
        List<ReportDto> content = reportJpaDao.search(text, status, date, page, size).stream()
                .map(ReportMapper::fromReportJpaEntitytoReportDto)
                .toList();
        long totalElements = reportJpaDao.countSearch(text, status, date);
        return new Page<>(content, page, size, totalElements);
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
    public List<ReportDto> findByStatus(ReportStatus status) {
        return reportJpaDao.findByStatus(status).stream().map(ReportMapper::fromReportJpaEntitytoReportDto).toList();
    }

    @Override
    public List<ReportDto> findByCreatedAt(String createdAt) {
        return reportJpaDao.findByCreatedAt(createdAt).stream().map(ReportMapper::fromReportJpaEntitytoReportDto)
                .toList();
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
