package File;
import java.io.*;

public class FileIndirectWritingText {

	public static void main(String[] args) {
		String s = "This line will be written";
		System.out.println("Writing to FileIndirectWritingText: " + s);
		try (FileWriter outFile = new FileWriter("IndirectBText.txt");
				BufferedWriter bWriter = new BufferedWriter(outFile)) {
			bWriter.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
