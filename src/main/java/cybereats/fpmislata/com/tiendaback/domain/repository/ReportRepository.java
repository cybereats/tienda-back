package cybereats.fpmislata.com.tiendaback.domain.repository;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;

public interface ReportRepository {
    ReportDto save(ReportDto reportDto);

    Page<ReportDto> findAll(int page, int size);

    List<ReportDto> findByUserId(Long userId);

    List<ReportDto> findByPCId(Long pcId);

    List<ReportDto> findByStatus(String status);

    List<ReportDto> findByCreatedAt(String createdAt);

    Optional<ReportDto> findById(Long id);

    void deleteById(Long id);
}
