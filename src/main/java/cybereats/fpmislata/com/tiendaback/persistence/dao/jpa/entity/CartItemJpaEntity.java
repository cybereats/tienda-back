package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
public class CartItemJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cart_id")
  private CartJpaEntity cart;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private ProductJpaEntity product;

  private Integer quantity;

  private BigDecimal price;

  public CartItemJpaEntity() {
  }

  public CartItemJpaEntity(Long id, CartJpaEntity cart, ProductJpaEntity product, Integer quantity, BigDecimal price) {
    this.id = id;
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CartJpaEntity getCart() {
    return cart;
  }

  public void setCart(CartJpaEntity cart) {
    this.cart = cart;
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
