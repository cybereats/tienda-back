package cybereats.fpmislata.com.tiendaback.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class CategoryPC {
    private Long id;
    private String label;
    private BigDecimal price;
    private List<PC> pc_list;

    private CategoryPC(CategoryPC.Builder builder) {
        this.id = builder.id;
        this.label = builder.label;
        this.price = builder.price;
        this.pc_list = builder.pc_list;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<PC> getPC_list() {
        return pc_list;
    }

    public static class Builder {
        private Long id;
        private String label;
        private BigDecimal price;
        private List<PC> pc_list;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder pc_list(List<PC> pc_list) {
            this.pc_list = pc_list;
            return this;
        }

        public CategoryPC build() {
            return new CategoryPC(this);
        }
    }
}
