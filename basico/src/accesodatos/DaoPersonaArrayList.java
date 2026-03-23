package accesodatos;

import java.util.ArrayList;

import pojos.Persona;

public class DaoPersonaArrayList implements DaoPersona {

	private Long ultimoId = 0L;

	private ArrayList<Persona> personas = new ArrayList<>();

	@Override
	public Iterable<Persona> obtenerTodos() {
		return personas;
	}

	@Override
	public Persona obtenerPorId(Long id) {
		for (Persona p : personas) {
			if (p.getId() == id) {
				return p;
			}
		}

		return null;
	}

	@Override
	public Persona insertar(Persona persona) {
		persona.setId(++ultimoId);

		personas.add(persona);

		return persona;
	}

	@Override
	public Persona modificar(Persona persona) {
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getId() == persona.getId()) {
				personas.set(i, persona);

				return persona;
			}
		}

		return null;
	}

	@Override
	public void borrar(Long id) {
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getId() == id) {
				personas.remove(i);
			}
		}
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombre) {
		ArrayList<Persona> resultados = new ArrayList<>();

		for (Persona p : personas) {
			if (p.getNombre().contains(nombre)) {
				resultados.add(p);
			}
		}
		
		return resultados;
	}
}
