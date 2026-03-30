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
		Collection<Persona> personas = new HashSet<>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM personas");
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nombre = rs.getString("nombre");
				java.sql.Date fechaNacimientoDate = rs.getDate("fecha_nacimiento");
				LocalDate fechaNacimiento = fechaNacimientoDate != null ? fechaNacimientoDate.toLocalDate() : null;

				Persona persona = new Persona(id, nombre, fechaNacimiento);

				personas.add(persona);
			}

			return personas;
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

	@Override
	public Persona obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM personas WHERE id=?");) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String nombre = rs.getString("nombre");
					java.sql.Date fechaNacimientoDate = rs.getDate("fecha_nacimiento");
					LocalDate fechaNacimiento = fechaNacimientoDate != null ? fechaNacimientoDate.toLocalDate() : null;

					return new Persona(id, nombre, fechaNacimiento);
				}

				return null;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

	@Override
	public Persona insertar(Persona persona) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con
						.prepareStatement("INSERT INTO personas (nombre, fecha_nacimiento) VALUES (?,?)");) {
			pst.setString(1, persona.getNombre());
			pst.setDate(2,
					persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null);

			pst.executeUpdate();

			return persona;
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

	@Override
	public Persona modificar(Persona persona) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con
						.prepareStatement("UPDATE personas SET nombre=?, fecha_nacimiento=? WHERE id=?");) {
			pst.setString(1, persona.getNombre());
			pst.setDate(2,
					persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null);
			pst.setLong(3, persona.getId());

			pst.executeUpdate();

			return persona;
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con.prepareStatement("DELETE FROM personas WHERE id=?");) {
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombreParcial) {
		Collection<Persona> personas = new HashSet<>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM personas WHERE nombre LIKE ?");) {
			pst.setString(1, "%" + nombreParcial + "%");

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Long id = rs.getLong("id");
					String nombre = rs.getString("nombre");
					java.sql.Date fechaNacimientoDate = rs.getDate("fecha_nacimiento");
					LocalDate fechaNacimiento = fechaNacimientoDate != null ? fechaNacimientoDate.toLocalDate() : null;

					personas.add(new Persona(id, nombre, fechaNacimiento));
				}

				return personas;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la capa DAO", e);
		}
	}

}
