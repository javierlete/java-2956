package com.ipartek.formacion.ejemplos.amazonia.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
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
}
