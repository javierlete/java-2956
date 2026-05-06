package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import java.math.BigDecimal;

import com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio.AdministradorNegocio;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;
import bibliotecas.fabrica.Fabrica;
import lombok.extern.java.Log;

@Log
public class AdministradorController {

	private static final AdministradorNegocio negocio = (AdministradorNegocio) Fabrica.getObjeto("negocio.admin");
	
	@Ruta("/admin/productos")
	public static String listadoProductos(Datos datos) {
		datos.salida().put("productos", negocio.listadoProductos());

		return "admin/productos";
	}

	@Ruta("/admin/producto")
	public static String formularioProducto(Datos datos) {
		log.info(datos.metodo());

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

			datos.salida().put("producto", negocio.detalleProducto(id));
		}

		return "admin/producto";
	}

	private static String formularioProductoPost(Datos datos) {
		String sId = datos.entrada().get("id")[0];
		String nombre = datos.entrada().get("nombre")[0];
		String sPrecio = datos.entrada().get("precio")[0];
		String descripcion = datos.entrada().get("descripcion")[0];
		String imagen = datos.entrada().get("imagen") != null ? datos.entrada().get("imagen")[0] : null;

		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		BigDecimal precio = new BigDecimal(sPrecio);

		Producto producto = new Producto(id, nombre, descripcion, precio);

		if (producto.getId() == null) {
			producto = negocio.anyadirProducto(producto);
		} else {
			producto = negocio.modificarProducto(producto);
		}

		if (imagen != null) {
			negocio.guardarImagenProducto(producto.getId(), datos.rutaRaiz(), datos.ficheros().get("imagen"));
		}
		
		return "redirect:/admin/productos";
	}

	@Ruta("/admin/producto-borrar")
	public static String borrar(Datos datos) {
		String sId = datos.entrada().get("id")[0];

		Long id = Long.parseLong(sId);

		negocio.borrarProducto(id);

		return "redirect:/admin/productos";
	}

}
