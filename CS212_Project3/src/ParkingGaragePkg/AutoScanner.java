package ParkingGaragePkg;

import java.util.concurrent.TimeUnit;

public class AutoScanner{

    TimeUnit time = TimeUnit.SECONDS;

    private int numOfCars;
    private int numOfSuvs;
    private int numOfMotorcycles;
    private int numOfUnknown;

    //Constructors
    AutoScanner(){
        numOfCars = 0;
        numOfSuvs = 0;
        numOfMotorcycles = 0;
        numOfUnknown = 0;
    }

    //Setters & Getters
    void setNumOfCars(int n){numOfCars = n;}
    void setNumOfSuvs(int n){numOfSuvs = n;}
    void setNumOfMotorcycles(int n){numOfMotorcycles = n;}
    void setNumOfUnknown(int n){numOfUnknown = n;}
    int getNumOfCars(){return numOfCars;}
    int getNumOfSuvs(){return numOfSuvs;}
    int getNumOfMotorcycles(){return numOfMotorcycles;}
    int getNumOfUnknown(){return numOfUnknown;}


    void autoVehicleScanner(Vehicle obj) throws InterruptedException {
        System.out.println("\nWelcome to the Garage");
        if(!Garage.checkType(obj)){
            System.out.println("Sorry your vehicle cannot fit");
            return;
        }
        if(Garage.checkFull()){
            time.sleep(2);
            Garage.checkFull();
        }
        //Assigns Vehicle to spot if space is available
        System.out.println("Scanning vehicle...");
        time.sleep(1);
        Spot s = new Spot(obj);
        Garage.assignSpot(s);
        checkVehicleType(obj);

    }

    void autoVehicleScanner(GenericVehicle obj) throws InterruptedException {
        System.out.println("\nWelcome to the Garage");
        if(!Garage.checkType(obj)){
            System.out.println("Sorry your vehicle cannot fit");
            return;
        }
        if(Garage.checkFull()){
            time.sleep(2);
            Garage.checkFull();
        }
        //Assigns Vehicle to spot if space is available
        System.out.println("Scanning vehicle...");
        time.sleep(1);
        Spot s = new Spot(obj);
        Garage.assignSpot(s);
        checkVehicleType(obj);

    }



    void checkVehicleType(Vehicle obj){
        if(obj.type == "Car") numOfCars++;
        else if(obj.type == "Suv") numOfSuvs++;
        else if(obj.type == "Motorcycle")numOfMotorcycles++;
        else numOfUnknown++;

    }

    void checkVehicleType(GenericVehicle obj){
        if(obj.type == "Car") numOfCars++;
        else if(obj.type == "Suv") numOfSuvs++;
        else if(obj.type == "Motorcycle")numOfMotorcycles++;
        else numOfUnknown++;

    }

}
