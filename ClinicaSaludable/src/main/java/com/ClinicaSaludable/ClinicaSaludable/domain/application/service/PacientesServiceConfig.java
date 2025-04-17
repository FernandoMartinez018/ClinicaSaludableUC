package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.PacientesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacientesServiceConfig {

    @Bean
    public PacientesService pacientesService(PacientesRepository pacientesRepository) {
        return new PacientesServiceImpl(pacientesRepository);
    }
}
