package bibliotecas.accesodatos;

import java.sql.ResultSet;
import java.util.function.Function;

public interface DaoJdbc {

	<T> T ejecutarSqlUno(String sql, Function<ResultSet, T> mapeador, T objeto, Object... args);

	<T> Iterable<T> ejecutarSql(String sql, Function<ResultSet, T> mapeador, T objeto, Object... args);

}