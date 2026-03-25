package pruebas;

import bibliotecas.fabrica.Fabrica;

public class FabricaPruebas {
	public static void main(String[] args) {
		Object hoy = Fabrica.getObjeto("fecha");
		
		System.out.println(hoy);
	}
}
