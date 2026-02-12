package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.ReportStatus;
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
        if (reportDto.id() != null) {
            Optional<ReportDto> reportDtoOptional = reportRepository.findById(reportDto.id());
            if (reportDtoOptional.isPresent()) {
                throw new BusinessException("Report already exists");
            }
        }
        ReportStatus status = reportDto.status() != null ? reportDto.status() : ReportStatus.OPEN;
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

        if (ReportStatus.IN_PROGRESS == status || ReportStatus.PENDING == status) {
            updatePCStatusToMaintenance(reportDto.pc());
        } else if (ReportStatus.RESOLVED == status) {
            updatePCStatusToAvailable(reportDto.pc());
        }

        return reportRepository.save(reportToSave);
    }

    @Override
    @Transactional
    public ReportDto update(ReportDto reportDto) {
        ReportDto existingReportDto = reportRepository.findById(reportDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));

        ReportStatus newStatus = reportDto.status() != null ? reportDto.status() : existingReportDto.status();

        ReportDto reportToSave = new ReportDto(
                existingReportDto.id(),
                reportDto.priority() != null ? reportDto.priority() : existingReportDto.priority(),
                reportDto.description() != null ? reportDto.description() : existingReportDto.description(),
                reportDto.subject() != null ? reportDto.subject() : existingReportDto.subject(),
                newStatus,
                existingReportDto.createdAt(),
                reportDto.user() != null ? reportDto.user() : existingReportDto.user(),
                reportDto.pc() != null ? reportDto.pc() : existingReportDto.pc());

        if (ReportStatus.IN_PROGRESS == newStatus || ReportStatus.PENDING == newStatus) {
            updatePCStatusToMaintenance(reportToSave.pc());
        } else if (ReportStatus.RESOLVED == newStatus) {
            updatePCStatusToAvailable(reportToSave.pc());
        }

        return reportRepository.save(reportToSave);
    }

    private void updatePCStatusToMaintenance(PCDto pcDto) {
        if (pcDto != null && pcDto.id() != null) {
            // Buscamos el PC completo para no perder datos al actualizar el estado
            PCDto fullPcDto = pcRepository.findById(pcDto.id())
                    .orElseThrow(() -> new ResourceNotFoundException("Computer not found"));

            PCDto updatedPcDto = new PCDto(
                    fullPcDto.id(),
                    fullPcDto.label(),
                    fullPcDto.slug(),
                    fullPcDto.runtime(),
                    fullPcDto.specs(),
                    fullPcDto.workingSince(),
                    fullPcDto.image(),
                    PCStatus.MAINTENANCE,
                    fullPcDto.categoryPCDto());
            pcRepository.save(updatedPcDto);
        }
    }

    private void updatePCStatusToAvailable(PCDto pcDto) {
        if (pcDto != null && pcDto.id() != null) {
            // Buscamos el PC completo para no perder datos al actualizar el estado
            PCDto fullPcDto = pcRepository.findById(pcDto.id())
                    .orElseThrow(() -> new ResourceNotFoundException("Computer not found"));

            PCDto updatedPcDto = new PCDto(
                    fullPcDto.id(),
                    fullPcDto.label(),
                    fullPcDto.slug(),
                    fullPcDto.runtime(),
                    fullPcDto.specs(),
                    fullPcDto.workingSince(),
                    fullPcDto.image(),
                    PCStatus.AVAILABLE,
                    fullPcDto.categoryPCDto());
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
        try {
            return reportRepository.findByStatus(ReportStatus.valueOf(status));
        } catch (IllegalArgumentException e) {
            // Handle invalid status string if necessary, or return empty list
            return List.of();
        }
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
