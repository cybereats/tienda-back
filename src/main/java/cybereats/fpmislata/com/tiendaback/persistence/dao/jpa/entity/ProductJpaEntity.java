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
    private String desc;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_product_id")
    private CategoryProductJpaEntity categoryProductJpaEntity;

    public ProductJpaEntity() { }

    public ProductJpaEntity(Long id, String label, String slug, String desc, BigDecimal price, CategoryProductJpaEntity categoryProductJpaEntity) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CategoryProductJpaEntity getCategoryProductJpaEntity() {
        return categoryProductJpaEntity;
    }
}
