package com.ClinicaSaludable.ClinicaSaludable.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Opcional: Asigna el código 404 automáticamente
public class SqlDataException extends RuntimeException {
    public SqlDataException(String message) {
        super(message);
    }

    public SqlDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
