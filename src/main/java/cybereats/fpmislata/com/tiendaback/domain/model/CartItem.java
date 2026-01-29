package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;

public class CartItem {
  private Long id;
  private Long productId;
  private Product product;
  private int quantity;
  private BigDecimal price;

  private CartItem(Builder builder) {
    this.id = builder.id;
    this.productId = builder.productId;
    this.product = builder.product;
    this.quantity = builder.quantity;
    this.price = builder.price;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
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
    private Long productId;
    private Product product;
    private int quantity;
    private BigDecimal price;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder productId(Long productId) {
      this.productId = productId;
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

    public Builder price(BigDecimal price) {
      this.price = price;
      return this;
    }

    public CartItem build() {
      return new CartItem(this);
    }
  }
}
