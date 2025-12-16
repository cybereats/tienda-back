package cybereats.fpmislata.com.tiendaback.security;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AllowedRoles allowedRoles = handlerMethod.getMethodAnnotation(AllowedRoles.class);

        if (allowedRoles == null) {
            allowedRoles = handlerMethod.getBeanType().getAnnotation(AllowedRoles.class);
        }

        if (allowedRoles == null) {
            return true;
        }

        String token = extractToken(request);
        if (token == null || !JwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String roleName = JwtUtil.extractRole(token);
        UserRole userRole;
        try {
            userRole = UserRole.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        List<UserRole> requiredRoles = Arrays.asList(allowedRoles.value());
        if (!requiredRoles.contains(userRole)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        return true;
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
