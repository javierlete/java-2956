package com.ipartek.formacion.ejemplos.amazonia.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoRest {
	@Autowired
	private AnonimoService anonimoService;

	@GetMapping
	public Iterable<Producto> get() {
		return anonimoService.listarProductos();
	}

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Producto encontrado"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@GetMapping("{id}")
	public Producto get(@PathVariable Long id) {
		var producto = anonimoService.obtenerProductoPorId(id);

		if (producto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return producto.get();
	}
	
	@GetMapping("buscar/porTexto")
	public Iterable<Producto> buscar(String texto) {
		return anonimoService.buscarProductos(texto);
	}
}
