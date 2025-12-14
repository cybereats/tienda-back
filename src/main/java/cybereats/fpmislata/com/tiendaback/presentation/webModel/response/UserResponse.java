package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;

public record UserResponse(
        Long id,
        String name,
        String surname,
        String email,
        @JsonFormat(pattern = "dd-MM-yyyy") String bornDate,
        String username,
        String password,
        UserRole role) {
}
