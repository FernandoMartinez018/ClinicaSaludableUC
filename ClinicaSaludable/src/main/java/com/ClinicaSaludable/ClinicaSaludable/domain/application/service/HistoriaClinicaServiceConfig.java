package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.HistoriaClinicaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoriaClinicaServiceConfig {

    @Bean
    public HistoriaClinicaService historiaClinicaService(HistoriaClinicaRepository historiaClinicaRepository) {
        return new HistoriaClinicaServiceImpl(historiaClinicaRepository);
    }
}
