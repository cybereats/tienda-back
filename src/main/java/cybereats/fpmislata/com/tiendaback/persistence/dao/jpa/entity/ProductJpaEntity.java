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
    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_product_id")
    private CategoryProductJpaEntity categoryProductJpaEntity;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderItemJpaEntity> orderItems = new java.util.ArrayList<>();

    public ProductJpaEntity() {
    }

    public ProductJpaEntity(Long id, String label, String slug, String description, BigDecimal price, String image,
            CategoryProductJpaEntity categoryProductJpaEntity) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryProductJpaEntity getCategoryProductJpaEntity() {
        return categoryProductJpaEntity;
    }
}
