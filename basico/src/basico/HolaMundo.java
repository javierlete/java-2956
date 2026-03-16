package basico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class HolaMundo {
	public static void main(String[] args) {
		System.out.println("Hola");

		Date date = new Date();

		System.out.println(date);

		double d1 = 0.1, d2 = 0.2;

		double suma = d1 + d2;

		System.out.println(suma);

		BigDecimal bd1 = new BigDecimal("0.1");
		BigDecimal bd2 = new BigDecimal("0.2");

		BigDecimal bdSuma = bd1.add(bd2);
		
		System.out.println(bd1);
		System.out.println(bd2);
		System.out.println(bdSuma);
		
		bd1 = new BigDecimal("10");
		bd2 = new BigDecimal("3");
		
		BigDecimal bdDivision = bd1.divide(bd2, 2, RoundingMode.HALF_UP);
		
		System.out.println(bdDivision);
		
		int x = 5;
		
		System.out.println(x++);
		System.out.println(x);
	}
}
