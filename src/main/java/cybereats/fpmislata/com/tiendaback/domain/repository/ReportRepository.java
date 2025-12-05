package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;

public interface ReportRepository {
    ReportDto save(ReportDto reportDto);

    List<ReportDto> findAll(int page, int size);

    List<ReportDto> findByUserId(Long userId);

    List<ReportDto> findByPCId(Long pcId);

    Optional<ReportDto> findById(Long id);

    void deleteById(Long id);
}
