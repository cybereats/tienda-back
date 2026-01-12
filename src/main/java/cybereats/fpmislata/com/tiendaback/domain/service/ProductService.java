package cybereats.fpmislata.com.tiendaback.domain.service;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;

import java.util.Optional;

public interface ProductService {
    Page<ProductDto> findAll(int page, int size);

    ProductDto getBySlug(String slug);

    Optional<ProductDto> findBySlug(String slug);

    Page<ProductDto> search(String text, String category, int page, int size);

    ProductDto create(ProductDto productoDto);

    ProductDto update(ProductDto productoDto);

    void deleteBySlug(String slug);
}
