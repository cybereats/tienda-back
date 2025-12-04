package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;

import java.util.Optional;

public interface PCService {
    Page<PCDto> getAll(int page, int size);
    PCDto getBySlug(String slug);
    Optional<PCDto> findBySlug(String slug);
    PCDto create(PCDto pcDto);
    PCDto update(PCDto pcDto);
    void deleteBySlug(String slug);
}
