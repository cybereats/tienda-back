package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_order")
public class UserOrderJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJpaEntity> orderItems;

    private String status;

    public UserOrderJpaEntity() {
    }

    public UserOrderJpaEntity(Long id, UserJpaEntity user, List<OrderItemJpaEntity> orderItems,
            String status) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
