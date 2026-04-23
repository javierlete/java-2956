package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import java.math.BigDecimal;
import java.util.List;

import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;

public class AdministradorController {
	@Ruta("/admin/productos")
	public static String listadoProductos(Datos datos) {
		datos.salida().put("productos",
				List.of(new Producto(1L, "Portátil", "El más chulo", new BigDecimal("1234.56")),
						new Producto(2L, "Monitor", "El más guay", new BigDecimal("123.56")),
						new Producto(3L, "Ratón", "El más mini", new BigDecimal("12.56"))));

		return "admin/productos";
	}

	@Ruta("/admin/producto")
	public static String formularioProducto(Datos datos) {
		System.out.println(datos.metodo());

		if ("POST".equals(datos.metodo())) {
			return formularioProductoPost(datos);
		} else {
			return formularioProductoGet(datos);
		}
	}

	private static String formularioProductoGet(Datos datos) {

		String sId = datos.entrada().get("id") != null ? datos.entrada().get("id")[0] : null;

		if (sId != null) {
			Long id = Long.parseLong(sId);

			datos.salida().put("producto", new Producto(id, "Nombre " + id, "Descripción " + id, new BigDecimal(id)));
		}

		return "admin/producto";
	}

	private static String formularioProductoPost(Datos datos) {
		String sId = datos.entrada().get("id")[0];
		String nombre = datos.entrada().get("nombre")[0];
		String sPrecio = datos.entrada().get("precio")[0];
		String descripcion = datos.entrada().get("descripcion")[0];

		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		BigDecimal precio = new BigDecimal(sPrecio);

		Producto producto = new Producto(id, nombre, descripcion, precio);

		if(producto.getId() == null) {
			System.out.println("INSERTAR");
		} else {
			System.out.println("MODIFICAR");
		}
		
		System.out.println(producto);

		return "redirect:/admin/productos";
	}

}
