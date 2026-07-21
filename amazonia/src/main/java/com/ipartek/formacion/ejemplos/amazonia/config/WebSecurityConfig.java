package com.ipartek.formacion.ejemplos.amazonia.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
			.cors(Customizer.withDefaults())
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
	
	 // Define un bean con las reglas específicas de CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Orígenes permitidos (puedes usar "*" para permitir todos, pero no si usas credenciales)
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Ejemplo para Angular
        
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Headers permitidos en las peticiones
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        
        // Permitir envío de credenciales (Cookies, Authorization header)
        configuration.setAllowCredentials(true);
        
        // Cuánto tiempo se cachea la respuesta preflight (en segundos)
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuración a todos los endpoints
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}