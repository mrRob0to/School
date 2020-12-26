package ParkingGaragePkg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


public class Garage {

    static private final int GARAGESIZE = 20;
    static private int occupancy = 0;
    static protected ArrayList<Spot> parkingSpots = new ArrayList<Spot>(Collections.nCopies(GARAGESIZE, new Spot()));


     static boolean checkFull() throws InterruptedException {
        System.out.println("Checking for open spots...");
        TimeUnit time = TimeUnit.SECONDS;
        time.sleep(2);
        if (occupancy == GARAGESIZE) {
            System.out.println("Sorry Garage is full");
            return true;
        }
        else {
            System.out.println("Space available");
            return false;
        }

    }

    static boolean checkType(Vehicle obj){
         if(obj.getType() == "Car" || obj.getType() == "Suv" || obj.getType() == "Motorcycle" ) {
            return true;
        }
         else return false;
    }

    static boolean checkType(GenericVehicle obj){
        if(obj.getType() == "Car" || obj.getType() == "Suv" || obj.getType() == "Motorcycle" ) {
            return true;
        }
        else return false;
    }

     static void listSpots(){
        System.out.println("\n\n");
        int n = 0;
        for(Spot x: parkingSpots){
            System.out.println("Spot#"+ n + "\tPlate: " + x);
            n++;
        }

    }

    static void assignSpot(Spot s){
        for(Spot x: parkingSpots){
            if(x.isTaken == false){
                System.out.println("Proceed to spot: "+ parkingSpots.indexOf(x));
                parkingSpots.set(parkingSpots.indexOf(x), s);
                occupancy++;
                break;
            }
        }
    }

    static void vehicleLeave(Vehicle obj) {
        for(Spot x: parkingSpots){
            if(x.spotLicensePlate == obj.vehicleLicensePlate){
                System.out.println("\nVehicle is leaving spot: " + parkingSpots.indexOf(x));
                x.setIsTaken(false);
                x.setSpotLicensePlate("xxx");
                occupancy--;
            }
        }
    }

}
