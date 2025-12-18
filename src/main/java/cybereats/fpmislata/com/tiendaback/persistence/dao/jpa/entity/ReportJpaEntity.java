package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class ReportJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String priority;
    @Column(name = "`desc`")
    private String desc;
    private String subject;
    private String status;
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

    public ReportJpaEntity(Long id, String priority, String desc, String subject, String status, String createdAt,
            UserJpaEntity user, PCJpaEntity pc) {
        this.id = id;
        this.priority = priority;
        this.desc = desc;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
