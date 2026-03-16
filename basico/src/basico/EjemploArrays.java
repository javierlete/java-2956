package basico;

import java.util.Arrays;

public class EjemploArrays {
	public static void main(String[] args) {
		int[] arr = new int[3];

		arr[0] = 3;
		arr[1] = 5;
		arr[2] = 4;

		System.out.println(Arrays.toString(arr));

		System.out.println(arr.length);

		for (int dato : arr) {
			System.out.println(dato);
		}

		int buscar = 5;

		for (int dato : arr) {
			System.out.println("¿Es el " + dato + "?");

			if (dato == buscar) {
				System.out.println("Encontrado");
				break;
			}
		}

		boolean encontrado = false;

		for (int i = 0; i < arr.length && !encontrado; i++) {
			int dato = arr[i];

			System.out.println("¿Es el " + dato + "?");

			if (dato == buscar) {
				System.out.println("Encontrado");
				encontrado = true;
			}
		}
	}
}
