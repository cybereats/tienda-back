package cybereats.fpmislata.com.tiendaback.domain.model;

import java.time.LocalDateTime;

public class Token {

    private String token;
    private LocalDateTime expiresAt;

    private Token(Builder builder) {
        this.token = builder.token;
        this.expiresAt = builder.expiresAt;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public static class Builder {
        private String token;
        private LocalDateTime expiresAt;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Token build() {
            return new Token(this);
        }
    }
}
