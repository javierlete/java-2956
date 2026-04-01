package pojos;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Persona {
	// VARIABLES DE INSTANCIA
	protected Long id;
	protected String nombre;
	protected LocalDate fechaNacimiento;
	
	private Rol rol;

	// CONSTRUCTORES
	public Persona(Long id, String nombre, LocalDate fechaNacimiento, Rol rol) {
		setId(id);
		setNombre(nombre);
		setFechaNacimiento(fechaNacimiento);
		setRol(rol);
	}
	
	public Persona(Long id, String nombre, LocalDate fechaNacimiento) {
		this(id, nombre, fechaNacimiento, null);
	}

	public Persona(String nombre, LocalDate fechaNacimiento) {
		this(null, nombre, fechaNacimiento, null);
	}

	public Persona(String nombre) {
		this(null, nombre, null, null);
	}

	public Persona() {
		this(null, "ANÓNIMO", null, null);
	}

	// Constructor de copia
	public Persona(Persona persona) {
		this(persona.getId(), persona.getNombre(), persona.getFechaNacimiento());
	}

	// GETTERS Y SETTERS (MÉTODOS DE ACCESO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null && id < 0L) {
			throw new RuntimeException("No se admite un valor de id negativo");
		}

		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new RuntimeException("No se admiten valores vacíos");
		}

		this.nombre = nombre.trim();
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
			throw new RuntimeException("No puedes nacer en el futuro");
		}

		this.fechaNacimiento = fechaNacimiento;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	// MÉTODO DE INSTANCIA
	public int getAnyos() {
		if (fechaNacimiento == null) {
			throw new RuntimeException("No sé la fecha de nacimiento");
		}

		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaNacimiento, id, nombre, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(rol, other.rol);
	}

	@Override
	public String toString() {
		return String.format("Persona [id=%s, nombre=%s, fechaNacimiento=%s, rol=%s]", id, nombre, fechaNacimiento,
				rol);
	}

}
