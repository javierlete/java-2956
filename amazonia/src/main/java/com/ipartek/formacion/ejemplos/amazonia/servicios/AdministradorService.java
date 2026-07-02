package com.ipartek.formacion.ejemplos.amazonia.servicios;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface AdministradorService {
	Iterable<Producto> listarProductos();
	Optional<Producto> obtenerProductoPorId(Long id);
	Producto guardarProducto(@NotNull @Valid Producto producto);
	void borrarProducto(Long id);
	
	// TODO Intentar utilizar algo más genérico que no dependa de web
	void subirFoto(Long id, MultipartFile foto);
}
