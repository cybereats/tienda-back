package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "booking")
public class BookingJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int hours;
    @ManyToOne
    @JoinColumn(name = "pc_id")
    private PCJpaEntity pcJpaEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    public BookingJpaEntity() { }

    public BookingJpaEntity(Long id, int hours) {
        this.id = id;
        this.hours = hours;
    }

    public Long getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }
}
