package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "log")
public class LogJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String info;
    private String timestamp;

    public LogJpaEntity() { }

    public LogJpaEntity(Long id, String info, String timestamp) {
        this.id = id;
        this.info = info;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
