package presentacion.consola;

import static bibliotecas.Consola.*;

import java.time.LocalDate;

import accesodatos.DaoPersona;
import accesodatos.DaoRol;
import bibliotecas.fabrica.Fabrica;
import pojos.Persona;
import pojos.Rol;

public class MantenimientoPersonas {
	private final static DaoPersona DAO_PERSONA = (DaoPersona) Fabrica.getObjeto("dao.persona");
	private final static DaoRol DAO_ROL = (DaoRol) Fabrica.getObjeto("dao.rol"); 
	
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
		case 1 -> listado();
		case 2 -> buscarPorId();
		case 3 -> insertar();
		case 4 -> modificar();
		case 5 -> borrar(); 
		case 0 -> salir();
		default -> opcionIncorrecta();
		};
	}

	private static boolean listado() {
		pl("LISTADO");
		
		for(Persona persona: DAO_PERSONA.obtenerTodosConRol()) {
			pl(persona);
		}
		
		return true;
	}

	private static boolean buscarPorId() {
		pl("BUSCAR POR ID");
		
		Long id = leerLong("Dime el id a buscar");
		
		pl(DAO_PERSONA.obtenerPorId(id));
		
		return true;
	}

	private static boolean insertar() {
		pl("INSERTAR");
		
		String nombre = leerString("Nombre");
		LocalDate fechaNacimiento = leerLocalDate("Fecha de nacimiento");
		
		for(Rol rol: DAO_ROL.obtenerTodos()) {
			pfl("%s: %s", rol.getId(), rol.getNombre());
		}
		
		Long idRol = leerLong("Dime qué id de rol quieres");
		
		Persona persona = new Persona(null, nombre, fechaNacimiento, new Rol(idRol, null, null));

		DAO_PERSONA.insertar(persona);
		
		return true;
	}

	private static boolean modificar() {
		pl("MODIFICAR");
		
		// TODO Refactorizar para juntar lo común de insertar y modificar
		Long id = leerLong("Id");
		String nombre = leerString("Nombre");
		LocalDate fechaNacimiento = leerLocalDate("Fecha de nacimiento");
		
		for(Rol rol: DAO_ROL.obtenerTodos()) {
			pfl("%s: %s", rol.getId(), rol.getNombre());
		}
		
		Long idRol = leerLong("Dime qué id de rol quieres");
		
		Persona persona = new Persona(id, nombre, fechaNacimiento, new Rol(idRol, null, null));
		
		DAO_PERSONA.modificar(persona);
		
		return true;
	}

	private static boolean borrar() {
		pl("BORRAR");
		
		Long id = leerLong("Id");

		DAO_PERSONA.borrar(id);
		
		return true;
	}

	private static boolean opcionIncorrecta() {
		pl("OPCIÓN INCORRECTA");
		
		return true;
	}

	private static boolean salir() {
		pl("Gracias por usar el programa. Cerrando");
		
		return false;
	}
}
