package com.proyect.artyhub_back.domain.service.presentationDto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record UserPresentacionDto(
        @NotNull(message = "La ID del usuario no puede ser nula")
        Long id,
        @NotNull(message = "El nombre de usuario no puede ser nulo")
        String name,
        @NotNull(message = "El email del usuario no puede ser nulo")
        String email,
        @NotNull(message = "La contraseña del usuario no puede ser nula")
        String password,
        @Size(min = 24, max = 24)
        String nAccount,
        @Size(max = 250)
        String description,
        String address,
        String imageProfileUrl
) {
}
