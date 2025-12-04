package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class UserOrder {
    private Long id;
    private User user;
    private List<OrderItem> orderItems;
    private BigDecimal totalPrice;
    private String status;

    private UserOrder(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.orderItems = builder.orderItems;
        this.totalPrice = orderItems.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private Long id;
        private User user;
        private List<OrderItem> orderItems;
        private String status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder orderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public UserOrder build() {
            return new UserOrder(this);
        }
    }
}
