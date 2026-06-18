package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.ProductoRepository;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AdministradorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor

@Log
@Service
public class AdministradorServiceImpl implements AdministradorService {
	private final ProductoRepository productoRepository;

	@Override
	public Iterable<Producto> listarProductos() {
		log.info("LISTADO PRODUCTOS");
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> obtenerProductoPorId(Long id) {
		log.info("PRODUCTO POR ID: " + id);
		return productoRepository.findById(id);
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		log.info("GUARDAR " + producto);
		return productoRepository.save(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		log.info("BORRAR " + id);
		productoRepository.deleteById(id);
	}

	
	
}
