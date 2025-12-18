package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.ReportRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.ReportService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    @Transactional
    public ReportDto insert(ReportDto reportDto) {
        Optional<ReportDto> reportDtoOptional = reportRepository.findById(reportDto.id());
        if (reportDtoOptional.isPresent()) {
            throw new BusinessException("Report already exists");
        }
        // Set default values if not present
        String status = reportDto.status() != null ? reportDto.status() : "OPEN";
        String createdAt = reportDto.createdAt() != null ? reportDto.createdAt()
                : java.time.LocalDate.now().toString();

        ReportDto reportToSave = new ReportDto(
                reportDto.id(),
                reportDto.priority(),
                reportDto.desc(),
                reportDto.subject(),
                status,
                createdAt,
                reportDto.user(),
                reportDto.pc());

        return reportRepository.save(reportToSave);
    }

    @Override
    @Transactional
    public ReportDto update(ReportDto reportDto) {
        ReportDto existingReportDto = reportRepository.findById(reportDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));

        ReportDto reportToSave = new ReportDto(
                existingReportDto.id(),
                reportDto.priority() != null ? reportDto.priority() : existingReportDto.priority(),
                reportDto.desc() != null ? reportDto.desc() : existingReportDto.desc(),
                reportDto.subject() != null ? reportDto.subject() : existingReportDto.subject(),
                reportDto.status() != null ? reportDto.status() : existingReportDto.status(),
                existingReportDto.createdAt(),
                reportDto.user() != null ? reportDto.user() : existingReportDto.user(),
                reportDto.pc() != null ? reportDto.pc() : existingReportDto.pc());

        return reportRepository.save(reportToSave);
    }

    @Override
    public Page<ReportDto> findAll(int page, int size) {
        return reportRepository.findAll(page, size);
    }

    @Override
    public List<ReportDto> findByUserId(Long userId) {
        return reportRepository.findByUserId(userId);
    }

    @Override
    public List<ReportDto> findByPCId(Long pcId) {
        return reportRepository.findByPCId(pcId);
    }

    @Override
    public List<ReportDto> findByStatus(String status) {
        return reportRepository.findByStatus(status);
    }

    @Override
    public List<ReportDto> findByCreatedAt(String createdAt) {
        return reportRepository.findByCreatedAt(createdAt);
    }

    @Override
    public Optional<ReportDto> findById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public ReportDto getById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report not found"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<ReportDto> reportDtoOptional = reportRepository.findById(id);
        if (reportDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Report not found");
        }
        reportRepository.deleteById(id);
    }

}
