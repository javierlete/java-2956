package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Rol;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import bibliotecas.accesodatos.DaoException;
import bibliotecas.accesodatos.DaoGenericoJdbc;

public class DaoUsuarioSqlite extends DaoGenericoJdbc<Usuario> implements DaoUsuario {

	public DaoUsuarioSqlite() {
		super("usuarios", new String[] { "nombre", "email", "password", "roles_id" }, DaoUsuarioSqlite::filaAObjeto,
				DaoUsuarioSqlite::objetoAFila);
	}

	private static Usuario filaAObjeto(ResultSet rs) {
		try {
			return new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"),
					rs.getString("password"), new Rol(rs.getLong("roles_id"), null, null));
		} catch (SQLException e) {
			throw new DaoException("No he podido convertir la fila a objeto", e);
		}
	}

	private static Object[] objetoAFila(Usuario p) {
		if (p.getId() == null) {
			return new Object[] { p.getNombre(), p.getEmail(), p.getPassword(), p.getRol().getId() };
		} else {
			return new Object[] { p.getId(), p.getNombre(), p.getEmail(), p.getPassword(), p.getRol().getId() };
		}
	}

	@Override
	public Optional<Usuario> obtenerPorEmailConRol(String email) {
		return Optional.ofNullable(dao.ejecutarSqlUno("""
				SELECT
					u.id u_id, u.nombre u_nombre, u.email u_email, u.password u_password,
					r.id r_id, r.nombre r_nombre, r.descripcion r_descripcion
				FROM usuarios u
				JOIN roles r ON u.roles_id = r.id
				WHERE u.email=?
				""", DaoUsuarioSqlite::filaAObjetoConRol, null, email));
	}

	private static Usuario filaAObjetoConRol(ResultSet rs) {
		try {
			return new Usuario(rs.getLong("u_id"), rs.getString("u_nombre"), rs.getString("u_email"),
					rs.getString("u_password"),
					new Rol(rs.getLong("r_id"), rs.getString("r_nombre"), rs.getString("r_descripcion")));
		} catch (SQLException e) {
			throw new DaoException("No he podido convertir la fila a objeto", e);
		}
	}
}
