package com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio;

import java.io.InputStream;

import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

public interface AdministradorNegocio extends PublicoNegocio {
	void guardarImagenProducto(Long id, String ruta, InputStream imagen);
	
	Producto anyadirProducto(Producto producto);
	Producto modificarProducto(Producto producto);
	void borrarProducto(Long id);
}
