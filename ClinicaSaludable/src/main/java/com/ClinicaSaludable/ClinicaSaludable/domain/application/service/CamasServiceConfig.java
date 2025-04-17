package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.CamasRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamasServiceConfig {

    @Bean
    public CamasService camasService(CamasRepository camasRepository) {
        return new CamasServiceImpl(camasRepository);
    }
}
