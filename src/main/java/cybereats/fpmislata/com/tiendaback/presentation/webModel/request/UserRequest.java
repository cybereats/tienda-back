package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;

public record UserRequest(
        Long id,
        String name,
        String surname,
        String email,
        @JsonFormat(pattern = "dd-MM-yyyy") String born_date,
        String username,
        String password,
        UserRole role) {
}
