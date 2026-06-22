package com.ipartek.formacion.ejemplos.amazonia.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

	// AUTENTICACIÓN: demuestra quien eres
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		var jdbc = new JdbcUserDetailsManager(dataSource);
		
		jdbc.setUsersByUsernameQuery("SELECT email, password, 1 FROM usuarios WHERE email=?");
		jdbc.setAuthoritiesByUsernameQuery("""
				SELECT u.email, CONCAT('ROLE_', r.nombre)
				FROM usuarios u 
				JOIN roles r ON r.id = u.rol_id 
				WHERE u.email=?
				""");
		
		return jdbc; // new JdbcUserDetailsManager(dataSource);
	}

	// AUTORIZACIÓN: entonces te permito hacer esto
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		// @formatter:off
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
				.anyRequest().permitAll()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout(LogoutConfigurer::permitAll);
		// @formatter:on

		return http.build();
	}

}