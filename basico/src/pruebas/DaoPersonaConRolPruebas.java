package pruebas;

import accesodatos.DaoPersona;
import bibliotecas.fabrica.Fabrica;
import pojos.Persona;

public class DaoPersonaConRolPruebas {
	public static void main(String[] args) {
		DaoPersona dao = (DaoPersona) Fabrica.getObjeto("dao.persona");

		for (Persona persona : dao.obtenerTodosConRol()) {
			System.out.println(persona);
			if (persona.getRol() != null) {
				System.out.printf("%s es un %s y es %s\n", persona.getNombre(), persona.getRol().getNombre(),
						persona.getRol().getDescripcion().toLowerCase());
			}
		}
	}
}
