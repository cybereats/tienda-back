package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;

@Entity
@Table(name = "category_product")
public class CategoryProductJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String slug;
    @OneToMany(mappedBy = "categoryProductJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductJpaEntity> products = new ArrayList<>();

    public CategoryProductJpaEntity() {
    }

    public CategoryProductJpaEntity(Long id, String label, String slug) {
        this.id = id;
        this.label = label;
        this.slug = slug;
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
}
