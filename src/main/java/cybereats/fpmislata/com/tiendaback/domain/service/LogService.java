package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;

import java.util.Optional;

public interface LogService {
    Page<LogDto> findAll(int page, int size);
    LogDto getById(Long id);
    Optional<LogDto> findById(Long id);
    LogDto create(LogDto logDto);
    LogDto update(LogDto logDto);
    void deleteById(Long id);
}
