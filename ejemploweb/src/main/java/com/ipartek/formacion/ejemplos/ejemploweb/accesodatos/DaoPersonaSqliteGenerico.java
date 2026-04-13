package com.ipartek.formacion.ejemplos.ejemploweb.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;
import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Rol;

import bibliotecas.accesodatos.DaoException;
import bibliotecas.accesodatos.DaoGenericoJdbc;

public class DaoPersonaSqliteGenerico extends DaoGenericoJdbc<Persona> implements DaoPersona {
	private static final String SQL_PERSONAS_ROLES = """
			SELECT p.id p_id, p.nombre p_nombre, p.fecha_nacimiento p_fecha_nacimiento, r.id r_id, r.nombre r_nombre, r.descripcion r_descripcion
			FROM personas p
			LEFT JOIN roles r ON p.id_rol = r.id
			""";

	public DaoPersonaSqliteGenerico() {
		super("personas", new String[] { "nombre", "fecha_nacimiento", "id_rol" },
				DaoPersonaSqliteGenerico::filaAPersona, DaoPersonaSqliteGenerico::personaAFila);
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombreParcial) {
		return dao.ejecutarSql("SELECT * FROM personas WHERE nombre LIKE ?", DaoPersonaSqliteGenerico::filaAPersona,
				null, nombreParcial);
	}

	@Override
	public Iterable<Persona> obtenerTodosConRol() {
		return dao.ejecutarSql(SQL_PERSONAS_ROLES, DaoPersonaSqliteGenerico::filaAPersonaConRol, null);
	}

	@Override
	public Persona obtenerPorIdConRol(Long id) {
		return dao.ejecutarSqlUno(SQL_PERSONAS_ROLES + " WHERE p.id=?", DaoPersonaSqliteGenerico::filaAPersonaConRol, null, id);
	}

	private static Persona filaAPersona(ResultSet rs) {
		try {
			Long id = rs.getLong("id");
			String nombre = rs.getString("nombre");
			java.sql.Date fechaNacimientoDate = rs.getDate("fecha_nacimiento");
			LocalDate fechaNacimiento = fechaNacimientoDate != null ? fechaNacimientoDate.toLocalDate() : null;

			Persona persona = new Persona(id, nombre, fechaNacimiento);
			return persona;
		} catch (SQLException e) {
			throw new DaoException("Error en el mapeado de persona", e);
		}
	}

	private static Persona filaAPersonaConRol(ResultSet rs) {
		try {
			Long id = rs.getLong("p_id");
			String nombre = rs.getString("p_nombre");
			java.sql.Date fechaNacimientoDate = rs.getDate("p_fecha_nacimiento");
			LocalDate fechaNacimiento = fechaNacimientoDate != null ? fechaNacimientoDate.toLocalDate() : null;

			Long rolId = rs.getLong("r_id");

			Rol rol = null;

			if (rolId != null && rolId != 0) {
				String rolNombre = rs.getString("r_nombre");
				String rolDescripcion = rs.getString("r_descripcion");

				rol = new Rol(rolId, rolNombre, rolDescripcion);
			}

			return new Persona(id, nombre, fechaNacimiento, rol);
		} catch (SQLException e) {
			throw new DaoException("Error en el mapeado de persona", e);
		}
	}

	private static Object[] personaAFila(Persona persona) {
		ArrayList<Object> campos = new ArrayList<>();

		campos.add(persona.getNombre());
		campos.add(persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null);
		campos.add(persona.getRol() != null ? persona.getRol().getId() : null);

		if (persona.getId() != null) {
			campos.add(persona.getId());
		}

		return campos.toArray();
	}
}
