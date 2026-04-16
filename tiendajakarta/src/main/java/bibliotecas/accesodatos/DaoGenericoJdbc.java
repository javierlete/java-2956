package bibliotecas.accesodatos;

import java.sql.ResultSet;
import java.util.function.Function;

import bibliotecas.fabrica.Fabrica;

public class DaoGenericoJdbc<T> implements Dao<T> {

	protected final DaoJdbc dao = (DaoJdbc) Fabrica.getObjeto("dao.jdbc");

	private String tabla;
	private String[] campos;

	private Function<ResultSet, T> mapeadorFilaAObjeto;
	private Function<T, Object[]> mapeadorObjetoACampos;

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
