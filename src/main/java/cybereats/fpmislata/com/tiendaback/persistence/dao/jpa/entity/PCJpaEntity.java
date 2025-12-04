package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

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
    private String working_since;
    @ManyToOne
    @JoinColumn(name = "category__pc_id")
    private CategoryPCJpaEntity category;

    public PCJpaEntity() { }

    public PCJpaEntity(Long id, String label, String slug, int runtime, String specs, String working_since) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.runtime = runtime;
        this.specs = specs;
        this.working_since = working_since;
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

    public String getWorking_since() {
        return working_since;
    }
}
