package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class CategoryPC {
    private Long id;
    private String label;
    private BigDecimal price;

    private CategoryPC(Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.price = builder.price;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class Builder {
        private Long id;
        private String label;
        private BigDecimal price;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CategoryPC build() {
            return new CategoryPC(this);
        }
    }
}
