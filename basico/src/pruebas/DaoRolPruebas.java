package pruebas;

import accesodatos.DaoRol;
import bibliotecas.fabrica.Fabrica;
import pojos.Rol;

public class DaoRolPruebas {
	public static void main(String[] args) {
		DaoRol dao = (DaoRol) Fabrica.getObjeto("dao.rol");
		
		try {
			dao.insertar(new Rol(null, "Mago", "El que hace magia"));
			dao.insertar(new Rol(null, "Guerrero", "El que pega ostias"));
			dao.insertar(new Rol(null, "Elfo", "El que tira flechas"));
			dao.insertar(new Rol(null, "Clérigo", "El que pega ostias y cura"));
		} catch (Exception e) {
			System.out.println("Parece que ya tenías los registros, pasamos simplemente a visualizarlos");
		}
		
		for(Rol rol: dao.obtenerTodos()) {
			System.out.println(rol);
		}
	}
}
