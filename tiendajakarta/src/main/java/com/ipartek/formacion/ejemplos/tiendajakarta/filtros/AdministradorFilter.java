package com.ipartek.formacion.ejemplos.tiendajakarta.filtros;

import java.io.IOException;

import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/cf/admin/*")
public class AdministradorFilter extends HttpFilter {

	private static final long serialVersionUID = -6270351077293674057L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

		if (usuario != null && usuario.getRol().getNombre().equals("ADMINISTRADOR")) {
			super.doFilter(request, response, chain);
		} else {
			response.sendRedirect(request.getContextPath() + "/cf/login");
		}
	}

}
