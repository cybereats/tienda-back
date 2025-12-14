package cybereats.fpmislata.com.tiendaback.domain.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.Token;

import java.time.LocalDateTime;

public class TokenMapper {

    private static TokenMapper instance;

    private TokenMapper() {
    }

    public static TokenMapper getInstance() {
        if (instance == null) {
            instance = new TokenMapper();
        }
        return instance;
    }

    public Token fromStringToToken(String tokenString, LocalDateTime expiresAt) {
        return new Token.Builder()
                .token(tokenString)
                .expiresAt(expiresAt)
                .build();
    }
}
