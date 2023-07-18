package Exception;

import java.io.*;
public class IoException {

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new FileReader("binary.txt"))) {
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {  
			System.out.println("Done");  
		}    
	}
}
