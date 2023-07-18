package Exception;

public class ArithemeticEx {
	public static float result(int a, int b) throws ArithmeticException{
		if (b == 0) throw new ArithmeticException("Can't divide by zero");
		float c = a/b;
		return c;
	}
	public static void main(String[] args) {
		int a = 10, b = 0;
		try {
        System.out.println(result(a,b));
		}catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
		}
	}

}
