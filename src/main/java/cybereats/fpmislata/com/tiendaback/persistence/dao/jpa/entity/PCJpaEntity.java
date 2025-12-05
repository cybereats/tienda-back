package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "pc")
public class PCJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String slug;
    private int runtime;
    private String specs;
    @Column(name = "working_since")
    private String workingSince;
    @ManyToOne
    @JoinColumn(name = "category_pc_id")
    private CategoryPCJpaEntity category;

    public PCJpaEntity() {
    }

    public PCJpaEntity(Long id, String label, String slug, int runtime, String specs, String workingSince,
            CategoryPCJpaEntity category) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.runtime = runtime;
        this.specs = specs;
        this.workingSince = workingSince;
        this.category = category;
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

    public int getRuntime() {
        return runtime;
    }

    public String getSpecs() {
        return specs;
    }

    public String getWorkingSince() {
        return workingSince;
    }

    public CategoryPCJpaEntity getCategory() {
        return category;
    }
}
