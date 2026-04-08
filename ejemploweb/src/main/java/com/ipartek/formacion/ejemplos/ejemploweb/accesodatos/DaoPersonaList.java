package com.ipartek.formacion.ejemplos.ejemploweb.accesodatos;

import java.util.LinkedList;
import java.util.List;

import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;

public class DaoPersonaList implements DaoPersona {

	private Long ultimoId = 0L;

	private List<Persona> personas = new LinkedList<>();

	@Override
	public Iterable<Persona> obtenerTodos() {
		return personas;
	}

	@Override
	public Persona obtenerPorId(Long id) {
		return personas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
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
		personas.stream().filter(p -> p.getId() == id).findFirst().ifPresent(p -> personas.remove(p));
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombre) {
		return personas.stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}
}
