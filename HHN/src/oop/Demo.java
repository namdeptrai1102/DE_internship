package oop;

public class Demo {
	public static void main(String[] args) {
		// Encapsulation
		Push ex1 = new Push(null);
		Push ex2 = new Push(null,null,null);//Static Polymorphism
		ex1.setName("squat");
		System.out.println(ex1.getName());
		ex2.practice(); // Abstraction
		Exercise ex3 = new Pull(null);//Inheritance
		ex3.setName("pullup");
		System.out.println(ex3.getName());
		ex3.practice();//Dynamic Polymorphism
		
		Practice.StartWorkout();
	}

}
