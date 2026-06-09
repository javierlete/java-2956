package com.ipartek.formacion.ejemplos.ipartexpring.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexpring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexpring.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.AnonimoService;

import lombok.extern.java.Log;

@Service
@Log
public class AnonimoServiceImpl implements AnonimoService {
	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Iterable<Mensaje> listarMensajes() {
		log.info("Listado de mensajes");

		return mensajeRepository.findAll();
	}

	@Override
	public Iterable<MensajeListadoDto> listarMensajesListado() {
		log.info("Listado de mensajes para listado");

		return mensajeRepository.obtenerTodosParaListado();
	}

	@Override
	public Iterable<MensajeListadoDto> listarMensajesListado(Long id) {
		log.info("Listado de mensajes para listado con id " + id);

		return mensajeRepository.obtenerTodosParaListado(id);
	}

	@Override
	public Iterable<MensajeListadoDto> listarMensajesRaizListado() {
		log.info("Listado de mensajes raíz para listado ");

		return mensajeRepository.obtenerRaicesParaListado();
	}

	@Override
	public Iterable<MensajeListadoDto> listarRespuestas(Long idMensaje) {
		log.info("Listado de respuestas de " + idMensaje);

		return mensajeRepository.obtenerRespuestas(idMensaje);
	}

	@Override
	public Iterable<MensajeListadoDto> listarRespuestas(Long idMensaje, Long idUsuario) {
		log.info("Listado de respuestas de " + idMensaje + " para el usuario " + idUsuario);

		return mensajeRepository.obtenerRespuestas(idMensaje, idUsuario);
	}

	@Override
	public Optional<Usuario> autenticar(Usuario usuario) {
		log.info("Intento de autenticación de " + usuario);

		var usuarioLogin = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioLogin.isEmpty() || !usuarioLogin.get().getPassword().equals(usuario.getPassword())) {
			log.warning("Usuario incorrecto");

			return Optional.empty();
		}

		return usuarioLogin;
	}

}
