package cybereats.fpmislata.com.tiendaback.domain.model;

public class Booking {
    private Long id;
    private int hours;

    private Booking(Builder builder) {
        this.id = builder.id;
        this.hours = builder.hours;
    }

    public Long getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public static class Builder {
        private Long id;
        private int hours;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder hours(int hours) {
            this.hours = hours;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
