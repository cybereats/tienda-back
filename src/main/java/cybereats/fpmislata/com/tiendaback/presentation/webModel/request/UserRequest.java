package cybereats.fpmislata.com.tiendaback.presentation.webModel.request;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UserRequest(
        Long id,
        String name,
        String surname,
        @JsonFormat(pattern = "dd-MM-yyyy") String born_date,
        String username,
        String password) {
}
