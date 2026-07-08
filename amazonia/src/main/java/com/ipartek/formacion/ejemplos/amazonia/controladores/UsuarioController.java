package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Cliente;
import com.ipartek.formacion.ejemplos.amazonia.servicios.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
	public String formularioPost(@Valid Cliente cliente, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "cliente";
		}
		
		usuarioService.registrarDatosCliente(cliente);
		
		return "redirect:/";
	}
}
