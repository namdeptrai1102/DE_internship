package FactoryMethodPattern;

public class GetExerciseFactory {
	// Use getExercise to get object of type Exercise
	public Exercise getExercise(String type) {
		if (type.equalsIgnoreCase("HIITEXERCISE")) {
			return new HiitExercise();
		}
		else if (type.equalsIgnoreCase("COMPOUNDEXERCISE")) {
			return new CompoundExercise();
		}
		else if (type.equalsIgnoreCase("ISOLATEEXERCISE")) {
			return new IsolateExercise();
		}
		return null;
	}
}
