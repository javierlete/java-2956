package accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import bibliotecas.accesodatos.DaoException;
import bibliotecas.accesodatos.DaoJdbc;
import bibliotecas.fabrica.Fabrica;
import pojos.Persona;

public class DaoPersonaSqlite implements DaoPersona {

	private static final DaoJdbc DAO = (DaoJdbc) Fabrica.getObjeto("dao.jdbc");
	
	@Override
	public Iterable<Persona> obtenerTodos() {
		return DAO.ejecutarSql("SELECT * FROM personas", DaoPersonaSqlite::filaAPersona, null);
	}

	@Override
	public Persona obtenerPorId(Long id) {
		return DAO.ejecutarSqlUno("SELECT * FROM personas WHERE id=?", DaoPersonaSqlite::filaAPersona, null, id);
	}

	@Override
	public Persona insertar(Persona persona) {
		return DAO.ejecutarSqlUno("INSERT INTO personas (nombre, fecha_nacimiento) VALUES (?,?)",
				null, persona, persona.getNombre(),
				persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null);
	}

	@Override
	public Persona modificar(Persona persona) {
		return DAO.ejecutarSqlUno("UPDATE personas SET nombre=?, fecha_nacimiento=? WHERE id=?",
				null, persona, persona.getNombre(),
				persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null,
				persona.getId());
	}

	@Override
	public void borrar(Long id) {
		DAO.ejecutarSql("DELETE FROM personas WHERE id=?", null, null, id);
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombreParcial) {
		return DAO.ejecutarSql("SELECT * FROM personas WHERE nombre LIKE ?", DaoPersonaSqlite::filaAPersona, null,
				nombreParcial);
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

}
