package AdapterPattern;
// Structural design patterns

/* Converts the interface of a class into another interface that a client wants*/
public class ClientDemo {
public static void main(String args[]){  
  GymCard targetInterface=new GymCustomer();  
  targetInterface.giveGymDetails();  
  System.out.print(targetInterface.getGymCard());  
 }   
}
