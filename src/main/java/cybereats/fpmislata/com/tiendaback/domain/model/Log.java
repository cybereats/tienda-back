package cybereats.fpmislata.com.tiendaback.domain.model;

public class Log {
    private Long id;
    private String info;
    private String timestamp;

    private Log(Builder builder) {
        this.id = builder.id;
        this.info = builder.info;
        this.timestamp = builder.timestamp;
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

    public static class Builder {
        private Long id;
        private String info;
        private String timestamp;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Log build() {
            return new Log(this);
        }
    }
}
