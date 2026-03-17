package pruebas;

import java.time.LocalDate;

import pojos.Persona;

public class PersonaPruebas {
	public static void main(String[] args) {
		Persona persona;

		persona = new Persona();

		System.out.println(persona.getNombre());
		
		persona.setId(1L);
		persona.setNombre("    Javier      ");
		persona.setFechaNacimiento(LocalDate.of(2000, 3, 18));

		persona.setId(persona.getId() + 1);

		System.out.println(persona.getId());
		System.out.println(persona.getNombre());
		System.out.println(persona.getFechaNacimiento());
		System.out.println(persona.getAnyos());
		
		Persona pepe = new Persona(2L, "   Pepe        ", LocalDate.of(2000, 1, 2));
		
		System.out.println(pepe);
		
		Persona juan = new Persona("Juan");
		
		System.out.println(juan);
	}
}
