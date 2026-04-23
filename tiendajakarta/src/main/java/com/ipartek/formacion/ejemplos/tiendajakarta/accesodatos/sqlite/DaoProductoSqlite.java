package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

import bibliotecas.accesodatos.DaoException;
import bibliotecas.accesodatos.DaoGenericoJdbc;

public class DaoProductoSqlite extends DaoGenericoJdbc<Producto> implements DaoProducto {

	public DaoProductoSqlite() {
		super("productos", new String[] { "nombre", "descripcion", "precio" }, DaoProductoSqlite::filaAObjeto,
				DaoProductoSqlite::objetoAFila);
	}

	private static Producto filaAObjeto(ResultSet rs) {
		try {
			return new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"),
					rs.getBigDecimal("precio"));
		} catch (SQLException e) {
			throw new DaoException("No he podido convertir la fila a objeto", e);
		}
	}

	private static Object[] objetoAFila(Producto p) {
		if (p.getId() == null) {
			return new Object[] { p.getNombre(), p.getDescripcion(), p.getPrecio() };
		} else {
			return new Object[] { p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getId() };
		}
	}
}
