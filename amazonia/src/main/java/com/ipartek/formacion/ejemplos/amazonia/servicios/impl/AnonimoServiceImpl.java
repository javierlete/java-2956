package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.amazonia.dtos.CredencialesDto;
import com.ipartek.formacion.ejemplos.amazonia.dtos.ProductoDto;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Usuario;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.CategoriaRepository;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.ProductoRepository;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor

@Slf4j
@Service
public class AnonimoServiceImpl implements AnonimoService {

	private final ProductoRepository productoRepository;
	private final CategoriaRepository categoriaRepository;
	private final UsuarioRepository usuarioRepository;

	private final PasswordEncoder passwordEncoder;

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

	@Override
	public Iterable<ProductoDto> obtenerProductosPorCategoriaId(Long id) {
		log.info("Productos por id de categoría: " + id);
		return productoRepository.findByCategoriaId(id);
	}

	@Override
	public Optional<Usuario> autenticar(CredencialesDto credenciales) {
		var usuario = usuarioRepository.findByEmail(credenciales.email());

		if (usuario.isEmpty() || !passwordEncoder.matches(credenciales.password(), usuario.get().getPassword())) {
			return Optional.empty();
		}

		return usuario;
	}

}
