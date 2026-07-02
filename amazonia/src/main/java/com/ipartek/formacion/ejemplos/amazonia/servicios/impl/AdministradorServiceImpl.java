package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.ProductoRepository;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AdministradorService;
import com.ipartek.formacion.ejemplos.amazonia.servicios.ServicioException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@RequiredArgsConstructor

@Log
@Service
@Validated
public class AdministradorServiceImpl implements AdministradorService {
	private final ProductoRepository productoRepository;

	@Value("${app.ruta.fotos}")
	private String rutaFotos;

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
	public Producto guardarProducto(@NotNull @Valid Producto producto) {
		log.info("GUARDAR " + producto);
		return productoRepository.save(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		log.info("BORRAR " + id);
		productoRepository.deleteById(id);
	}

	@Override
	public void subirFoto(Long id, MultipartFile foto) {
		try {
			if (foto.isEmpty()) {
				throw new ServicioException("No se va a guardar un fichero vacío.");
			}

//			Path fichero = this.raizFotos.resolve(Paths.get(foto.getOriginalFilename())).normalize()
//					.toAbsolutePath();

			Path raizFotos = Path.of(rutaFotos);

			Path fichero = raizFotos.resolve(Paths.get(id + ".jpg")).normalize().toAbsolutePath();

			if (!fichero.getParent().equals(raizFotos.toAbsolutePath())) {
				// This is a security check
				throw new ServicioException("Cannot store file outside current directory.");
			}

			try (InputStream inputStream = foto.getInputStream()) {
				Files.copy(inputStream, fichero, StandardCopyOption.REPLACE_EXISTING);

				Thumbnails.of(fichero.toString()).crop(Positions.CENTER).size(400, 300).outputQuality(0.95).toFile(
						raizFotos.resolve(Paths.get(id + "_400x300.jpg")).normalize().toAbsolutePath().toString());
			}
		} catch (IOException e) {
			throw new ServicioException("Failed to store file.", e);
		}
	}

}
