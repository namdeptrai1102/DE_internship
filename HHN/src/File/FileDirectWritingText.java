package File;
import java.io.*;

public class FileDirectWritingText {
	public static void main(String[] args) throws IOException{
		InputStream istream;
		int c;
		final int EOF = -1;
		istream = System.in;
		FileWriter outFile = new FileWriter("DirectBText.txt");
		BufferedWriter bWriter = new BufferedWriter(outFile);
		System.out.println("Type characters to write in FileDirectWritingText");
		
		while ((c = istream.read()) != EOF)
			bWriter.write(c);
		bWriter.close();
	}
}
