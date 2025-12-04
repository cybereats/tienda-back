package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ProductJpaEntity;

public class ProductMapper {
    private static ProductMapper INSTANCE;

    private ProductMapper() {}

    public static ProductMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductMapper();
        }
        return INSTANCE;
    }

    public ProductDto productJpaEntityToProductDto(ProductJpaEntity productJpaEntity) {
        if (productJpaEntity == null) {
            return null;
        }

        return new ProductDto(
                productJpaEntity.getId(),
                productJpaEntity.getLabel(),
                productJpaEntity.getSlug(),
                productJpaEntity.getDesc(),
                productJpaEntity.getPrice()
        );
    }

    public ProductJpaEntity productDtoToProductJpaEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return new ProductJpaEntity(
                productDto.id(),
                productDto.label(),
                productDto.slug(),
                productDto.desc(),
                productDto.price()
        );
    }
}
