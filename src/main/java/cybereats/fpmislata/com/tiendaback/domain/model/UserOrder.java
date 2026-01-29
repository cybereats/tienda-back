package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class UserOrder {
    private Long id;
    private User user;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    private DeliveryType deliveryType;
    private LocalDateTime createdAt;

    private UserOrder(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.orderItems = builder.orderItems;
        this.status = builder.status;
        this.deliveryType = builder.deliveryType;
        this.createdAt = builder.createdAt;
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

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static class Builder {
        private Long id;
        private User user;
        private List<OrderItem> orderItems;
        private OrderStatus status;
        private DeliveryType deliveryType;
        private LocalDateTime createdAt;

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

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder deliveryType(DeliveryType deliveryType) {
            this.deliveryType = deliveryType;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserOrder build() {
            return new UserOrder(this);
        }
    }
}
