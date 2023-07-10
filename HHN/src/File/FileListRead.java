package File;
import java.io.*;

public class FileListRead {
	public static void main(String[] args) {
		String directoryPath = "/home/hoanghainam/DE_internship/week1";
		
		// List of File
		File directory = new File(directoryPath);
		File[] files = directory.listFiles();
		if (files != null) {
			System.out.println("Directory: " + directoryPath);
			for (File file : files) {
				if(file.isFile()) {
					System.out.println(file.getName());
				}
			}
		}
		else {
			System.out.println("Directory doesn't exist or isn't a directory");
		}
		
		//Read file
		String filePath = "/home/hoanghainam/DE_internship/week1/Binary.txt";
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			System.out.println("File: " + filePath);
			String line;
			while ((line = reader.readLine()) != null)
				System.out.println(line);
		}
		catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace(); //Tells what happened and where in the code this happened.
		}
	}
}
