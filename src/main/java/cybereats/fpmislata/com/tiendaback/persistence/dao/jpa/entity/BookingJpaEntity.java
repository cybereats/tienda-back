package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "created_at")
    @CreationTimestamp
    private java.time.LocalDateTime createdAt;

    public BookingJpaEntity() {
    }

    public BookingJpaEntity(Long id, int hours, UserJpaEntity userJpaEntity, PCJpaEntity pcJpaEntity, java.time.LocalDateTime createdAt) {
        this.id = id;
        this.hours = hours;
        this.pcJpaEntity = pcJpaEntity;
        this.userJpaEntity = userJpaEntity;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public PCJpaEntity getPcJpaEntity() {
        return pcJpaEntity;
    }

    public UserJpaEntity getUserJpaEntity() {
        return userJpaEntity;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
