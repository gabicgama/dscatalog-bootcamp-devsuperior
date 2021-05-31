package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Definindo uma classe de configuração

@Configuration
public class AppConfig {

	/*
	 * Essa notação faz com que a instancia de BCryptPassword seja um componente
	 * gerenciado pelo spring. Sendo possivel fazer a injeção do mesmo em outros
	 * componentes.
	 */

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
