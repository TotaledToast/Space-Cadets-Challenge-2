import java.util.*;
import java.io.*;

public class Main {

    List<String> programInput = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the program file path: ");
        String path = reader.nextLine();
        reader.close();
        Program_Runner program = new Program_Runner(path);


    }



}

class Program_Runner {
    List<String> programInput = new ArrayList<String>();

    public Program_Runner(String fileName) throws FileNotFoundException {
        fileReader(fileName);
    }

    void fileReader(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner fileReader = new Scanner(input);
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            programInput.add(line);
        }
        fileReader.close();
    }
}