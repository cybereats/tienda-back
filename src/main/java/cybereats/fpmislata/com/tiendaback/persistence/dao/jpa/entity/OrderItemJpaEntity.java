package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product_id;
    @ManyToOne
    @JoinColumn(name = "user_order_id")
    private UserOrderJpaEntity user_order_id;

    public OrderItemJpaEntity() {
    }

    public OrderItemJpaEntity(Long id, Integer quantity, ProductJpaEntity product_id,
            UserOrderJpaEntity user_order_id) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.user_order_id = user_order_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductJpaEntity getProduct() {
        return product_id;
    }

    public void setProduct(ProductJpaEntity product_id) {
        this.product_id = product_id;
    }

    public UserOrderJpaEntity getUser_order_id() {
        return user_order_id;
    }

    public void setUser_order_id(UserOrderJpaEntity user_order_id) {
        this.user_order_id = user_order_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
