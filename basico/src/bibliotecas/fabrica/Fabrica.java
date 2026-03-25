package bibliotecas.fabrica;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

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
		try {
			Object objeto = null;
			
			String nombreClase = PROPS.getProperty(nombre);
			
			Class<?> clase = Class.forName(nombreClase);
			Constructor<?> constructor = clase.getConstructor();
			objeto = constructor.newInstance();
			
			return objeto;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new FabricaException("No se ha podido crear el objeto " + nombre, e);
		}
	}
}
