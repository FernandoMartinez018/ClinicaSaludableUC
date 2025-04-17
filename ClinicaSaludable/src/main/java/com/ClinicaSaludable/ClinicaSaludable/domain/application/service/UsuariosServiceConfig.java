package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.UsuariosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuariosServiceConfig {

    @Bean
    public UsuariosService usuariosService(UsuariosRepository usuariosRepository) {
        return new UsuariosServiceImpl(usuariosRepository);
    }
}
