package cybereats.fpmislata.com.tiendaback.domain.model;

public class PC {
    private Long id;
    private String label;
    private String slug;
    private int runtime;
    private String specs;
    private String workingSince;
    private String image;
    private CategoryPC categoryPC;

    private PC(Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.slug = builder.slug;
        this.runtime = builder.runtime;
        this.specs = builder.specs;
        this.workingSince = builder.workingSince;
        this.image = builder.image;
        this.categoryPC = builder.categoryPC;
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

    public String getWorkingSince() {
        return workingSince;
    }

    public String getImage() {
        return image;
    }

    public CategoryPC getCategoryPC() {
        return categoryPC;
    }

    public static class Builder {
        private Long id;
        private String label;
        private String slug;
        private int runtime;
        private String specs;
        private String workingSince;
        private String image;
        private CategoryPC categoryPC;

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

        public Builder workingSince(String workingSince) {
            this.workingSince = workingSince;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder categoryPC(CategoryPC categoryPC) {
            this.categoryPC = categoryPC;
            return this;
        }

        public PC build() {
            return new PC(this);
        }
    }
}
