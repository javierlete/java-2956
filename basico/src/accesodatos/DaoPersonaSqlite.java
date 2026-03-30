package accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import pojos.Persona;

public class DaoPersonaSqlite implements DaoPersona {

	private static final String URL = "jdbc:sqlite:ejemplo.db"; // System.getenv("JDBC_URL");
	private static final String USER = "";
	private static final String PASS = "";

	@Override
	public Iterable<Persona> obtenerTodos() {
		return ejecutarSql("SELECT * FROM personas", null);
	}

	@Override
	public Persona obtenerPorId(Long id) {
		return ejecutarSqlUno("SELECT * FROM personas WHERE id=?", null, id);
	}

	@Override
	public Persona insertar(Persona persona) {
		return ejecutarSqlUno("INSERT INTO personas (nombre, fecha_nacimiento) VALUES (?,?)", persona,
				persona.getNombre(),
				persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null);
	}

	@Override
	public Persona modificar(Persona persona) {
		return ejecutarSqlUno("UPDATE personas SET nombre=?, fecha_nacimiento=? WHERE id=?", persona,
				persona.getNombre(),
				persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null,
				persona.getId());
	}

	@Override
	public void borrar(Long id) {
		ejecutarSql("DELETE FROM personas WHERE id=?", null, id);
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombreParcial) {
		return ejecutarSql("SELECT * FROM personas WHERE nombre LIKE ?", null, nombreParcial);
	}

	public Persona ejecutarSqlUno(String sql, Persona persona, Object... args) {
		Iterable<Persona> personas = ejecutarSql(sql, persona, args);

		if (personas.iterator().hasNext()) {
			return personas.iterator().next();
		}

		return null;
	}

	public Iterable<Persona> ejecutarSql(String sql, Persona persona, Object... args) {
		Collection<Persona> personas = new HashSet<>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con.prepareStatement(sql);) {
			for (int i = 0; i < args.length; i++) {
				pst.setObject(i + 1, args[i]);
			}

			if (sql.trim().startsWith("SELECT")) {
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Long id = rs.getLong("id");
						String nombre = rs.getString("nombre");
						java.sql.Date fechaNacimientoDate = rs.getDate("fecha_nacimiento");
						LocalDate fechaNacimiento = fechaNacimientoDate != null ? fechaNacimientoDate.toLocalDate()
								: null;

						personas.add(new Persona(id, nombre, fechaNacimiento));
					}

					return personas;
				}
			} else {
				pst.executeUpdate();

				personas.add(persona);

				return personas;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

}
