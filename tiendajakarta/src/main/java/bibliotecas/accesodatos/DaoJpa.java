package bibliotecas.accesodatos;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa {
	private final static EntityManagerFactory EMF;

	static {
		try {
			Properties props = new Properties();
			props.load(DaoJpa.class.getClassLoader().getResourceAsStream("accesodatos.properties"));

			String persistenceUnit = props.getProperty("jpa.persistence-unit");

			EMF = Persistence.createEntityManagerFactory(persistenceUnit);
			
		} catch (IOException e) {
			throw new DaoException("Error al buscar la unidad de persistencia", e);
		}
	}

	public static <T> T ejecutarJpa(Function<EntityManager, T> funcion) {
		EntityTransaction t = null;
		EntityManager em = null;

		try {
			em = EMF.createEntityManager();
			t = em.getTransaction();

			t.begin();

			T respuesta = funcion.apply(em);

			t.commit();

			return respuesta;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new DaoException("Error en la operación JPA", e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}
}
