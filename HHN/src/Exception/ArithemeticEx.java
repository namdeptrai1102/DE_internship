package Exception;

public class ArithemeticEx extends Calc{
	int a;
	int b;
	public float result(int a, int b) throws ArithmeticException{
		if (b == 0) throw new ArithmeticException("Can't divide by zero");
		float c = a/b;
		return c;
	}
	public static void main(String[] args) {
		ArithemeticEx Ex = new ArithemeticEx();
		Ex.a = 10;
		Ex.b = 0;
		try {
        System.out.println(Ex.result(Ex.a,Ex.b));
		}catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
		}
	}

}
