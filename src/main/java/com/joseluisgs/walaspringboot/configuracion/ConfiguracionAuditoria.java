package com.joseluisgs.walaspringboot.configuracion;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Clase de configuración de auditoria para que funcionen automaticamente las fechas
// Creandose e insertandose automaticamente las del sistema

@Configuration
@EnableJpaAuditing
public class ConfiguracionAuditoria {
}
