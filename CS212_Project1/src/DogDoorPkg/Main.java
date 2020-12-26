//Robert Antenorcruz
//CS212 Prof.Fried
package DogDoorPkg;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        boolean runningApp = true;
        DogDoor d = new DogDoor(Utilities.CreatePIN());
        System.out.println("Successfully created PIN");

        while (runningApp) {
            System.out.println("\n\nCODE LIST");
            System.out.println("=========");
            System.out.println("0000 - EXIT  \t1111 - Toggle Windows  \t2222 - Toggle Scratch \nPIN - Toggle Lock \t 5555 - Test DogDoor");
            System.out.println("\n\nPlease enter code on keypad: ");

            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            d.inputPIN(n);
        }
    }

}








