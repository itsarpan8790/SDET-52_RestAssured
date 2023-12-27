package practise;

public class Debugging {
	public static void main(String[] args) {
		int a = 10;
		int b = 0;
		result(a, b);
		System.out.println(a + b);
		System.out.println(a - b);
	}

	public static int result(int a, int b) {
		return a / b;
	}

}
