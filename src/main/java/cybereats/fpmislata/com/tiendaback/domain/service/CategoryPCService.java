package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;

import java.util.List;
import java.util.Optional;

public interface CategoryPCService {
    List<CategoryPCDto> findAll();

    Page<CategoryPCDto> findAll(int page, int size);

    CategoryPCDto getById(Long id);

    Optional<CategoryPCDto> findById(Long id);

    CategoryPCDto create(CategoryPCDto categoryPCDto);

    CategoryPCDto update(CategoryPCDto categoryPCDto);

    CategoryPCDto getBySlug(String slug);

    Optional<CategoryPCDto> findBySlug(String slug);

    void deleteById(Long id);
}
