package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.IngresoAltasRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngresosAltasServiceConfig {

    @Bean
    public IngresosAltasService ingresosAltasService(IngresoAltasRepository ingresoAltasRepository) {
        return new IngresosAltasServiceImpl(ingresoAltasRepository);
    }
}
