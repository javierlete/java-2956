package com.ipartek.formacion.ejemplos.ipartexpring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.AnonimoService;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.UsuarioService;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRest {
	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/autenticacion")
	public Usuario get(String email, String password) {
		return anonimoService.autenticar(Usuario.builder().email(email).password(password).build())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no autenticado"));
	}
	
	@GetMapping("/me-gusta")
	public void meGusta(Long mensajeId, Long usuarioId) {
		usuarioService.meGusta(usuarioId, mensajeId);
	}

	@GetMapping("/no-me-gusta")
	public void noMeGusta(Long mensajeId, Long usuarioId) {
		usuarioService.noMeGusta(usuarioId, mensajeId);
	}
}
