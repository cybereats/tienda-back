
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import cybereats.fpmislata.com.tiendaback.domain.model.User;

public class VerifyJwtFix {
    public static void main(String[] args) {
        try {
            System.out.println("Attempting to initialize JwtUtil and generate token...");
            
            User user = new User.Builder()
                    .id(1L)
                    .username("testuser")
                    .name("Test User")
                    .build();
            
            String token = JwtUtil.generateToken(user);
            System.out.println("Token generated successfully: " + token.substring(0, 20) + "...");
            System.out.println("Verification SUCCESS");
        } catch (Throwable e) {
            System.out.println("Verification FAILED");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
