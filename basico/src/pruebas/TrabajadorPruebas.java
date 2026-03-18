package pruebas;

import java.time.LocalDate;

import pojos.Persona;
import pojos.Trabajador;

public class TrabajadorPruebas {
	public static void main(String[] args) {
		Trabajador trabajador = new Trabajador("Javier", LocalDate.of(2008, 3, 18), "12345678A");
		
		System.out.println(trabajador);
//		System.out.println(trabajador.getDni());
		
		trabajador.setId(1L);
		trabajador.setNombre("Javier");
		trabajador.setFechaNacimiento(LocalDate.of(2000, 1, 2));
		
		System.out.println(trabajador);
//		System.out.println(trabajador.getDni());
		
		Persona persona = trabajador;
		
//		System.out.println(persona.getDni());
		
		Trabajador trabajador2 = (Trabajador) persona;
		
		System.out.println(trabajador2.getDni());
	}
}
