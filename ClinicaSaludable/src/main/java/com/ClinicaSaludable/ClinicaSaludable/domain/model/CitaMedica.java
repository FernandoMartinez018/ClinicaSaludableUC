package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CitaMedica(
        Integer id_cita_medica,
        @NotNull(message = "el campo pacientes_fk no puede ser nulo")
        Integer pacientes_fk,
        @NotNull(message = "el campo medico_fk no puede ser nulo")
        Integer medico_fk,
        @NotNull(message = "el campo fecha_cita no puede ser nulo")
        LocalDateTime fecha_cita,
        @NotNull(message = "el campo estado_cita no puede ser nulo")
        String estado_cita,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
