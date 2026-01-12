package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryProductRepository {
    List<CategoryProductDto> findAll();

    Page<CategoryProductDto> findAll(int page, int size);

    Optional<CategoryProductDto> findById(Long id);

    Optional<CategoryProductDto> findBySlug(String slug);

    CategoryProductDto save(CategoryProductDto categoryProductDto);

    void deleteBySlug(String slug);
}
