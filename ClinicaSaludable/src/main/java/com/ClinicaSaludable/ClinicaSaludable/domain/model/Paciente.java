package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record Paciente(
        Integer id_paciente,
        @NotNull(message = "el campo usuario_fk no puede ser nulo")
        Integer usuario_fk,
        @NotNull(message = "el campo fecha_nacimiento")
        LocalDateTime fecha_nacimiento,
        String direccion,
        @Pattern(regexp = "\\d{7,15}", message = "telefono_contacto, Teléfono debe tener entre 7 y 15 dígitos")
        String telefono_contacto,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
