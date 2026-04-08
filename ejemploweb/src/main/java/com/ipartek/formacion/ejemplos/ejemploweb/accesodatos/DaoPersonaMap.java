package com.ipartek.formacion.ejemplos.ejemploweb.accesodatos;

import java.util.TreeMap;

import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;

public class DaoPersonaMap implements DaoPersona {

	private TreeMap<Long, Persona> personas = new TreeMap<>(); 
	
	@Override
	public Iterable<Persona> obtenerTodos() {
		return personas.values();
	}

	@Override
	public Persona obtenerPorId(Long id) {
		return personas.get(id);
	}

	@Override
	public Persona insertar(Persona persona) {
		Long id = personas.size() > 0 ? personas.lastKey() + 1L : 1L;
		
		persona.setId(id);
		
		personas.put(id, persona);
		
		return persona;
	}

	@Override
	public Persona modificar(Persona persona) {
		personas.put(persona.getId(), persona);
		
		return persona;
	}

	@Override
	public void borrar(Long id) {
		personas.remove(id);
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombre) {
		return personas.values().stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}

}
