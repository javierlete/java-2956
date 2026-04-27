package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
		String imagen = datos.entrada().get("imagen") != null ? datos.entrada().get("imagen")[0] : null;

		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		BigDecimal precio = new BigDecimal(sPrecio);

		Producto producto = new Producto(id, nombre, descripcion, precio);

		System.out.println(producto);

		if (producto.getId() == null) {
			producto = DAO.insertar(producto);
		} else {
			producto = DAO.modificar(producto);
		}

		System.out.println(producto);

		if (imagen != null) {
			System.out.println("IMAGEN ======> " + imagen);

			String ruta = datos.rutaRaiz() + "imgs" + File.separator + producto.getId() + ".jpg";

			System.out.println(ruta);
			
			try {
				datos.ficheros().get("imagen").transferTo(new FileOutputStream(ruta));
			} catch (IOException e) {
				System.out.println("ERROR AL GUARDAR LA IMAGEN " + ruta);
				e.printStackTrace();
			}
		} else {
			System.out.println("SIN IMAGEN");
		}

		return "redirect:/admin/productos";
	}

	@Ruta("/admin/producto-borrar")
	public static String borrar(Datos datos) {
		String sId = datos.entrada().get("id")[0];

		Long id = Long.parseLong(sId);

		DAO.borrar(id);

		return "redirect:/admin/productos";
	}

}
