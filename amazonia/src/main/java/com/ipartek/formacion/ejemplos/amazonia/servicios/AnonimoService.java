package com.ipartek.formacion.ejemplos.amazonia.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.amazonia.dtos.CredencialesDto;
import com.ipartek.formacion.ejemplos.amazonia.dtos.ProductoDto;
import com.ipartek.formacion.ejemplos.amazonia.dtos.UsuarioDto;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

public interface AnonimoService {
	Iterable<Producto> listarProductos();
	Optional<Producto> obtenerProductoPorId(Long id);
	Iterable<Producto> buscarProductos(String texto);
	
	Iterable<Categoria> listarCategorias();
	Iterable<ProductoDto> obtenerProductosPorCategoriaId(Long id);
	Optional<UsuarioDto> autenticar(CredencialesDto credenciales);
}
