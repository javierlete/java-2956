package com.ipartek.formacion.ejemplos.amazonia.controladores;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.ProductoRepository;

@SpringBootTest
@Transactional
class AdminControllerTest {

	private static final long ACCESORIOS_ID = 2L;

	private static final long MONITOR_ID = 2L;
	
	private static final Categoria INFORMATICA = Categoria.builder().id(1L).nombre("Informática").build();
	private static final Categoria ACCESORIOS = Categoria.builder().id(ACCESORIOS_ID).nombre("Accesorios").build();

	private static final Producto PORTATIL = Producto.builder().id(1L).nombre("Portátil").precio(new BigDecimal("1234"))
			.categoria(INFORMATICA).build();
	private static final Producto MONITOR = Producto.builder().id(MONITOR_ID).nombre("Monitor").precio(new BigDecimal("123"))
			.categoria(ACCESORIOS).build();
	private static final Producto RATON = Producto.builder().id(3L).nombre("Ratón").precio(new BigDecimal("12"))
			.categoria(ACCESORIOS).build();

	private static final Producto TECLADO = Producto.builder().nombre("Teclado").precio(new BigDecimal("21"))
			.categoria(Categoria.builder().id(ACCESORIOS_ID).build()).build();
	
	private static final List<Producto> PRODUCTOS = List.of(PORTATIL, MONITOR, RATON);

	@Autowired
	private AdminController adminController;

	@Autowired
	private ProductoRepository productoRepository;

	@Test
	void testListado() {
		var modelo = new ExtendedModelMap();

		var vista = adminController.listado(modelo);

		assertEquals(1, modelo.size());

		var productos = (Iterable<?>) modelo.get("productos");

		assertNotNull(productos);

		assertIterableEquals(PRODUCTOS, productos);

		assertEquals("admin/productos/listado", vista);
	}

	@Test
	void testFormularioProducto() {
		var vista = adminController.formulario(Producto.builder().build());
		
		assertEquals("admin/productos/formulario", vista);
	}

	@Test
	void testFormularioLongModel() {
		var modelo = new ExtendedModelMap();
		
		var vista = adminController.formulario(MONITOR_ID, modelo);

		assertEquals(MONITOR, modelo.getAttribute("producto"));
		
		assertEquals("admin/productos/formulario", vista);
	}

	@Test
	void testBorrar() {
		var productoAntes = productoRepository.findById(MONITOR_ID);
		
		assertTrue(productoAntes.isPresent());
		
		var vista = adminController.borrar(MONITOR_ID);

		var productoDespues = productoRepository.findById(MONITOR_ID);
		
		assertFalse(productoDespues.isPresent());
		
		assertEquals("redirect:/admin/productos/listado", vista);
	}

	@Test
	void testGuardar() {
		var numeroProductosAntes = productoRepository.count();
		
		var vista = adminController.guardar(TECLADO);
		
		var numeroProductosDespues = productoRepository.count();
		
		assertEquals(numeroProductosAntes + 1, numeroProductosDespues);
		
		var productoInsertado = productoRepository.findByNombreContains("Teclado").iterator().next();
		
		assertNotNull(productoInsertado.getId());
		
		productoInsertado.setId(null);
		
		assertEquals(TECLADO, productoInsertado);
		
		assertEquals("redirect:/admin/productos/listado", vista);
	}

}
