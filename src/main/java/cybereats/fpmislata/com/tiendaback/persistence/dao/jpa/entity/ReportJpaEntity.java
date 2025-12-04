package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class ReportJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String priority;
    private String desc;
    private UserJpaEntity user;
    private PCJpaEntity pc;

    public ReportJpaEntity() {
    }

    public ReportJpaEntity(Long id, String priority, String desc, UserJpaEntity user, PCJpaEntity pc) {
        this.id = id;
        this.priority = priority;
        this.desc = desc;
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

    public UserJpaEntity getUser() {
        return user;
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }

    public PCJpaEntity getPC() {
        return pc;
    }

    public void setPC(PCJpaEntity pc) {
        this.pc = pc;
    }

}
