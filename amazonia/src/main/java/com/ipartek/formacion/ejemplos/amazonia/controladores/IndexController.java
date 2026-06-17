package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class IndexController {
	private final AnonimoService anonimoService;
	
	@GetMapping({"/", "productos"})
	public String productos(Model modelo) {
		modelo.addAttribute("productos", anonimoService.listarProductos());
		modelo.addAttribute("mostrarCategoria", true);
		
		return "productos";
	}

	@GetMapping("productos/{id}")
	public String producto(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("producto", anonimoService.obtenerProductoPorId(id).get());
		modelo.addAttribute("mostrarCategoria", true);
		
		return "producto";
	}

	@GetMapping("buscar")
	public String producto(String texto, Model modelo) {
		modelo.addAttribute("productos", anonimoService.buscarProductos(texto));
		modelo.addAttribute("mostrarCategoria", true);
		
		return "productos";
	}
	
	@GetMapping("categoria/{id}")
	public String categoria(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("productos", anonimoService.obtenerProductosPorCategoriaId(id));
		modelo.addAttribute("mostrarCategoria", false);
		
		return "productos";
	}
}
