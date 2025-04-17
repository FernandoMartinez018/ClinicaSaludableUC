package com.ClinicaSaludable.ClinicaSaludable.database;

import com.gerleinco.databasemanager.QueryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración de Spring para la gestión de consultas a la base de datos.
 *
 * Define un `QueryManager` como un bean de Spring para que pueda ser inyectado en otras partes de la aplicación.
 */
@Configuration
public class QueryManagerConfig {
    /**
     * Define un bean de `QueryManager` que utiliza una instancia de `ArasDB` como fuente de datos.
     *
     * @return Una nueva instancia de `QueryManager` configurada con la conexión a la base de datos.
     */
    @Bean
    public QueryManager queryManager() {
        return new QueryManager(new ClinicaSaludableDB());
    }
}
