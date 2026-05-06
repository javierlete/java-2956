package com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

public interface PublicoNegocio {
	Optional<Usuario> autenticar(String email, String password);
	Optional<Usuario> autenticar(Usuario usuario);

	Iterable<Producto> listadoProductos();
	Producto detalleProducto(Long id);
}
