package bibliotecas.fabrica;

import java.util.Properties;

import accesodatos.DaoPersonaList;
import accesodatos.DaoPersonaMap;

public class Fabrica {
	private static final Properties PROPS = new Properties();
	
	static {
		try {
			PROPS.load(Fabrica.class.getClassLoader().getResourceAsStream("fabrica.properties"));
		} catch (Exception e) {
			throw new FabricaException("No se ha podido cargar la configuración", e);
		}
	}
	
	public static Object getObjeto(String nombre) {
		return switch(PROPS.getProperty(nombre)) { 
			case "dao.list"-> new DaoPersonaList();
			case "dao.map" -> new DaoPersonaMap();
			default -> throw new IllegalArgumentException();
		};
	}
}
