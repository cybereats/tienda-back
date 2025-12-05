package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;

import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;

public interface CategoryProductService {

    Page<CategoryProductDto> findAll(int page, int size);

    CategoryProductDto getById(Long id);

    CategoryProductDto getBySlug(String slug);

    Optional<CategoryProductDto> findById(Long id);

    Optional<CategoryProductDto> findBySlug(String slug);

    CategoryProductDto insert(CategoryProductDto categoryProductDto);

    CategoryProductDto update(CategoryProductDto categoryProductDto);

    void deleteBySlug(String slug);
}
