package bibliotecas;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Function;

public class Consola {
	public static final boolean NO_REQUERIDO = false;
	public static final boolean REQUERIDO = true;

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
		return leerString(mensaje, NO_REQUERIDO);
	}

	public static String leerString(String mensaje, boolean requerido) {
		String texto;

		do {
			p(mensaje + (requerido ? " (OBLIGATORIO)" : "") + ": ");
			texto = SC.nextLine();

			if (!requerido && texto.isBlank()) {
				return null;
			}

		} while (requerido && texto.isBlank());

		return texto;
	}

	public static <T> T leer(String mensaje, Function<String, T> funcion) {
		return leer(mensaje, funcion, REQUERIDO);
	}

	public static <T> T leer(String mensaje, Function<String, T> funcion, boolean requerido) {
		boolean hayError = true;

		T resultado = null;

		do {
			try {
				String texto = leerString(mensaje, requerido);

				if (texto == null) {
					return null;
				}

				resultado = funcion.apply(texto);
				hayError = false;
			} catch (Exception e) {
				pl("No se ha podido convertir");
			}
		} while (hayError);

		return resultado;
	}

	public static Integer leerInt(String mensaje) {
		return leerInt(mensaje, REQUERIDO);
	}

	public static Integer leerInt(String mensaje, boolean requerido) {
		return leer(mensaje, Integer::parseInt, requerido);
	}

	public static Long leerLong(String mensaje) {
		return leerLong(mensaje, REQUERIDO);
	}

	public static Long leerLong(String mensaje, boolean requerido) {
		return leer(mensaje, Long::parseLong, requerido);
	}

	public static Double leerDouble(String mensaje) {
		return leerDouble(mensaje, REQUERIDO);
	}

	public static Double leerDouble(String mensaje, boolean requerido) {
		return leer(mensaje, Double::parseDouble, requerido);
	}

	public static LocalDate leerLocalDate(String mensaje) {
		return leerLocalDate(mensaje, REQUERIDO);
	}

	public static LocalDate leerLocalDate(String mensaje, boolean requerido) {
		return leer(mensaje, LocalDate::parse, requerido);
	}
}
