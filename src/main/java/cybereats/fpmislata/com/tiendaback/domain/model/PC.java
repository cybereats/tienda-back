package cybereats.fpmislata.com.tiendaback.domain.model;

public class PC {
    private Long id;
    private String label;
    private String slug;
    private int runtime;
    private String specs;
    private String working_since;

    private PC(Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.slug = builder.slug;
        this.runtime = builder.runtime;
        this.specs = builder.specs;
        this.working_since = builder.working_since;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getSlug() {
        return slug;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getSpecs() {
        return specs;
    }

    public String getWorking_since() {
        return working_since;
    }

    public static class Builder {
        private Long id;
        private String label;
        private String slug;
        private int runtime;
        private String specs;
        private String working_since;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder runtime(int runtime) {
            this.runtime = runtime;
            return this;
        }

        public Builder specs(String specs) {
            this.specs = specs;
            return this;
        }

        public Builder working_since(String working_since) {
            this.working_since = working_since;
            return this;
        }

        public PC build() {
            return new PC(this);
        }
    }
}
