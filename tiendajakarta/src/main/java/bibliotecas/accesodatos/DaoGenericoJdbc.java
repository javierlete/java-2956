package bibliotecas.accesodatos;

import java.sql.ResultSet;
import java.util.function.Function;

import bibliotecas.fabrica.Fabrica;

/**
 * Clase para la generación de un CRUD automático
 * Necesita configurar en <code>fabrica.properties</code> la propiedad <code>dao.jdbc</code> para especificar la implementación deseada
 * Por defecto podemos usar <code>dao.jdbc=bibliotecas.accesodatos.DaoJdbcImpl</code>
 * @param <T> Tipo de datos para el CRUD específico
 */
public class DaoGenericoJdbc<T> implements Dao<T> {

	protected final DaoJdbc dao = (DaoJdbc) Fabrica.getObjeto("dao.jdbc");

	private String tabla;
	private String[] campos;

	private Function<ResultSet, T> mapeadorFilaAObjeto;
	private Function<T, Object[]> mapeadorObjetoACampos;

	/**
	 * Se deben pasar estos datos para poder construir automáticamente un CRUD
	 * @param tabla Nombre de la tabla que almacena la información del objeto
	 * @param campos Nombre de todos los campos de la tabla ordenados <strong>sin el id</strong>
	 * @param mapeadorFilaAObjeto Función que recibe un ResultSet y devuelve un objeto
	 * @param mapeadorObjetoACampos Función que recibe un objeto y devuelve un array de objetos con o sin id dependiendo de si se va a utilizar para un INSERT o para un UPDATE
	 */
	public DaoGenericoJdbc(String tabla, String[] campos, Function<ResultSet, T> mapeadorFilaAObjeto,
			Function<T, Object[]> mapeadorObjetoACampos) {
		super();
		this.tabla = tabla;
		this.campos = campos;
		this.mapeadorFilaAObjeto = mapeadorFilaAObjeto;
		this.mapeadorObjetoACampos = mapeadorObjetoACampos;
	}

	@Override
	public Iterable<T> obtenerTodos() {
		return dao.ejecutarSql("SELECT * FROM " + tabla + " ORDER BY id", mapeadorFilaAObjeto, null);
	}

	@Override
	public T obtenerPorId(Long id) {
		return dao.ejecutarSqlUno("SELECT * FROM " + tabla + " WHERE id=?", mapeadorFilaAObjeto, null, id);
	}

	@Override
	public T insertar(T objeto) {
		return dao.ejecutarSqlUno("INSERT INTO " + tabla + " (" + String.join(",", campos) + ") VALUES ("+ String.join(",", java.util.Collections.nCopies(campos.length, "?")) + ")", null,
				objeto, mapeadorObjetoACampos.apply(objeto));
	}

	@Override
	public T modificar(T objeto) {
		return dao.ejecutarSqlUno("UPDATE " + tabla + " SET " + String.join("=?, ", campos) + "=? WHERE id=?", null,
				objeto, mapeadorObjetoACampos.apply(objeto));
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarSql("DELETE FROM " + tabla + " WHERE id=?", null, null, id);
	}

}
