package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import cybereats.fpmislata.com.tiendaback.domain.model.ReportStatus;

@Entity
@Table(name = "report")
public class ReportJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer priority;
    @Column(name = "description")
    private String description;
    private String subject;
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    @Column(name = "created_at")
    private String createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;
    @ManyToOne
    @JoinColumn(name = "pc_id")
    private PCJpaEntity pc;

    public PCJpaEntity getPc() {
        return pc;
    }

    public void setPc(PCJpaEntity pc) {
        this.pc = pc;
    }

    public ReportJpaEntity() {
    }

    public ReportJpaEntity(Long id, Integer priority, String description, String subject, ReportStatus status,
            String createdAt,
            UserJpaEntity user, PCJpaEntity pc) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.subject = subject;
        this.status = status;
        this.createdAt = createdAt;
        this.user = user;
        this.pc = pc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public UserJpaEntity getUser() {
        return user;
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }

}
