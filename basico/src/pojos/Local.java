package pojos;

import java.util.ArrayList;

public class Local {
	// VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	private Persona propietario;

	private ArrayList<Persona> personas = new ArrayList<>();

	// CONSTRUCTORES
	public Local(Long id, String nombre, Persona propietario) {
		setId(id);
		setNombre(nombre);
		setPropietario(propietario);
	}

	public Local(String nombre, Persona propietario) {
		this(null, nombre, propietario);
	}

	// GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new RuntimeException("El nombre es obligatorio");
		}

		this.nombre = nombre;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		if (propietario == null) {
			throw new RuntimeException("El propietario es obligatorio");
		}

		if (propietario.getFechaNacimiento() == null || propietario.getAnyos() < 18) {
			throw new RuntimeException("Debes ser mayor de 18 años para ser propietario");
		}

		this.propietario = propietario;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	// MÉTODOS DE INSTANCIA
	public void entrar(Persona persona) {
		if (persona == null) {
			throw new RuntimeException("Debe ser una persona");
		}

		personas.add(persona);
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Local [id=" + id + ", nombre=" + nombre + ", propietario=" + propietario + "]";
	}

}
