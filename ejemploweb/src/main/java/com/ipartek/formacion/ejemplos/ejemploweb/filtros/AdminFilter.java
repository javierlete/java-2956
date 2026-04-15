package com.ipartek.formacion.ejemplos.ejemploweb.filtros;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter({ "/cf/admin/*", "/admin/*" })
public class AdminFilter extends HttpFilter {

	private static final long serialVersionUID = 6319106102630535591L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String email = (String) request.getSession().getAttribute("email");

		if (email == null) {
			response.sendRedirect(request.getContextPath() + "/cf/login");
			return;
		}

		super.doFilter(request, response, chain); // chain.doFilter(request, response);
	}
}
