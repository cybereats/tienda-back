package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import java.util.List;
import java.util.Optional;

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
        return reportRepository.save(reportDto);
    }

    @Override
    @Transactional
    public ReportDto update(ReportDto reportDto) {
        Optional<ReportDto> reportDtoOptional = reportRepository.findById(reportDto.id());
        if (reportDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Report not found");
        }
        return reportRepository.save(reportDto);
    }

    @Override
    public List<ReportDto> findAll(int page, int size) {
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
