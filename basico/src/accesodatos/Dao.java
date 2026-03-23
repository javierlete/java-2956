package accesodatos;

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	T obtenerPorId(Long id);
	
	T insertar(T o);
	T modificar(T o);
	void borrar(Long id);
}
