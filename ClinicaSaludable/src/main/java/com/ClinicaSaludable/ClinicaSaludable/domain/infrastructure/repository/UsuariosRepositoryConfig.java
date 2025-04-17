package com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository;

import com.gerleinco.databasemanager.QueryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuariosRepositoryConfig {

    @Bean
    public UsuariosRepository usuariosRepository(QueryManager queryManager) {
        return new UsuariosRepositoryImpl(queryManager);
    }
}
