package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String label;
    private String slug;
    private String desc;
    private BigDecimal price;
    private CategoryProduct categoryProduct;

    private Product(Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.slug = builder.slug;
        this.desc = builder.desc;
        this.price = builder.price;
        this.categoryProduct = builder.categoryProduct;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getSlug() {
        return slug;
    }

    public String getDesc() {
        return desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public static class Builder {
        private Long id;
        private String label;
        private String slug;
        private String desc;
        private BigDecimal price;
        private CategoryProduct categoryProduct;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder categoryProduct(CategoryProduct categoryProduct) {
            this.categoryProduct = categoryProduct;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
