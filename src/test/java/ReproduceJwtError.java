
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

public class ReproduceJwtError {
    public static void main(String[] args) {
        try {
            String SECRET_KEY = "mi-cybereats-key";
            System.out.println("Key length: " + SECRET_KEY.getBytes(StandardCharsets.UTF_8).length + " bytes");
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            System.out.println("Key generated successfully");
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getClass().getName());
            e.printStackTrace();
        }
    }
}
