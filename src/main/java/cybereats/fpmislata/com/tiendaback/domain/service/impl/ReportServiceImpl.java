package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.repository.ReportRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.ReportService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportDto insert(ReportDto reportDto) {
        Optional<ReportDto> reportDtoOptional = reportRepository.getById(reportDto.id());
        if (reportDtoOptional.isPresent()) {
            throw new BusinessException("Report already exists");
        }
        return reportRepository.save(reportDto);
    }

    @Override
    public ReportDto update(ReportDto reportDto) {
        Optional<ReportDto> reportDtoOptional = reportRepository.getById(reportDto.id());
        if (reportDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Report not found");
        }
        return reportRepository.save(reportDto);
    }

    @Override
    public List<ReportDto> getAll() {
        return reportRepository.getAll();
    }

    @Override
    public List<ReportDto> getByUserId(Long userId) {
        return reportRepository.getByUserId(userId);
    }

    @Override
    public List<ReportDto> getByPCId(Long pcId) {
        return reportRepository.getByPCId(pcId);
    }

    @Override
    public Optional<ReportDto> getById(Long id) {
        Optional<ReportDto> reportDtoOptional = reportRepository.getById(id);
        if (reportDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Report not found");
        }
        return reportDtoOptional;
    }

    @Override
    public void deleteById(Long id) {
        Optional<ReportDto> reportDtoOptional = reportRepository.getById(id);
        if (reportDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Report not found");
        }
        reportRepository.deleteById(id);
    }
}
