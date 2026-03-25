package pruebas;

import static bibliotecas.Consola.*;

public class ConsolaPruebas {
	public static void main(String[] args) {
		int numero = leerInt("Id");
		
		pl(numero);

		String nombre = leerString("Nombre");
		
		pl(nombre);
	}
}
