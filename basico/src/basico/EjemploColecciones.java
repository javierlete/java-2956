package basico;

import java.util.ArrayList;

public class EjemploColecciones {
	public static void main(String[] args) {
		ArrayList<Integer> lista = new ArrayList<>();
		
		lista.add(1);
		lista.add(2);
		
		System.out.println(lista);
		
		for(Integer dato: lista) {
			System.out.println(dato);
		}
	}
}
