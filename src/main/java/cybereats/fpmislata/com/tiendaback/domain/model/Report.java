package cybereats.fpmislata.com.tiendaback.domain.model;

public class Report {
    private Long id;
    private String priority;
    private String description;
    private String subject;
    private String status;
    private String createdAt;
    private User user;
    private PC pc;

    private Report(Builder builder) {
        this.id = builder.id;
        this.priority = builder.priority;
        this.description = builder.description;
        this.subject = builder.subject;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.user = builder.user;
        this.pc = builder.pc;
    }

    public Long getId() {
        return id;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public PC getPC() {
        return pc;
    }

    public static class Builder {
        private Long id;
        private String priority;
        private String description;
        private String subject;
        private String status;
        private String createdAt;
        private User user;
        private PC pc;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
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

        public Report build() {
            return new Report(this);
        }
    }

}
