package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

// @Configuration: Definindo uma classe de configuração

@Configuration
public class AppConfig {

	/*
	 * @Bean: Essa notação faz com que a instancia de BCryptPassword seja um
	 * componente gerenciado pelo Spring. Sendo possivel fazer a injeção do mesmo em
	 * outros componentes.
	 */

	/* BCrypt é um algoritmo que encripta senhas */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * Bean para token JWT (os dois abaixo). São objs capazes de acessar um token
	 * JWT. Acessar = ler um token, decodificar um token, criar um token, etc
	 */

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("MY-JWT-SECRET"); // assinatura do token
		return tokenConverter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
