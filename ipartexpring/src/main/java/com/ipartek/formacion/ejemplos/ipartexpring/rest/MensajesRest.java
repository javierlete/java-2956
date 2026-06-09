package com.ipartek.formacion.ejemplos.ipartexpring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.AnonimoService;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.UsuarioService;

@RestController
@RequestMapping("/api/v2/mensajes")
public class MensajesRest {
	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public Iterable<Mensaje> get() {
		return anonimoService.listarMensajes();
	}

	@GetMapping("breves")
	public Iterable<MensajeListadoDto> getBreves() {
		return anonimoService.listarMensajesRaizListado();
	}

	@GetMapping("breves/{id}")
	public Iterable<MensajeListadoDto> getBreves(@PathVariable Long id) {
		return anonimoService.listarMensajesListado(id);
	}

	@GetMapping("breves/respuestas/{id}")
	public Iterable<MensajeListadoDto> getRespuestas(@PathVariable Long id, Long idUsuario) {
		if (idUsuario == null) {
			return anonimoService.listarRespuestas(id);
		}

		return anonimoService.listarRespuestas(id, idUsuario);
	}

	@PostMapping
	public Mensaje post(Mensaje mensaje) {
		System.out.println(mensaje);

		return usuarioService.enviarMensaje(mensaje);
	}
}
