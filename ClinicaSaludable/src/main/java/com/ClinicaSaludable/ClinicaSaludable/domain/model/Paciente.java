package com.ClinicaSaludable.ClinicaSaludable.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.Date;

public record Paciente(
        Integer id_paciente,
        @NotNull(message = "el campo usuario_fk no puede ser nulo")
        Integer usuario_fk,
        @NotNull(message = "el campo fecha_nacimiento")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date fecha_nacimiento,
        String direccion,
        @Pattern(regexp = "\\d{7,15}", message = "telefono_contacto, Teléfono debe tener entre 7 y 15 dígitos")
        String telefono_contacto,
        LocalDateTime fecha_insercion,
        String usuario_insercion,
        LocalDateTime fecha_modificacion,
        String usuario_modificacion
) {
}
