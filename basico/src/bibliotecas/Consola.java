package bibliotecas;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Function;

public class Consola {
	private static final Scanner SC = new Scanner(System.in);

	private Consola() {
	}

	public static void pl() {
		System.out.println();
	}

	public static void pl(Object o) {
		System.out.println(o);
	}

	public static void p(Object o) {
		System.out.print(o);
	}

	public static void pfl(String formato, Object... objetos) {
		System.out.printf(formato + "\n", objetos);
	}

	public static String leerString(String mensaje) {
		p(mensaje + ": ");
		return SC.nextLine();
	}
	
	public static <T> T leer(String mensaje, Function<String, T> funcion) {
		boolean hayError = true;

		T resultado = null;

		do {
			try {
				resultado = funcion.apply(leerString(mensaje));
				hayError = false;
			} catch (Exception e) {
				pl("No se ha podido convertir");
			}
		} while (hayError);

		return resultado;
	}

	public static int leerInt(String mensaje) {
		return leer(mensaje, Integer::parseInt);
	}

	public static long leerLong(String mensaje) {
		return leer(mensaje, Long::parseLong);
	}
	
	public static double leerDouble(String mensaje) {
		return leer(mensaje, Double::parseDouble);
	}
	
	public static LocalDate leerLocalDate(String mensaje) {
		return leer(mensaje, LocalDate::parse);
	}
}
