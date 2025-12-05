package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_product")
public class CategoryProductJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "label")
    private String name;
    private String slug;

    public CategoryProductJpaEntity() {
    }

    public CategoryProductJpaEntity(Long id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
