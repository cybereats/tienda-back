package cybereats.fpmislata.com.tiendaback.domain.model;

public class CategoryProduct {
    private Long id;
    private String name;
    private String slug;

    private CategoryProduct(CategoryProduct.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.slug = builder.slug;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String slug;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public CategoryProduct build() {
            return new CategoryProduct(this);
        }
    }
}
