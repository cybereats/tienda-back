package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category_pc")
public class CategoryPCJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private BigDecimal price;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PCJpaEntity> pcs = new ArrayList<>();

    public CategoryPCJpaEntity() {
    }

    public CategoryPCJpaEntity(Long id, String label, BigDecimal price) {
        this.id = id;
        this.label = label;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getPrice() {
        return price;
    }
}