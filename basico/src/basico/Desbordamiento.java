package basico;

public class Desbordamiento {
	public static void main(String[] args) {
		int i = Integer.MAX_VALUE;
		
		System.out.println(i);
		System.out.println(++i);
		
		byte b = 0;
		
		while(true) {
			System.out.println(b++);
		}
	}
}
