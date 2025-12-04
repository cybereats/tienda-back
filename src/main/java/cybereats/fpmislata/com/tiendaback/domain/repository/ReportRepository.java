package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;

public interface ReportRepository {
    ReportDto save(ReportDto reportDto);

    List<ReportDto> getAll();

    List<ReportDto> getByUserId(Long userId);

    List<ReportDto> getByPCId(Long pcId);

    Optional<ReportDto> getById(Long id);

    void deleteById(Long id);
}
