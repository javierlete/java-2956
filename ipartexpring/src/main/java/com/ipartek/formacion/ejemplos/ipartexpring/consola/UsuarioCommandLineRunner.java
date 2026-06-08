package com.ipartek.formacion.ejemplos.ipartexpring.consola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexpring.repositorios.UsuarioRepository;

@Component
public class UsuarioCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		usuarioRepository.save(Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build());

		usuarioRepository
				.saveAll(List.of(Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build(),
						Usuario.builder().nombre("Juan").email("juan@email.net").password("juan").build()));

		for (var usuario : usuarioRepository.findAll()) {
			System.out.println(usuario);
		}

		System.out.println(usuarioRepository.findByEmail("pepe@email.net"));
	}

}
