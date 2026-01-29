package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", unique = true)
  private UserJpaEntity user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItemJpaEntity> items = new ArrayList<>();

  public CartJpaEntity() {
  }

  public CartJpaEntity(Long id, UserJpaEntity user, List<CartItemJpaEntity> items) {
    this.id = id;
    this.user = user;
    this.items = items;
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

  public List<CartItemJpaEntity> getItems() {
    return items;
  }

  public void setItems(List<CartItemJpaEntity> items) {
    this.items = items;
  }

  public void addItem(CartItemJpaEntity item) {
    items.add(item);
    item.setCart(this);
  }

  public void removeItem(CartItemJpaEntity item) {
    items.remove(item);
    item.setCart(null);
  }

  public void clearItems() {
    items.forEach(item -> item.setCart(null));
    items.clear();
  }
}
