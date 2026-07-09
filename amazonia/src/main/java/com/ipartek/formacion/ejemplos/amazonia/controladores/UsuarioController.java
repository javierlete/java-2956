package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.amazonia.config.UsuarioAutenticado;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Cliente;
import com.ipartek.formacion.ejemplos.amazonia.servicios.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RequiredArgsConstructor

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	@GetMapping("registrar-cliente")
	public String formulario(Cliente cliente) {
		return "cliente";
	}
	
	@PostMapping("registrar-cliente")
	public String formularioPost(@Valid Cliente cliente, BindingResult bindingResult, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		log.info(cliente.toString());
		log.info(usuarioAutenticado.toString());
		
		if(bindingResult.hasErrors()) {
			return "cliente";
		}

		usuarioAutenticado.setCliente(usuarioService.registrarDatosCliente(usuarioAutenticado.getId(), cliente));
		
		return "redirect:/";
	}
}
