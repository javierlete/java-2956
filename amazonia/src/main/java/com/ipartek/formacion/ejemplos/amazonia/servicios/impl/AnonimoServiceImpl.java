package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.CategoriaRepository;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.ProductoRepository;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnonimoServiceImpl implements AnonimoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Iterable<Producto> listarProductos() {
		log.info("Listado de productos");
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> obtenerProductoPorId(Long id) {
		log.info("Producto: " + id);
		return productoRepository.findById(id);
	}

	@Override
	public Iterable<Producto> buscarProductos(String texto) {
		log.info("Buscar producto por texto: " + texto);
		return productoRepository.findByNombreContains(texto);
	}

	@Override
	public Iterable<Categoria> listarCategorias() {
		log.info("Listar categorías");
		return categoriaRepository.findAll();
	}

}
