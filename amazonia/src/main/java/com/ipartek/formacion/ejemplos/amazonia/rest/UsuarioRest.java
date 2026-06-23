package com.ipartek.formacion.ejemplos.amazonia.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.amazonia.dtos.CredencialesDto;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Usuario;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRest {
	private final AnonimoService anonimoService;
	
	@PostMapping("autenticar")
	public Usuario autenticar(String email, String password) {
		var usuario = anonimoService.autenticar(new CredencialesDto(email, password));
		
		if(usuario.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		return usuario.get();
	}
}
