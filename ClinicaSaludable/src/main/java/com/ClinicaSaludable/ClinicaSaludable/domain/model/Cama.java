package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public record Cama(
        Integer id_cama,
        @Min(value = 0,message = "el numero de cama no puede ser negativo")
        Integer numero_cama,
        String estado,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
