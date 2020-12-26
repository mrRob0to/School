package DogDoorPkg;

public interface Remote {


    //Default features
    void ToggleLock() throws InterruptedException;
    void inputPIN(int input) throws InterruptedException;

    //Optional Features
    void ToggleWindows();
    void ToggleScratch();
}
