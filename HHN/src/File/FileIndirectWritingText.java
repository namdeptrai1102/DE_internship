package File;
import java.io.*;

public class FileIndirectWritingText {

	private static final long serialVersionUID = 972003L;
	
	public static void main(String[] args) {
		// Write to file txt
		String s = "This line will be written";
		System.out.println("Writing to FileIndirectWritingText: " + s);
		
		try (FileWriter outFile = new FileWriter("IndirectBText.txt");
				BufferedWriter bWriter = new BufferedWriter(outFile)) {
			bWriter.write(s);
			//bWriter.close();
			//outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Serialization
		try (FileOutputStream fileOut = new FileOutputStream("IndirectBText.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
			objectOut.writeObject(s);
			 System.out.println("Object serialized and written to " + fileOut);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		// Deserialization
		try (FileInputStream fileIn = new FileInputStream("IndirectBText.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn)){
			String deserializedString = (String) objectIn.readObject();
			System.out.println("Read from file: " + deserializedString);
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();			
		}
	}
}
