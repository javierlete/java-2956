package bibliotecas;

import accesodatos.DaoPersonaList;
import accesodatos.DaoPersonaMap;

public class Fabrica {
	public static Object getObjeto(String nombre) {
		return switch(nombre) { 
			case "dao.list"-> new DaoPersonaList();
			case "dao.map" -> new DaoPersonaMap();
			default -> throw new IllegalArgumentException();
		};
	}
}
