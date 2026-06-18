package com.ipartek.formacion.ejemplos.amazonia.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AdministradorService;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoRest {
	private final AnonimoService anonimoService;
	private final AdministradorService administradorService;

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
	
	@ApiResponse(responseCode = "201", description = "Producto creado")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Producto insertar(@RequestBody Producto producto) {
		return administradorService.guardarProducto(producto);
	}
	
	@PutMapping("{id}")
	public Producto modificar(@PathVariable Long id, @RequestBody Producto producto) {
		return administradorService.guardarProducto(producto);
	}
	
	@ApiResponse(responseCode = "204", description = "Producto borrado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void borrar(@PathVariable Long id) {
		administradorService.borrarProducto(id);
	}
}
