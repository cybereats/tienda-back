package cybereats.fpmislata.com.tiendaback.domain.service;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;

public interface ReportService {
    ReportDto insert(ReportDto reportDto);

    ReportDto update(ReportDto reportDto);

    List<ReportDto> findAll(int page, int size);

    List<ReportDto> findByUserId(Long userId);

    List<ReportDto> findByPCId(Long pcId);

    ReportDto getById(Long id);

    Optional<ReportDto> findById(Long id);

    void deleteById(Long id);

}
