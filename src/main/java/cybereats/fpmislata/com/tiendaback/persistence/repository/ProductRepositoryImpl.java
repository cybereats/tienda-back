package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.ProductRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.ProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaDao productJpaDao;

    public ProductRepositoryImpl(ProductJpaDao productJpaDao) {
        this.productJpaDao = productJpaDao;
    }

    @Override
    public Page<ProductDto> findAll(int page, int size) {
        List<ProductDto> content = productJpaDao.findAll(page, size).stream()
                .map(productJpaEntity -> ProductMapper.getInstance().fromProductJpaEntityToProductDto(productJpaEntity))
                .toList();
        long totalElements = productJpaDao.count();
        return new Page<>(content, page, size, totalElements);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productJpaDao.findById(id)
                .map(productJpaEntity -> ProductMapper.getInstance().fromProductJpaEntityToProductDto(productJpaEntity));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        ProductJpaEntity productJpaEntity = ProductMapper.getInstance().fromProductDtoToProductJpaEntity(productDto);

        if(productDto.id() == null) {
            return ProductMapper.getInstance().fromProductJpaEntityToProductDto(productJpaDao.insert(productJpaEntity));
        }

        return ProductMapper.getInstance().fromProductJpaEntityToProductDto(productJpaDao.update(productJpaEntity));
    }

    @Override
    public void deleteById(Long id) {
        productJpaDao.deleteById(id);
    }

    @Override
    public void deleteBySlug(String slug) {
        productJpaDao.deleteBySlug(slug);
    }

    @Override
    public Optional<ProductDto> findBySlug(String slug) {
        return productJpaDao.findBySlug(slug)
                .map(productJpaEntity -> ProductMapper.getInstance().fromProductJpaEntityToProductDto(productJpaEntity));
    }
}
