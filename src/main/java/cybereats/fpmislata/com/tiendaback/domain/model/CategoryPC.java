package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;
import java.util.List;

import cybereats.fpmislata.com.tiendaback.exception.BusinessException;

public class CategoryPC {
    private Long id;
    private String label;
    private String slug;
    private BigDecimal price;

    private CategoryPC(Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.slug = builder.slug;
        this.price = builder.price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public static class Builder {
        private Long id;
        private String label;
        private String slug;
        private BigDecimal price;

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

        public Builder price(BigDecimal price) {
            if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("Price must be a positive number");
            }
            this.price = price;
            return this;
        }

        public CategoryPC build() {
            return new CategoryPC(this);
        }
    }
}
