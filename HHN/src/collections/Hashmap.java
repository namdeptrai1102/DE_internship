package collections;
import java.util.HashMap;

public class Hashmap {

	public static void main(String[] args) {
		HashMap <Integer, String> hm = new HashMap<>();
		
		hm.put(1,"Squat");
		hm.put(2, "Deadlift");
		hm.put(3, "Bench Press");
		System.out.println(hm);
		
		String name = hm.get(1);
		System.out.println("The fisrt exercise: " + name);
		
		boolean exists1 = hm.containsKey(4);
		System.out.println("Key 4 exists: " + exists1);
		boolean exists2 = hm.containsValue("Squat");
		System.out.println("Value Squat exists: " + exists2);
		
		hm.remove(3);
		System.out.println(hm);
		
		int size = hm.size();
		System.out.println("Size of HashMap: " + size);
		
		System.out.println("The hashcode value of the map: " + hm.hashCode());
		
		// Copy hm to nhm
		HashMap <Integer, String> nhm = new HashMap<>();
		 for (int key : hm.keySet()) {
	            String value = hm.get(key);
	            nhm.put(key, value);
	        }
		 //hm.remove(2);
		 System.out.println("Equality: " + hm.equals(nhm));
	}

}
