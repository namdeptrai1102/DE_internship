package FactoryMethodPattern;
import java.io.*;    
//Creational Pattern

/*Define an interface or abstract class for creating an object but let 
the subclasses decide which class to instantiate*/
public class GenerateRestTime {

	public static void main(String[] args) throws IOException{
		GetExerciseFactory exerciseFactory = new GetExerciseFactory();
		
		System.out.println("Enter the type of excercise: ");
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		
		String type = buff.readLine();
		System.out.println("Enter the number of reps: ");
		int reps = Integer.parseInt(buff.readLine());
		
		Exercise ex = exerciseFactory.getExercise(type);
		
		System.out.println("Rest time (second) for " + type + " of " + reps + " reps is: " );
		ex.getIntensity();
		ex.restSecond(reps);
	}

}
