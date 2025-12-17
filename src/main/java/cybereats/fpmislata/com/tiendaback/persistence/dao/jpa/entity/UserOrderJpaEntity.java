package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import cybereats.fpmislata.com.tiendaback.domain.model.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user_order")
public class UserOrderJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @OneToMany(mappedBy = "user_order_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJpaEntity> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private java.time.LocalDateTime createdAt;

    public UserOrderJpaEntity() {
    }

    public UserOrderJpaEntity(Long id, UserJpaEntity user, List<OrderItemJpaEntity> orderItems,
            OrderStatus status, java.time.LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserJpaEntity getUser() {
        return user;
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }

    public List<OrderItemJpaEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemJpaEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
