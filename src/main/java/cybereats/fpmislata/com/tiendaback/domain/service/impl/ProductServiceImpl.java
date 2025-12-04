package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.ProductRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.ProductService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> getAll(int page, int size) {
        Page<ProductDto> productDtoPage = productRepository.findAll(page, size);

        return new Page<>(
                productDtoPage.data(),
                productDtoPage.pageNumber(),
                productDtoPage.pageSize(),
                productDtoPage.totalElements()
        );
    }

    @Override
    public ProductDto getBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Optional<ProductDto> findBySlug(String slug) {
        return Optional.ofNullable(productRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return productRepository.save(productDto);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        return productRepository.save(productDto);
    }

    @Override
    public void deleteBySlug(String slug) {
        productRepository.deleteBySlug(slug);
    }
}
