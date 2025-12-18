package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Product;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;

public class ProductMapper {
    private static ProductMapper INSTANCE;

    private ProductMapper() {
    }

    public static ProductMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductMapper();
        }
        return INSTANCE;
    }

    public ProductDto fromProductToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDto(
                product.getId(),
                product.getLabel(),
                product.getSlug(),
                product.getDescription(),
                product.getPrice(),
                CategoryProductMapper.getInstance()
                        .fromCategoryProductToCategoryProductDto(product.getCategoryProduct()));
    }

    public Product fromProductDtoToProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return new Product.Builder()
                .id(productDto.id())
                .label(productDto.label())
                .slug(productDto.slug())
                .description(productDto.description())
                .price(productDto.price())
                .categoryProduct(CategoryProductMapper.getInstance()
                        .fromCategoryProductDtoToCategoryProduct(productDto.category()))
                .build();
    }
}
