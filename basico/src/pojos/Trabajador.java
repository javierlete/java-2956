package pojos;

import java.time.LocalDate;

public class Trabajador extends Persona {
	private String dni;

	public Trabajador(Long id, String nombre, LocalDate fechaNacimiento, String dni) {
		super(id, nombre, fechaNacimiento);
		setDni(dni);
	}

	public Trabajador(String nombre, LocalDate fechaNacimiento, String dni) {
		this(null, nombre, fechaNacimiento, dni);
	}

	@Override
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new RuntimeException("La fecha de nacimiento en un trabajador es obligatoria");
		}
		
		if(fechaNacimiento.plusYears(18).isAfter(LocalDate.now())) {
			throw new RuntimeException("El trabajador debe ser mayor de edad");
		}

		super.setFechaNacimiento(fechaNacimiento);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni == null) {
			throw new RuntimeException("El DNI es obligatorio para todo trabajador");
		}

		this.dni = dni;
	}

	@Override
	public String toString() {
		return String.format("Trabajador [id=%s, nombre=%s, fechaNacimiento=%s, dni=%s]", id, nombre,
				fechaNacimiento, dni);
	}

	
}
