package accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import bibliotecas.accesodatos.DaoException;
import bibliotecas.accesodatos.DaoGenericoJdbc;
import pojos.Persona;

public class DaoPersonaSqliteGenerico extends DaoGenericoJdbc<Persona> implements DaoPersona {

	public DaoPersonaSqliteGenerico() {
		super("personas", new String[] { "nombre", "fecha_nacimiento" }, DaoPersonaSqliteGenerico::filaAPersona,
				DaoPersonaSqliteGenerico::personaAFila);
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombreParcial) {
		return dao.ejecutarSql("SELECT * FROM personas WHERE nombre LIKE ?", DaoPersonaSqliteGenerico::filaAPersona, null,
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

	private static Object[] personaAFila(Persona persona) {
		ArrayList<Object> campos = new ArrayList<>();

		campos.add(persona.getNombre());
		campos.add(persona.getFechaNacimiento() != null ? java.sql.Date.valueOf(persona.getFechaNacimiento()) : null);

		if (persona.getId() != null) {
			campos.add(persona.getId());
		}

		return campos.toArray();
	}
}
