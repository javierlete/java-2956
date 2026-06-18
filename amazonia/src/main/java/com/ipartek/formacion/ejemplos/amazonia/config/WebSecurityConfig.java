package com.ipartek.formacion.ejemplos.amazonia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

	// AUTENTICACIÓN: demuestra quien eres
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails javier = User.withUsername("javier@email.net").password(encoder.encode("javier")).roles("ADMINISTRADOR").build();
		UserDetails pepe = User.withUsername("pepe@email.net").password(encoder.encode("pepe")).roles("USUARIO").build();

		return new InMemoryUserDetailsManager(javier, pepe);
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
				// .loginPage("/login")
				.permitAll()
			)
			.logout(LogoutConfigurer::permitAll);
		// @formatter:on

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}