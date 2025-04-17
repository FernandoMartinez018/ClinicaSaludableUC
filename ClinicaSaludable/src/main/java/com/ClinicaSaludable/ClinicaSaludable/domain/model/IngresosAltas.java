package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record IngresosAltas(
        Integer id_ingreso_altas,
        @NotNull(message = "el campo pacientes_fk no puede ser nulo")
        Integer pacientes_fk,
        @NotNull(message = "el campo camas_fk no puede ser nulo")
        Integer camas_fk,
        @NotNull(message = "el campo fecha_ingreso no puede ser nulo")
        LocalDateTime fecha_ingreso,
        Integer fecha_alta,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
