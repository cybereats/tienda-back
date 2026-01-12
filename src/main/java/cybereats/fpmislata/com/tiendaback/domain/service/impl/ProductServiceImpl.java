package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.mapper.ProductMapper;
import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.model.Product;
import cybereats.fpmislata.com.tiendaback.domain.repository.ProductRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.ProductService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findAll(int page, int size) {
        Page<ProductDto> productDtoPage = productRepository.findAll(page, size);

        return new Page<>(
                productDtoPage.data(),
                productDtoPage.pageNumber(),
                productDtoPage.pageSize(),
                productDtoPage.totalElements());
    }

    @Override
    public ProductDto getBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Optional<ProductDto> findBySlug(String slug) {
        return Optional.ofNullable(
                productRepository.findBySlug(slug)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found")));
    }

    @Override
    public Page<ProductDto> search(String text, String category, int page, int size) {
        return productRepository.search(text, category, page, size);
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto productDto) {
        Optional<ProductDto> productDtoOptional = productRepository.findBySlug(productDto.slug());
        if (productDtoOptional.isPresent()) {
            throw new BusinessException("Product already exists");
        }
        Product product = ProductMapper.getInstance().fromProductDtoToProduct(productDto);
        return productRepository.save(ProductMapper.getInstance().fromProductToProductDto(product));
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {
        Optional<ProductDto> productDtoOptional = productRepository.findBySlug(productDto.slug());
        if (productDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }
        Product product = ProductMapper.getInstance().fromProductDtoToProduct(productDto);
        return productRepository.save(ProductMapper.getInstance().fromProductToProductDto(product));
    }

    @Override
    @Transactional
    public void deleteBySlug(String slug) {
        Optional<ProductDto> productDtoOptional = productRepository.findBySlug(slug);
        if (productDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteBySlug(slug);
    }
}
