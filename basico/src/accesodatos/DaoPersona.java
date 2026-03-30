package accesodatos;

import bibliotecas.accesodatos.Dao;
import pojos.Persona;

public interface DaoPersona extends Dao<Persona> {
	Iterable<Persona> buscarPorNombre(String nombre);
}
