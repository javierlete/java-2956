package accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bibliotecas.accesodatos.DaoException;
import bibliotecas.accesodatos.DaoGenericoJdbc;
import pojos.Rol;

public class DaoRolSqliteGenerico extends DaoGenericoJdbc<Rol> implements DaoRol {

	public DaoRolSqliteGenerico() {
		super("roles", new String[] { "nombre", "descripcion" }, DaoRolSqliteGenerico::filaARol,
				DaoRolSqliteGenerico::rolAFila);
	}

	private static Rol filaARol(ResultSet rs) {
		try {
			Long id = rs.getLong("id");
			String nombre = rs.getString("nombre");
			String descripcion = rs.getString("descripcion");
			

			return new Rol(id, nombre, descripcion);
		} catch (SQLException e) {
			throw new DaoException("Error en el mapeado de rol", e);
		}
	}

	private static Object[] rolAFila(Rol rol) {
		ArrayList<Object> campos = new ArrayList<>();

		campos.add(rol.getNombre());
		campos.add(rol.getDescripcion());

		if (rol.getId() != null) {
			campos.add(rol.getId());
		}

		return campos.toArray();
	}
}
