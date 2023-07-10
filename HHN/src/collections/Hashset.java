package collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Hashset {

	public static void main(String[] args) {
		HashSet<String> set = new HashSet<>();
		
		set.add("Plank");
		set.add("Sit up");
		set.add("Row");
		Iterator<String> iterator = set.iterator();
		System.out.println("Elements in HashSet:");
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
		
		boolean contains= set.contains("Lunge");
        System.out.println("Contains Lunge? " + contains);
        
        int size1 = set.size();
        System.out.println("Size of HashSet: " + size1);
        
        System.out.println("The hashcode value of the map: " + set.hashCode());
        
        set.remove("Sit up");
        int size2 = set.size();
        System.out.println("HashSet after remove \n" + set + "Size " + size2);
        
        set.clear();
        boolean isEmpty = set.isEmpty();
        System.out.println("Is HashSet empty? " + isEmpty);
        
        // Clone set
        HashSet<String> cloneset = new HashSet<>(set);
        //set.remove("Row");
     	System.out.println("Equality: " + set.equals(cloneset));
     	
	}

}
