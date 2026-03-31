package pruebas;

import java.time.LocalDate;

import pojos.Persona;
import pojos.Rol;

public class RelacionUnoAVariosObjetosPruebas {
	public static void main(String[] args) {
		Rol mago = new Rol(1L, "Mago", "El que hace magia");
		
		Persona javier = new Persona(1L, "Javier", LocalDate.of(2000,  1, 2), mago);
		
		System.out.println(javier);
	}
}
