package com.ipartek.formacion.ejemplos.amazonia.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface AdministradorService {
	Iterable<Producto> listarProductos();
	Optional<Producto> obtenerProductoPorId(Long id);
	Producto guardarProducto(@NotNull @Valid Producto producto);
	void borrarProducto(Long id);
}
