package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AdministradorService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
@Transactional
class AdministradorServiceImplTest {

	@Autowired
	private AdministradorService administradorService;

	@Test
	void testGuardarProducto() {
		assertNotNull(administradorService);

		assertThrows(ConstraintViolationException.class, () -> administradorService.guardarProducto(null));

		assertThrows(ConstraintViolationException.class,
				() -> administradorService.guardarProducto(Producto.builder().build()));
		
		assertThrows(ConstraintViolationException.class,
				() -> administradorService.guardarProducto(Producto.builder().nombre("").build()));
		
		assertThrows(ConstraintViolationException.class,
				() -> administradorService.guardarProducto(Producto.builder().nombre("     ").build()));
		
		assertThrows(ConstraintViolationException.class,
				() -> administradorService.guardarProducto(Producto.builder().nombre("Nombre").build()));
		
		assertThrows(ConstraintViolationException.class,
				() -> administradorService.guardarProducto(Producto.builder().precio(new BigDecimal("1234")).build()));
		
		assertThrows(ConstraintViolationException.class,
				() -> administradorService.guardarProducto(Producto.builder().nombre("PRODUCTO").precio(new BigDecimal("1234")).build()));
		
		var producto =administradorService.guardarProducto(Producto.builder().nombre("PRODUCTO").precio(new BigDecimal("1234")).categoria(Categoria.builder().id(1L).build()).build());
		
		assertNotNull(producto);

		assertEquals("PRODUCTO", producto.getNombre());
		assertEquals(new BigDecimal("1234"), producto.getPrecio());

		assertTrue(producto.getId() > 0L);
	}

}
