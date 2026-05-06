package bibliotecas.accesodatos;

import static bibliotecas.accesodatos.DaoJpa.ejecutarJpa;

public class DaoGenericoJpa<T> implements Dao<T> {

	private final Class<T> clase;
	
	public DaoGenericoJpa(Class<T> clase) {
		this.clase = clase;
	}
	
	@Override
	public Iterable<T> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from " + clase.getSimpleName(), clase).getResultList());
	}

	@Override
	public T obtenerPorId(Long id) {
		return ejecutarJpa(em -> em.find(clase, id));
	}

	@Override
	public T insertar(T objeto) {
		return ejecutarJpa(em -> {
			em.persist(objeto);
			return objeto;
		});
	}

	@Override
	public T modificar(T objeto) {
		return ejecutarJpa(em -> {
			em.merge(objeto);
			return objeto;
		});
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(clase, id));
			return null;
		});
	}
}
