package pruebas;

import java.util.Iterator;

import accesodatos.DaoPersona;
import bibliotecas.fabrica.Fabrica;
import pojos.Persona;

public class DaoPruebas {
	public static void main(String[] args) {
		DaoPersona dao = (DaoPersona) Fabrica.getObjeto("dao.persona");
		
		System.out.println(dao.getClass().getName());
		
		dao.insertar(new Persona("Pepe"));
		dao.insertar(new Persona("Juan"));
		dao.insertar(new Persona("Pedro"));
		
		for(Persona p: dao.obtenerTodos()) {
			System.out.println(p);
		}
		
		for(Persona p: dao.buscarPorNombre("Pe")) {
			System.out.println(p);
		}
		
		dao.modificar(new Persona(2L, "Juanito", null));
		
		dao.borrar(3L);
			
		// BUCLE FOR..EACH EN CÓDIGO TRADUCIDO
		Iterable<Persona> iterable = dao.obtenerTodos();
		
		Iterator<Persona> iterator = iterable.iterator();
		
		while(iterator.hasNext()) {
			Persona p = iterator.next();
			
			System.out.println(p);
		}
		// FIN BUCLE FOR..EACH EN CÓDIGO TRADUCIDO
		
		for(Persona p: dao.obtenerTodos()) {
			System.out.println(p);
		}
		
		System.out.println(dao.obtenerPorId(1L));
	}
}
