package com.ipartek.formacion.ejemplos.amazonia.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ipartek.formacion.ejemplos.amazonia.repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
	private final UsuarioRepository usuarioRepository;

	// AUTENTICACIÓN: demuestra quien eres
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		return email -> {
			var usuario = usuarioRepository.findByEmail(email);

			if (usuario.isEmpty()) {
				throw new UsernameNotFoundException("No se ha encontrado el usuario o la contraseña");
			}

			var usuarioAutenticado = usuario.get();

			// @formatter:off
			return UsuarioAutenticado.builder()
					.id(usuarioAutenticado.getId())
					.nombre(usuarioAutenticado.getNombre())
					.email(usuarioAutenticado.getEmail())
					.password(usuarioAutenticado.getPassword())
					.rol(usuarioAutenticado.getRol())
					.cliente(usuarioAutenticado.getCliente())
				.build();
			// @formatter:on
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// AUTORIZACIÓN: entonces te permito hacer esto
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		// @formatter:off
		http
	        .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
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