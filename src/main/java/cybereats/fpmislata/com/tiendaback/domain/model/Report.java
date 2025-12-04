package cybereats.fpmislata.com.tiendaback.domain.model;

import java.lang.module.ModuleDescriptor.Builder;

public class Report {
    private Long id;
    private String priority;
    private String desc;
    private User user;
    private Pc pc;

    private Report(Builder builder) {
        this.id = builder.id;
        this.priority = builder.priority;
        this.desc = builder.desc;
        this.user = builder.user;
        this.pc = builder.pc;
    }

    public Long getId() {
        return id;
    }

    public String getPriority() {
        return priority;
    }

    public String getDesc() {
        return desc;
    }

    public User getUser() {
        return user;
    }

    public Pc getPc() {
        return pc;
    }

    public static class Builder {
        private Long id;
        private String priority;
        private String desc;
        private User user;
        private Pc pc;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder pc(Pc pc) {
            this.pc = pc;
            return this;
        }

        public Report build() {
            return new Report(id, priority, desc, user, pc);
        }
    }

}
