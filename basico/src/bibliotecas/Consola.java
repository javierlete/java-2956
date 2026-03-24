package bibliotecas;

public class Consola {
	// private static final Scanner SC = new Scanner(System.in);

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

}
