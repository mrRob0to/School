package DogDoorPkg;
import java.util.concurrent.TimeUnit;


public class DogDoor implements Remote {

    private int pin;
    private boolean boolLock, boolWindows, boolScratch;

    //Constructors
    DogDoor(){
        pin = 1234;
    }

    DogDoor(Integer pin){
        if(pin == 1111 || pin == 2222 || pin == 0000 || pin == 5555){
            System.out.println("Cannot use: " +pin + "\nPIN is set to Default 1234");
            this.pin = 0000;
        }
        else {
            this.pin = pin;
        }
    }

    DogDoor(DogDoor copy){
        pin = copy.pin;
        boolLock = copy.boolLock;
        boolWindows = copy.boolWindows;
        boolScratch = copy.boolScratch;
    }

    //Getters & Setters
    int getPin(){return pin;}
    boolean getBoolLock(){return boolLock;}
    boolean getBoolWindows(){return boolWindows;}
    boolean getBoolScratch(){return boolScratch;}

    void setPin(int n){pin = n;}
    void setBoolLock(boolean n){boolLock = n;}
    void setBoolWindows(boolean n){boolWindows = n;}
    void setBoolScratch(boolean n){boolScratch = n;}

    @Override
    public String toString(){
        String str = "PIN is: " + pin +"\nLock Status: " + boolLock + "\nWindow Status: " + boolWindows +
                "\nScratch Status: " + boolScratch;
        return str;
    }


    //Remote Interface
    @Override
    public void ToggleLock() throws InterruptedException {
        if(boolLock){
            boolLock = false;
            doorTimer();
        }
        else System.out.println("The dog door is closed"); boolLock = true;
    }

    @Override
    public void inputPIN(int input) throws InterruptedException {
        if (pin == input) ToggleLock();
        else if (0000 == input) System.exit(1);
        else if (1111 == input) ToggleWindows();
        else if (2222 == input) ToggleScratch();
        else if (5555 == input) TestDogDoor();
        else System.out.println("Incorrect Code");
    }

    @Override
    public void ToggleWindows() {
        if(boolWindows){
            System.out.println("Unlock Windows enabled...");
            boolWindows = false;
        }
        else System.out.println("Unlock Windows disabled..."); boolWindows = true;
    }

    @Override
    public void ToggleScratch() {
        if(boolScratch){
            System.out.println("Door Scratch enabled...");
            boolScratch = false;
        }
        else System.out.println("Door Scratch disabled..."); boolScratch = true;
    }


    //Methods
    public void doorTimer() throws InterruptedException {
        final int maxSeconds = 8;
        int timer = 0;
        boolLock = false;
        System.out.println("The dog door is open.");
            while (timer < maxSeconds){
                TimeUnit time = TimeUnit.SECONDS;
                try {
                    time.sleep(1);
                    timer++;
                    System.out.println("Dog door will close in: " + (maxSeconds + 1 - timer ));
                }
                catch (InterruptedException e){
                    System.err.format("Error in doorTimer()", e);
                }
            }
            System.out.println("The dog door is closed.");
            boolLock = true;
    }

    void TestDogDoor() throws InterruptedException {
        TimeUnit time = TimeUnit.SECONDS;
        System.out.println("Creating bark() objects...");
        time.sleep(4);
        Bark a = new Bark("woof");
        Bark b = new Bark("WOOOOOF");
        Bark c = new Bark("meow");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("\n\nCreating BarkRecognizer() object...");
        time.sleep(4);
        BarkRecognizer br = new BarkRecognizer(a);
        System.out.println(br);
        System.out.println("\n\nTesting BarkRecognizerListen()...");
        time.sleep(4);
        br.BarkRecognizerListen(b);
        br.BarkRecognizerListen(c);
        br.BarkRecognizerListen(a);
    }

}
