package presentacion.consola;

import static bibliotecas.Consola.*;

import accesodatos.DaoPersona;
import bibliotecas.fabrica.Fabrica;
import pojos.Persona;

public class MantenimientoPersonas {
	private final static DaoPersona DAO = (DaoPersona) Fabrica.getObjeto("dao.persona");
	
	public static void main(String[] args) {
		boolean seguir = false;
		int opcion;

		do {
			menu();
			opcion = pedirOpcion();
			seguir = procesarOpcion(opcion);
		} while (seguir);
	}

	private static void menu() {
		pl("""
				
			MENU
			====
			1. Listado
			2. Buscar por id
			3. Insertar
			4. Modificar
			5. Borrar
			
			0. SALIR
										
			""");
	}

	private static int pedirOpcion() {
		return leerInt("Dime la opción deseada");
	}

	private static boolean procesarOpcion(int opcion) {
		return switch(opcion) {
		case 0 -> salir();
		case 1 -> listado();
		default -> opcionIncorrecta();
		};
	}

	private static boolean opcionIncorrecta() {
		pl("OPCIÓN INCORRECTA");
		return true;
	}

	private static boolean listado() {
		pl("LISTADO");
		
		for(Persona persona: DAO.obtenerTodosConRol()) {
			pl(persona);
		}
		
		return true;
	}

	private static boolean salir() {
		System.out.println("Gracias por usar el programa. Cerrando");
		return false;
	}
}
