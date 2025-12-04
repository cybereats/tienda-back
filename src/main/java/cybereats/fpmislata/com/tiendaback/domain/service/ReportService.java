package cybereats.fpmislata.com.tiendaback.domain.service;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;

public interface ReportService {
    ReportDto insert(ReportDto reportDto);

    ReportDto update(ReportDto reportDto);

    List<ReportDto> getAll();

    List<ReportDto> getByUserId(Long userId);

    List<ReportDto> getByPcId(Long pcId);

    Optional<ReportDto> getById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);
}
