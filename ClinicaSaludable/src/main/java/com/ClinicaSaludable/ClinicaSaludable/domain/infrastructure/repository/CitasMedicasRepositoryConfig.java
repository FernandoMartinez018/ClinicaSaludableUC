package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CitasMedicasRepositoryConfig {

    @Bean
    public CitasMedicasRepository citasMedicasRepository(QueryManager queryManager) {
        return new CitasMedicasRepositoryImpl(queryManager);
    }
}
