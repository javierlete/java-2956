package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.amazonia.servicios.CarritoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("carrito")
public class CarritoController {
	private final CarritoService carritoService;
	
	@GetMapping
	public String carrito() {
		return "carrito";
	}
	
	@GetMapping("producto")
	public String producto(Long id) {
		carritoService.agregarProducto(id);
		
		return "redirect:/carrito";
	}
	
}
