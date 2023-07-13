package File;

import java.io.*;

class Profile implements Serializable {
	private static final long serialVersionUID =1L;
    transient int key;
    int sex;
    transient final int valid = 1;
    String name;
    static int age;
 
    // Default constructor
public Profile(String name, int age, int key, int sex)
    {
        this.name = name;
        this.age = age;
        this.key = key;
        this.sex = sex;
    }
}
 
public class Serialized {
public static void printdata(Profile profile)
    {
 
        System.out.println("name = " + profile.name);
        System.out.println("age = " + profile.age);
        System.out.println("key = " + profile.key);
        System.out.println("sex = " + profile.sex);
        System.out.println("valid = " + profile.valid);
    }
 
public static void main(String[] args)
    {
        Profile profile = new Profile("Nam", 21, 28122002, 1);
        String filename = "profile.ser";
        // Serialization
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
 
            // Method for serialization of object
            out.writeObject(profile);
 
            System.out.println("Object has been serialized\n" + "Data before Deserialization.");
            printdata(profile);
 
            // value of static variable changed
            profile.age =22;
            profile.sex = 0;
        }
        catch (IOException e) {
            System.out.println("IOException is caught");
            e.printStackTrace();
        }
        profile = null;
        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
            profile = (Profile)in.readObject();
            System.out.println("Object has been deserialized\n" + "Data after Deserialization.");
            printdata(profile);
        }
        catch(IOException | ClassNotFoundException e) {
        System.out.println("Exception is caught");
		e.printStackTrace();			
        }
    }
}