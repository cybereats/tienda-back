package cybereats.fpmislata.com.tiendaback.domain.model;

public class Booking {
    private Long id;
    private int hours;
    private User user;
    private PC pc;

    private Booking(Builder builder) {
        this.id = builder.id;
        this.hours = builder.hours;
        this.user = builder.user;
        this.pc = builder.pc;
    }

    public Long getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public User getUser() {
        return user;
    }

    public PC getPc() {
        return pc;
    }

    public static class Builder {
        private Long id;
        private int hours;
        private User user;
        private PC pc;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder hours(int hours) {
            this.hours = hours;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder pc(PC pc) {
            this.pc = pc;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
