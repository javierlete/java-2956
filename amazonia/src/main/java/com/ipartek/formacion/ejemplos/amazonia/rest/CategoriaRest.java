package com.ipartek.formacion.ejemplos.amazonia.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

@RestController
@RequestMapping("/api/v2/categorias")
public class CategoriaRest {
	@Autowired
	private AnonimoService anonimoService;

	@GetMapping
	public Iterable<Categoria> get() {
		return anonimoService.listarCategorias();
	}
	
	@GetMapping("{id}/productos")
	public Iterable<Producto> getProductosPorCategoriaId(@PathVariable Long id) {
		return anonimoService.obtenerProductosPorCategoriaId(id);
	}
}
