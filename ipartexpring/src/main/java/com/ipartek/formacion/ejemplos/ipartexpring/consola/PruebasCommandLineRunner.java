package com.ipartek.formacion.ejemplos.ipartexpring.consola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexpring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexpring.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.AnonimoService;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.UsuarioService;

@Component
public class PruebasCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MensajeRepository mensajeRepository;

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

		mensajeRepository
				.save(Mensaje.builder().texto("Hola a todos").usuario(Usuario.builder().id(1L).build()).build());
		mensajeRepository.save(Mensaje.builder().texto("Hola Javier").respuestaA(Mensaje.builder().id(1L).build())
				.usuario(Usuario.builder().id(2L).build()).build());
		mensajeRepository.save(Mensaje.builder().texto("Biennnnn").usuario(Usuario.builder().id(3L).build()).build());

		for (var mensaje : mensajeRepository.findAll()) {
			System.out.println(mensaje);
		}

		mensajeRepository.insertarMeGusta(1L, 2L);
		mensajeRepository.insertarMeGusta(3L, 1L);

		for (var mensajeDto : mensajeRepository.obtenerTodosParaListado(3L)) {
			System.out.println(mensajeDto);
		}

		for (var mensajeDto : mensajeRepository.obtenerRespuestas(1L, 1L)) {
			System.out.println(mensajeDto);
		}

		servicios();
	}

	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioService usuarioService;

	private void servicios() {
		System.out.println("SERVICIOS");

		for (var mensaje : anonimoService.listarMensajesRaizListado()) {
			System.out.println(mensaje);
		}

		for (var mensaje : anonimoService.listarRespuestas(1L)) {
			System.out.println(mensaje);
		}

		var usuarioErroneo = anonimoService
				.autenticar(Usuario.builder().email("asdf@asdf").password("alsdfajs").build());

		System.out.println(usuarioErroneo);

		var usuario = anonimoService.autenticar(Usuario.builder().email("juan@email.net").password("juan").build());

		System.out.println(usuario);

		usuarioService.enviarMensaje(Mensaje.builder().texto("Mensajeando").usuario(usuario.get()).build());
		
		usuarioService.enviarMensaje(Mensaje.builder().respuestaA(Mensaje.builder().id(1L).build()).texto("Ok")
				.usuario(usuario.get()).build());

		for (var mensaje : anonimoService.listarMensajesListado(usuario.get().getId())) {
			System.out.println(mensaje);
		}

		for (var mensaje : anonimoService.listarRespuestas(1L, usuario.get().getId())) {
			System.out.println(mensaje);
		}
	}

}
