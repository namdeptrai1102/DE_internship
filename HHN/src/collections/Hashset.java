package collections;
import java.util.HashSet;
import java.util.Iterator;

public class Hashset {

	public static void main(String[] args) {
		HashSet<Exercise> set = new HashSet<>();
		
		set.add(new Exercise("Plank", 60));
		set.add(new Exercise("Sit up", 45));
		set.add(new Exercise("Row", 30));
		Iterator<Exercise> iterator = set.iterator();
		System.out.println("Elements in HashSet:");
        while (iterator.hasNext()) {
            Exercise element = iterator.next();
            System.out.println(element);
        }
		
		boolean contains= set.contains(new Exercise("Lunge", 40));
        System.out.println("Contains Lunge? " + contains);
        
        int size1 = set.size();
        System.out.println("Size of HashSet: " + size1);
        
        System.out.println("The hashcode value of the map: " + set.hashCode());
        
        set.remove(new Exercise("Sit up", 45));
        int size2 = set.size();
        System.out.println("HashSet after remove: \n" + set + "\nSize " + size2);
        
        set.clear();
        boolean isEmpty = set.isEmpty();
        System.out.println("Is HashSet empty? " + isEmpty);
        
        // Clone set
        HashSet<Exercise> cloneset = new HashSet<>(set);
        //set.remove("Row");
     	System.out.println("Equality: " + set.equals(cloneset));
     	
	}

}
