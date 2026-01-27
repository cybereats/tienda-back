package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

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
    private String image;
    private String status;
    @ManyToOne
    @JoinColumn(name = "category_pc_id")
    private CategoryPCJpaEntity category;

    @OneToMany(mappedBy = "pcJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingJpaEntity> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportJpaEntity> reports = new ArrayList<>();

    public PCJpaEntity() {
    }

    public PCJpaEntity(Long id, String label, String slug, int runtime, String specs, String workingSince, String image,
            String status,
            CategoryPCJpaEntity category) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.runtime = runtime;
        this.specs = specs;
        this.workingSince = workingSince;
        this.image = image;
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public CategoryPCJpaEntity getCategory() {
        return category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
