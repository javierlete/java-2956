package com.ipartek.formacion.ejemplos.tiendajakarta.restservlet;

import java.io.IOException;

import com.google.gson.Gson;
import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

import bibliotecas.fabrica.Fabrica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/productos/*")
public class ProductosRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DaoProducto DAO_PRODUCTO = (DaoProducto) Fabrica.getObjeto("dao.producto");
	private static final Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = obtenerId(request);

		String respuesta;

		if (id != null) {
			Producto producto = DAO_PRODUCTO.obtenerPorId(id);

			if (producto == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			respuesta = gson.toJson(producto);
		} else {
			respuesta = gson.toJson(DAO_PRODUCTO.obtenerTodos());
		}

		response.setContentType("application/json");
		response.getWriter().append(respuesta);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		Producto producto = gson.fromJson(request.getReader(), Producto.class);

		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().append(gson.toJson(DAO_PRODUCTO.insertar(producto)));
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Producto producto = gson.fromJson(request.getReader(), Producto.class);

		if(obtenerId(request) != producto.getId()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		response.setContentType("application/json");
		response.getWriter().append(gson.toJson(DAO_PRODUCTO.modificar(producto)));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO_PRODUCTO.borrar(obtenerId(request));
		
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	private static Long obtenerId(HttpServletRequest request) {
		String ruta = request.getPathInfo();

		if (ruta == null) {
			return null;
		}

		return Long.parseLong(ruta.substring(1));
	}

}
