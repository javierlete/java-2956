package accesodatos;

import bibliotecas.accesodatos.Dao;
import bibliotecas.accesodatos.DaoException;
import pojos.Persona;

public interface DaoPersona extends Dao<Persona> {
	Iterable<Persona> buscarPorNombre(String nombre);
	default Iterable<Persona> obtenerTodosConRol() {
		throw new DaoException("NO IMPLEMENTADO");
	}
}
