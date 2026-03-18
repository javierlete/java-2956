package pruebas;

import java.time.LocalDate;

import pojos.Local;
import pojos.Persona;

public class LocalPruebas {
	public static void main(String[] args) {
		Local local = new Local("Bilbao", new Persona("Javier", LocalDate.of(2008, 3, 18)));
		
		System.out.println(local);
		
		local.entrar(new Persona("Pepe"));
		local.entrar(new Persona("Juan"));
		local.entrar(new Persona("Pedro"));
		local.entrar(new Persona("María"));
		
		for(Persona persona: local.getPersonas()) {
			System.out.println(persona);
		}
		
		System.out.println(local.getPersonas().size());
	}
}
