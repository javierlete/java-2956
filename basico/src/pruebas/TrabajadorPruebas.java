package pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import pojos.Local;
import pojos.Persona;
import pojos.Trabajador;
import pojos.TrabajadorIndefinido;
import pojos.TrabajadorPorHoras;

import static bibliotecas.Consola.*;

public class TrabajadorPruebas {
	public static void main(String[] args) {
		Trabajador trabajador = new TrabajadorIndefinido(null, "Javier", LocalDate.of(2008, 3, 18), "12345678A",
				new BigDecimal(12345), 14);

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

		Object o = trabajador2;

		System.out.println(o);

		Local local = new Local("Bilbao", trabajador);

		local.entrar(trabajador);

		System.out.println(local);

		Persona pepe = new Persona("Pepe");

		local.entrar(pepe);
		local.entrar(new Persona(null, "Juan", LocalDate.now().minusYears(18)));
		local.entrar(new Persona(null, "Pedro", LocalDate.now().minusYears(42)));
		local.entrar(new TrabajadorPorHoras(null, "María", LocalDate.now().minusYears(20), "12345678A",
				new BigDecimal(30), 80));

		List<Persona> personasConEdad = local.getPersonas().stream().filter(p -> p.getFechaNacimiento() != null)
				.toList();

		int numero = personasConEdad.size();

		double media = personasConEdad.stream().mapToDouble(Persona::getAnyos).reduce(0.0,
				(totalAcumulado, edad) -> totalAcumulado + edad) / numero;

		p("MEDIA: " + media);

		System.out.println("DESPUÉS DE ENTRADA");

		for (Persona p : local.getPersonas()) {
			System.out.println(p);

			if (p instanceof Trabajador t) {
				// Trabajador t = (Trabajador) p;

				System.out.println(t.getAnyos());
				System.out.println(t.getSueldoMensual());
			}
		}

		local.salir(pepe);
		local.salir(new Persona("Juan"));
		local.salir(new TrabajadorPorHoras(null, "María", LocalDate.now().minusYears(20), "12345678A",
				new BigDecimal(30), 80));

		System.out.println("DESPUÉS DE SALIDA");

		for (Persona p : local.getPersonas()) {
			System.out.println(p);
		}

		if (new Persona("María") == new Persona("María")) {
			System.out.println("Son el mismo");
		} else {
			System.out.println("Son dos objetos separados");
		}

		if (new Persona("María").equals(new Persona("María"))) {
			System.out.println("Son iguales");
		} else {
			System.out.println("Son diferentes");
		}
	}
}
