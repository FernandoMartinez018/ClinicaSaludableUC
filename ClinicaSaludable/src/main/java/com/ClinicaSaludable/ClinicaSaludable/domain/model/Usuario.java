package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record Usuario(
        Integer id_usuario,
        @NotNull(message = "el campo nombre no puede ser nulo")
        String nombre,
        @Email(message = "el email digitado no es válido")
        @NotNull(message = "el campo email no puede ser nulo")
        String email,
        @NotNull(message = "el campo password no puede ser nulo")
        String password,
        @NotNull(message = "el campo rol no puede ser nulo")
        String rol,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
