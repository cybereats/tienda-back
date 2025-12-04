package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;

public class OrderItem {
    Long id;
    Product product;
    int quantity;
    BigDecimal price;

    private OrderItem(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.price = this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class Builder {
        private Long id;
        private Product product;
        private int quantity;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
