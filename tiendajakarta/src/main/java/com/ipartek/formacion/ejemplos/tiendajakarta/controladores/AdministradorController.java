package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;

public class AdministradorController {
	@Ruta("/admin/productos")
	public static String listadoProductos(Datos datos) {
		return "admin/productos";
	}
}
