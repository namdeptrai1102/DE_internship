package File;
import java.io.*;

public class FileList_Read {
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
	}
}
