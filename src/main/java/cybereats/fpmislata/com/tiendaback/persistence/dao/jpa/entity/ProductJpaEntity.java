package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String slug;
    @Column(name = "description")
    private String description;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_product_id")
    private CategoryProductJpaEntity categoryProductJpaEntity;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderItemJpaEntity> orderItems = new java.util.ArrayList<>();

    public ProductJpaEntity() {
    }

    public ProductJpaEntity(Long id, String label, String slug, String description, BigDecimal price,
            CategoryProductJpaEntity categoryProductJpaEntity) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.categoryProductJpaEntity = categoryProductJpaEntity;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CategoryProductJpaEntity getCategoryProductJpaEntity() {
        return categoryProductJpaEntity;
    }
}
