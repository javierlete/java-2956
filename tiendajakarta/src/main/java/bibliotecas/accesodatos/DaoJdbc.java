package bibliotecas.accesodatos;

import java.sql.ResultSet;
import java.util.function.Function;

public interface DaoJdbc {

	/**
	 * Ejecuta una sentencia SQL y devuelve UN registro mapeado al objeto que le corresponde
	 * @param <T>		Tipo de los objetos que representan cada fila de la SQL
	 * @param sql		Sentencia SQL a ejecutar
	 * @param mapeador	Función que recibiendo una fila en un ResultSet devuelve un objeto de tipo T
	 * @param objeto	Objeto que se va a insertar o modificar
	 * @param args		Argumentos ordenados de la sentencia que se va a ejecutar
	 * @return 			Objeto de tipo T o null
	 */
	<T> T ejecutarSqlUno(String sql, Function<ResultSet, T> mapeador, T objeto, Object... args);

	/**
	 * Ejecuta una sentencia SQL y devuelve los registros mapeados a objetos
	 * @param <T>		Tipo de los objetos que representan cada fila de la SQL
	 * @param sql		Sentencia SQL a ejecutar
	 * @param mapeador	Función que recibiendo una fila en un ResultSet devuelve un objeto de tipo T
	 * @param objeto	Objeto que se va a insertar o modificar
	 * @param args		Argumentos ordenados de la sentencia que se va a ejecutar
	 * @return 			Iterable de objetos del tipo T. En el caso de que no haya ninguna fila, no valdrá null, sino que será un Iterable de 0 elementos
	 */
	<T> Iterable<T> ejecutarSql(String sql, Function<ResultSet, T> mapeador, T objeto, Object... args);

}