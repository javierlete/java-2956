package com.ipartek.formacion.ejemplos.amazonia.servicios;

public interface CarritoService {
	void agregarProducto(Long id);
	void agregarProducto(Long id, Integer cantidad);
	void borrarProducto(Long id);
}
