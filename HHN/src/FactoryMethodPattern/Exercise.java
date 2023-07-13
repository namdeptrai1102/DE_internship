package FactoryMethodPattern;

abstract class Exercise {
	protected double intensity;
	abstract void getIntensity();
	
	public void restSecond(int reps) {
		System.out.println(intensity*reps);
	}
}
