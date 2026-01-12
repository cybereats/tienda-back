package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;

import java.util.Optional;

public interface PCService {
    Page<PCDto> findAll(int page, int size);

    PCDto getBySlug(String slug);

    Optional<PCDto> findBySlug(String slug);

    Page<PCDto> search(String text, String category, int page, int size);

    PCDto create(PCDto pcDto);

    PCDto update(PCDto pcDto);

    void deleteBySlug(String slug);
}
