package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import java.math.BigDecimal;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;
import bibliotecas.fabrica.Fabrica;

public class AdministradorController {
	private final static DaoProducto DAO = (DaoProducto) Fabrica.getObjeto("dao.producto");
	
	@Ruta("/admin/productos")
	public static String listadoProductos(Datos datos) {
		datos.salida().put("productos", DAO.obtenerTodos());

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

			datos.salida().put("producto", DAO.obtenerPorId(id));
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

		System.out.println(producto);

		if(producto.getId() == null) {
			DAO.insertar(producto);
		} else {
			DAO.modificar(producto);
		}

		return "redirect:/admin/productos";
	}

}
