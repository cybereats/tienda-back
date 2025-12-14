package cybereats.fpmislata.com.tiendaback.util;

import cybereats.fpmislata.com.tiendaback.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "mi-cybereats-key";
    private static final long EXPIRATION_TIME = 7776000000L; // 90 días (esto está en milisegundos)

    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("name", user.getName());
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public static Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    public static LocalDateTime extractExpirationDate(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static String extractName(String token) {
        return extractAllClaims(token).get("name", String.class);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static LocalDateTime getExpirationTime() {
        return LocalDateTime.now().plusSeconds(EXPIRATION_TIME / 1000);
    }
}
