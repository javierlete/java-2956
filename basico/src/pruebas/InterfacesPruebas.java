package pruebas;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class InterfacesPruebas {
	public static void main(String[] args) {
		Calculable calculable = new Sumar();

		System.out.println(calculable.operar(5, 6));

		calculable = new Restar();

		System.out.println(calculable.operar(5, 6));

		// CLASE ANÓNIMA
		calculable = new Calculable() {
			@Override
			public int operar(int op1, int op2) {
				return op1 * op2;
			}
		};

		System.out.println(calculable.operar(5, 6));

		// LAMBDA
		calculable = (a, b) -> a / b;

		System.out.println(calculable.operar(50, 6));

		BiFunction<Integer, Integer, Integer> biFuncion = (a, b) -> (int) Math.pow(a, b);
		
		System.out.println(biFuncion.apply(2, 3));
		
		BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
		
		System.out.println(binaryOperator.apply(5, 6));
	}

	// CLASE INTERNA
	static class Restar implements Calculable {

		@Override
		public int operar(int op1, int op2) {
			return op1 - op2;
		}

	}
}

// Interface funcional
interface Calculable {
	int operar(int op1, int op2);
}

// CLASE NORMAL
class Sumar implements Calculable {

	@Override
	public int operar(int op1, int op2) {
		return op1 + op2;
	}

}