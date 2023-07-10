package File;		
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWritingBinary {
    public static void main(String[] args) {
        String filePath = "/home/hoanghainam/DE_internship/week1/Binary.txt";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] data = { 0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x20, 0x57, 0x6F, 0x72, 0x6C, 0x64 }; // Hello World
            fos.write(data);
            System.out.println("Binary file written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the binary file.");
            e.printStackTrace();
        }
    }
}