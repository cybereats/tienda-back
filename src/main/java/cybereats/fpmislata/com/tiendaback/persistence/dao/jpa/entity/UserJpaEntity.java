package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;

@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    @Column(name = "born_date")
    private String bornDate;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrderJpaEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "userJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingJpaEntity> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReportJpaEntity> reports = new ArrayList<>();

    public UserJpaEntity() {
    }

    public UserJpaEntity(Long id, String name, String surname, String email, String bornDate, String username,
            String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.bornDate = bornDate;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getBornDate() {
        return bornDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
