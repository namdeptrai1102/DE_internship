package collections;
import java.util.ArrayList;
import java.util.List;

public class Arraylist {

	public static void main(String[] args) {
		ArrayList<String> arrl = new ArrayList<String>();
		
		arrl.add("Shoulder press");
		arrl.add("L-sit");
		arrl.add("Bicep curl");
		arrl.forEach((n) -> System.out.println(n));
		
		String exercise = arrl.get(0);
		System.out.println(exercise); 
		
		arrl.set(1, "Dead hang");
		
		arrl.remove(2);
		
		List<String> sarrl = arrl.subList(0, 1);
		System.out.println(sarrl);
	}

}
