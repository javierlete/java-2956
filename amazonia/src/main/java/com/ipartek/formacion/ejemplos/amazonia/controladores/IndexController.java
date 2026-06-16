package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private AnonimoService anonimoService;
	
	@GetMapping({"/", "productos"})
	public String productos(Model modelo) {
		modelo.addAttribute("productos", anonimoService.listarProductos());
		
		return "productos";
	}

	@GetMapping({"productos/{id}"})
	public String producto(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("producto", anonimoService.obtenerProductoPorId(id).get());
		
		return "producto";
	}

	@GetMapping({"buscar"})
	public String producto(String texto, Model modelo) {
		modelo.addAttribute("productos", anonimoService.buscarProductos(texto));
		
		return "productos";
	}
}
