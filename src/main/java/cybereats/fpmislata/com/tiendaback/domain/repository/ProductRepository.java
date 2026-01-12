package cybereats.fpmislata.com.tiendaback.domain.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;

import java.util.Optional;

public interface ProductRepository {
    Page<ProductDto> findAll(int page, int size);

    Optional<ProductDto> findById(Long id);

    Optional<ProductDto> findBySlug(String slug);

    Page<ProductDto> search(String text, String category, int page, int size);

    ProductDto save(ProductDto productDto);

    void deleteById(Long id);

    void deleteBySlug(String slug);
}
