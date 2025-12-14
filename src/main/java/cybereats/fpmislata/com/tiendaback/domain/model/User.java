package cybereats.fpmislata.com.tiendaback.domain.model;

public class User {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String bornDate;
    private String username;
    private String password;
    private UserRole role;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.bornDate = builder.bornDate;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
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

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private String email;
        private String bornDate;
        private String username;
        private String password;
        private UserRole role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder bornDate(String bornDate) {
            this.bornDate = bornDate;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}