package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public record Cama(
        Integer id_cama,
        @Min(value = 0,message = "el numero de cama no puede ser negativo")
        Integer numero_cama,
        String estado,
        LocalDateTime fecha_disponibilidad,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion,
        LocalDateTime fecha_inicio_cita,        //objeto java que no esta en bd para el control de fecha hora
        LocalDateTime fecha_final_cita          //objeto java que no esta en bd para el control de fecha hora
) {
}
