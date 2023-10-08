import java.util.*;

public class Main {
    public static void main(String[] args) {
        //clear X;
        //incr X;
        //incr X;
        //incr X;
        //while X not 0 do;
        //   decr X;
        //end;
        String[] Things = {"clear X;","incr X;","while X not 0 do;","   decr X;","end;"};

        for (String element : Things){
            System.out.println(element);
            //System.out.println(element.matches("(\\s*)decr (.+);"));
            //System.out.println(element.replaceAll("(\\s*)decr (.+);", "$2"));
        }
    }
}