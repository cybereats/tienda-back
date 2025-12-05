package cybereats.fpmislata.com.tiendaback.presentation.webModel.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UserResponse(
        Long id,
        String name,
        String surname,
        @JsonFormat(pattern = "dd-MM-yyyy") String bornDate,
        String username,
        String password) {
}
