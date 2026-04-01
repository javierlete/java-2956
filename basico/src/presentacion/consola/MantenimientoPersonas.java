package presentacion.consola;

import static bibliotecas.Consola.*;

import java.time.LocalDate;
import java.util.ArrayList;

import accesodatos.DaoPersona;
import accesodatos.DaoRol;
import bibliotecas.accesodatos.DaoException;
import bibliotecas.fabrica.Fabrica;
import pojos.Persona;
import pojos.Rol;

public class MantenimientoPersonas {
	private static final boolean CON_ID = true;
	private static final boolean SIN_ID = false;

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
		return leerInt("Dime la opción deseada", REQUERIDO);
	}

	private static boolean procesarOpcion(int opcion) {
		return switch (opcion) {
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

		for (Persona persona : DAO_PERSONA.obtenerTodosConRol()) {
			pl(persona);
		}

		return true;
	}

	private static boolean buscarPorId() {
		pl("BUSCAR POR ID");

		Long id = leerLong("Dime el id a buscar");

		Persona persona = DAO_PERSONA.obtenerPorId(id);

		if (persona != null) {
			pl(persona);
		} else {
			pl("No se ha encontrado ninguna persona con el id " + id);
		}

		return true;
	}

	private static boolean insertar() {
		pl("INSERTAR");

		Persona persona = leerPersona(SIN_ID);

		DAO_PERSONA.insertar(persona);

		return true;
	}

	private static boolean modificar() {
		pl("MODIFICAR");

		Persona persona = leerPersona(CON_ID);

		try {
			DAO_PERSONA.modificar(persona);
		} catch (DaoException e) {
			pl(e.getMessage());
		}

		return true;
	}

	private static Persona leerPersona(boolean conId) {
		Long id = null;

		if (conId) {
			id = leerLong("Id", REQUERIDO);
		}

		String nombre = leerString("Nombre", REQUERIDO);
		LocalDate fechaNacimiento = leerLocalDate("Fecha de nacimiento", NO_REQUERIDO);

		do {
			ArrayList<Long> ids = new ArrayList<>();

			for (Rol rol : DAO_ROL.obtenerTodos()) {
				pfl("%s: %s", rol.getId(), rol.getNombre());

				ids.add(rol.getId());
			}

			Long idRol = leerLong("Dime qué id de rol quieres", NO_REQUERIDO);

			if (idRol == null || ids.contains(idRol)) {
				return new Persona(id, nombre, fechaNacimiento, new Rol(idRol, null, null));
			} else {
				pl("No existe ese id de rol. Inténtalo de nuevo.");
			}
		} while (true);
	}

	private static boolean borrar() {
		pl("BORRAR");

		Long id = leerLong("Id", REQUERIDO);

		try {
			DAO_PERSONA.borrar(id);
		} catch (DaoException e) {
			pl(e.getMessage());
		}

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
