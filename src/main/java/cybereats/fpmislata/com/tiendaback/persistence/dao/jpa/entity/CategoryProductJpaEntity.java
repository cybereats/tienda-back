package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_product")
public class CategoryProductJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String slug;

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
