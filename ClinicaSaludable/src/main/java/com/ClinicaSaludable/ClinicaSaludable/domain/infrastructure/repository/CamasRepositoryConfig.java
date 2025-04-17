package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamasRepositoryConfig {

    @Bean
    public CamasRepository camasRepository(QueryManager queryManager) {
        return new CamasRepositoryImpl(queryManager);
    }
}
