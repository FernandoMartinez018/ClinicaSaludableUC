package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record HistoriaClinica(
        Integer id_historia_clinica,
        @NotNull(message = "el campo pacientes_fk no puede ser nulo")
        Integer pacientes_fk,
        @NotNull(message = "el campo medico_fk no puede ser nulo")
        Integer medico_fk,
        @NotNull(message = "el campo fecha_historia no puede ser nulo")
        Date fecha_historia,
        @NotNull(message = "el campo diagnostico no puede ser nulo")
        String diagnostico,
        @NotNull(message = "el campo tratamiento no puede ser nulo")
        String tratamiento,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
