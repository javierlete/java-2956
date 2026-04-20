package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;
import bibliotecas.fabrica.Fabrica;

public class PublicoController {
	private static final DaoProducto DAO = (DaoProducto) Fabrica.getObjeto("dao.producto");
	
	@Ruta("/")
	public static String listadoProductos(Datos datos) {
		datos.salida().put("productos", DAO.obtenerTodos());
		
		return "listado-productos";
	}
	
	@Ruta("/producto")
	public static String detalleProducto(Datos datos) {
		return "producto";
	}
}
