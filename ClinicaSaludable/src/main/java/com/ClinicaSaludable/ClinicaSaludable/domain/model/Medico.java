package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Medico(
        Integer id_medico,
        @NotNull(message = "La llave foránea del usuario es obligatoria")
        Integer usuario_fk,
        @NotNull(message = "el campo especialidad no puede ser nulo")
        String especialidad,
        String nombre,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
