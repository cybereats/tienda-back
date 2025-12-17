package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;

import java.util.Optional;

public interface PCRepository {
    Page<PCDto> findAll(int page, int size);
    Optional<PCDto> findById(Long id);
    Optional<PCDto> findBySlug(String slug);
    PCDto save(PCDto pcDto);
    void deleteById(Long id);
    void deleteBySlug(String slug);
    long count();
}
