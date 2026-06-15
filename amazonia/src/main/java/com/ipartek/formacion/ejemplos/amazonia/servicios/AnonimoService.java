package com.ipartek.formacion.ejemplos.amazonia.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

public interface AnonimoService {
	Iterable<Producto> listarProductos();
	Optional<Producto> obtenerProductoPorId(Long id);
}
