package com.ipartek.formacion.ejemplos.amazonia.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.amazonia.dtos.ProductoDto;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/categorias")
public class CategoriaRest {
	private final AnonimoService anonimoService;

	@GetMapping
	public Iterable<Categoria> get() {
		return anonimoService.listarCategorias();
	}
	
	@GetMapping("{id}/productos")
	public Iterable<ProductoDto> getProductosPorCategoriaId(@PathVariable Long id) {
		return anonimoService.obtenerProductosPorCategoriaId(id);
	}
}
