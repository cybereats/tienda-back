package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;

import java.util.Optional;

public interface CategoryPCRepository {
    Page<CategoryPCDto> findAll(int page, int size);

    Optional<CategoryPCDto> findById(Long id);

    Optional<CategoryPCDto> findBySlug(String slug);

    CategoryPCDto save(CategoryPCDto categoryPCDto);

    void deleteById(Long id);
}
