package com.ClinicaSaludable.ClinicaSaludable.domain.application.service;

import com.ClinicaSaludable.ClinicaSaludable.domain.infrastructure.repository.CitasMedicasRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CitasMedicasServiceConfig {

    @Bean
    public CitasMedicasService citasMedicasService(CitasMedicasRepository citasMedicasRepository) {
        return new CitasMedicasServiceImpl(citasMedicasRepository);
    }
}
