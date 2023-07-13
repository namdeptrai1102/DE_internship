package AdapterPattern;
import java.io.*;
//Adapter class

public class GymCustomer extends GymDetails implements GymCard{
public void giveGymDetails() {
try {
	BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
	
	System.out.println("Enter the account name: ");
	String accname = buff.readLine();
	
	System.out.print("Enter the account ID: \n");  
	long id=Long.parseLong(buff.readLine());  
	   
	System.out.println("Enter the gym name: ");
	String gymname = buff.readLine();
	
	setAccName(accname);
	setAccId(id);
	setGymName(gymname);
	
	}catch(Exception e){
		e.printStackTrace();
	}
}

public String getGymCard() {
	String accname = getAccName();
	long id = getAccId();
	String gymname = getGymName();
	return ("The ID number " + id + " of " + accname + " in " + gymname + " gym is valid!");
	}
}