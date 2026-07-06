package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.amazonia.Carrito;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("carrito")
public class CarritoController {
	private final Carrito carrito;
	private final AnonimoService anonimoService;
	
	@GetMapping("producto")
	public String producto(Long id) {
		carrito.getProductos().add(anonimoService.obtenerProductoPorId(id).get());
		
		return "carrito";
	}
	
}
