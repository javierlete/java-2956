package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.amazonia.modelos.Carrito;
import com.ipartek.formacion.ejemplos.amazonia.modelos.Carrito.Linea;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;
import com.ipartek.formacion.ejemplos.amazonia.servicios.CarritoService;
import com.ipartek.formacion.ejemplos.amazonia.servicios.ServicioException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class CarritoServiceImpl implements CarritoService {
	private final AnonimoService anonimoService;
	private final Carrito carrito;

	@Override
	public void agregarProducto(Long id) {
		agregarProducto(id, 1);
	}

	@Override
	public void agregarProducto(Long id, Integer cantidad) {
		var producto = anonimoService.obtenerProductoPorId(id)
				.orElseThrow(() -> new ServicioException("No se ha encontrado el producto"));

		var linea = carrito.getLineas().stream().filter(l -> l.getProducto().getId() == id).findFirst();

		if (linea.isPresent()) {
			var cantidadExistente = linea.get().getCantidad();
			var total = cantidadExistente + cantidad;

			if (total >= 1) {
				linea.get().setCantidad(total);
			} else {
				borrarProducto(linea.get().getProducto().getId());
			}
		} else {
			if (cantidad >= 1) {
				carrito.getLineas().add(Linea.builder().producto(producto).cantidad(cantidad).build());
			}
		}
	}

	@Override
	public void borrarProducto(Long id) {
		var linea = carrito.getLineas().stream().filter(l -> l.getProducto().getId() == id).findFirst()
				.orElseThrow(() -> new ServicioException("No se ha encontrado el producto"));

		carrito.getLineas().remove(linea);
	}

}
