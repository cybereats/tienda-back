package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "units")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    @ManyToOne
    @JoinColumn(name = "user_order_id")
    private UserOrderJpaEntity userOrder;

    public OrderItemJpaEntity() {
    }

    public OrderItemJpaEntity(Long id, ProductJpaEntity product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductJpaEntity getProduct() {
        return product;
    }

    public void setProduct(ProductJpaEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
