package com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio.impl;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio.PublicoNegocio;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import bibliotecas.fabrica.Fabrica;
import lombok.extern.java.Log;

@Log
public class PublicoNegocioImpl implements PublicoNegocio {

	private final DaoUsuario daoUsuario = (DaoUsuario)Fabrica.getObjeto("dao.usuario");
	protected final DaoProducto daoProducto= (DaoProducto)Fabrica.getObjeto("dao.producto");
	
	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = daoUsuario.obtenerPorEmailConRol(email);
		
		if(usuario.isEmpty()) {
			log.warning("Usuario no encontrado");
			return usuario;
		}
		
		if(usuario.get().getPassword().equals(password)) {
			return usuario;
		} else {
			log.warning("Contraseña incorrecta");
			return Optional.empty();
		}
	}

	@Override
	public Optional<Usuario> autenticar(Usuario usuario) {
		return autenticar(usuario.getEmail(), usuario.getPassword());
	}

	@Override
	public Iterable<Producto> listadoProductos() {
		return daoProducto.obtenerTodos();
	}

	@Override
	public Producto detalleProducto(Long id) {
		return daoProducto.obtenerPorId(id);
	}

}
