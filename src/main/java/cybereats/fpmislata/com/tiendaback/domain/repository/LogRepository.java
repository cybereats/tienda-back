package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;

import java.util.Optional;

public interface LogRepository {
    Page<LogDto> findAll(int page, int size);
    Optional<LogDto> findById(Long id);
    LogDto save(LogDto logDto);
    void deleteById(Long id);
}
