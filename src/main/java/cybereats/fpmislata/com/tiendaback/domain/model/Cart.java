package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
  private Long id;
  private User user;
  private List<CartItem> items;

  private Cart(Builder builder) {
    this.id = builder.id;
    this.user = builder.user;
    this.items = builder.items != null ? builder.items : new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public int getTotalItems() {
    return items.stream().mapToInt(CartItem::getQuantity).sum();
  }

  public BigDecimal getTotalPrice() {
    return items.stream()
        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public static class Builder {
    private Long id;
    private User user;
    private List<CartItem> items;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder user(User user) {
      this.user = user;
      return this;
    }

    public Builder items(List<CartItem> items) {
      this.items = items;
      return this;
    }

    public Cart build() {
      return new Cart(this);
    }
  }
}
