package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.MedicosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicosServiceConfig {
    @Bean
    public MedicosService medicosService(MedicosRepository medicosRepository) {
        return new MedicosServiceImpl(medicosRepository);
    }
}
