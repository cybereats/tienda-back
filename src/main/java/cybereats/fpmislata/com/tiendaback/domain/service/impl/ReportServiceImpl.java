package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.PCStatus;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.ReportRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.ReportService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository pcRepository;

    public ReportServiceImpl(ReportRepository reportRepository,
            cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository pcRepository) {
        this.reportRepository = reportRepository;
        this.pcRepository = pcRepository;
    }

    @Override
    @Transactional
    public ReportDto insert(ReportDto reportDto) {
        Optional<ReportDto> reportDtoOptional = reportRepository.findById(reportDto.id());
        if (reportDtoOptional.isPresent()) {
            throw new BusinessException("Report already exists");
        }
        String status = reportDto.status() != null ? reportDto.status() : "OPEN";
        String createdAt = reportDto.createdAt() != null ? reportDto.createdAt()
                : java.time.LocalDate.now().toString();

        ReportDto reportToSave = new ReportDto(
                reportDto.id(),
                reportDto.priority(),
                reportDto.description(),
                reportDto.subject(),
                status,
                createdAt,
                reportDto.user(),
                reportDto.pc());

        if ("IN_PROGRESS".equalsIgnoreCase(status)) {
            updatePCStatusToMaintenance(reportDto.pc());
        }

        return reportRepository.save(reportToSave);
    }

    @Override
    @Transactional
    public ReportDto update(ReportDto reportDto) {
        ReportDto existingReportDto = reportRepository.findById(reportDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));

        String newStatus = reportDto.status() != null ? reportDto.status() : existingReportDto.status();

        ReportDto reportToSave = new ReportDto(
                existingReportDto.id(),
                reportDto.priority() != null ? reportDto.priority() : existingReportDto.priority(),
                reportDto.description() != null ? reportDto.description() : existingReportDto.description(),
                reportDto.subject() != null ? reportDto.subject() : existingReportDto.subject(),
                newStatus,
                existingReportDto.createdAt(),
                reportDto.user() != null ? reportDto.user() : existingReportDto.user(),
                reportDto.pc() != null ? reportDto.pc() : existingReportDto.pc());

        if ("IN_PROGRESS".equalsIgnoreCase(newStatus)) {
            updatePCStatusToMaintenance(reportToSave.pc());
        }

        return reportRepository.save(reportToSave);
    }

    private void updatePCStatusToMaintenance(PCDto pcDto) {
        if (pcDto != null) {
            PCDto updatedPcDto = new PCDto(
                    pcDto.id(),
                    pcDto.label(),
                    pcDto.slug(),
                    pcDto.runtime(),
                    pcDto.specs(),
                    pcDto.workingSince(),
                    pcDto.image(),
                    PCStatus.MAINTENANCE,
                    pcDto.categoryPCDto());
            pcRepository.save(updatedPcDto);
        }
    }

    @Override
    public Page<ReportDto> findAll(int page, int size) {
        return reportRepository.findAll(page, size);
    }

    @Override
    public Map<String, Long> countReportsByStatus() {
        return reportRepository.countReportsByStatus();
    }

    @Override
    public Page<ReportDto> search(String text, String status, String date, int page, int size) {
        return reportRepository.search(text, status, date, page, size);
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
