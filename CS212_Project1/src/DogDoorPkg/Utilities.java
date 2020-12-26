package DogDoorPkg;
import java.util.Scanner;

public class Utilities {

    static int CreatePIN(){
            Scanner in = new Scanner(System.in);
            System.out.println("Create 4-digit pin for Dog Door: ");
            checkValidInput(in);
            int input = in.nextInt();
            if(!checkPIN(input)) {
                return CreatePIN();
            }
            return input;
        }

    static void checkValidInput(Scanner input){
        while(!input.hasNextInt()) {
            System.out.println("Integer please");
            input.nextLine();
        }
    }

    static boolean checkPIN(int input){
        int length = (int)(Math.log10(input)+1);

        if(length != 4){
            System.out.println("Try again");
            return false;
        }
        else return true;
    }

}
