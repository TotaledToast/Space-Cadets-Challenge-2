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
    Integer CurrentLine = 0;

    List<While_Storage> Whiles = new ArrayList<While_Storage>();
    Dictionary<String, Integer> Variables = new Hashtable<>();

    void Run_Code(){
        for (String Line : programInput){
            String command = get_Command(Line);
            Run_Command(command, Line);
            Display_Variable();
            CurrentLine++;
        }
    }

    void Display_Variable(){
        System.out.println("Variable are: " + Variables);
    }

    void Run_Command(String command, String Line){
        switch (command) {
            case "clear":
                clear_Command(Line);
                break;
            case "incr":
                incr_Command(Line);
                break;
            case "decr":
                decr_Command(Line);
                break;
            case "while":
                while_Command(Line);
                break;
            case "end":
                end_Command(Line);
                break;
        }
    }

    void clear_Command(String Line){
        Variables.put(Line.replaceAll("^(\\s*)clear (.+);", "$2"), 0);
    }

    void incr_Command(String Line){
        String Identifier = Line.replaceAll("^(\\s*)incr (.+);", "$2");
        Variables.put(Identifier, Variables.get(Identifier) + 1);
    }

    void decr_Command(String Line){
        String Identifier = Line.replaceAll("^(\\s*)decr (.+);", "$2");
        Variables.put(Identifier, Variables.get(Identifier) - 1);
    }

    void while_Command(String Line){
        String Variable = Line.replaceAll("^(\\s*)while (.+) not 0 do;", "$2");
        Integer Whitespace = Line.replaceAll("^(\\s*)while (.+) not 0 do;", "$1").length();
        Whiles.add(new While_Storage(Variable, Whitespace, CurrentLine));
    }

    void end_Command(String Line){
        Integer Whitespace = Line.replaceAll("^(\\s*)end;", "$1").length();
        for (While_Storage element : Whiles){
            if (Objects.equals(element.Whitespace, Whitespace) && element.active){
                if (Variables.get(element.Variable) != 0){
                    CurrentLine = element.Startspace;
                } else {
                    element.active = false;
                }
            }
        }
    }

    String get_Command(String Line){
        return Line.replaceAll("^(\\s*)(.+).*", "$2");
    }

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

class While_Storage {
    String Variable;
    Integer Whitespace;
    Integer Startspace;
    //Integer Endspace;
    //List<String> inner_Code = new ArrayList<String>();
    Boolean active = true;

    While_Storage(String var, Integer White, Integer Start){
        Variable = var;
        Whitespace = White;
        Startspace = Start;
    }

}