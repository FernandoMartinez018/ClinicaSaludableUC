package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacientesRepositoryConfig {
    @Bean
    PacientesRepository pacientesRepository(QueryManager queryManager) {
        return new PacientesRepositoryImpl(queryManager);
    }
}
