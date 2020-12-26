package ParkingGaragePkg;

import java.util.ArrayList;

public class Spot implements Comparable<Spot>{

    protected boolean isTaken;
    protected String spotLicensePlate;

    //Constructors
    Spot(){
        isTaken = false;
        spotLicensePlate = "~~~";
    }

    Spot(Vehicle obj){
        spotLicensePlate = obj.vehicleLicensePlate;
        isTaken = true;
    }

    Spot(GenericVehicle obj){
        spotLicensePlate = (String) obj.licensePlate;
        isTaken = true;
    }

    //Setters & Getters
    void setIsTaken(boolean n){isTaken = n;}
    void setSpotLicensePlate(String n){spotLicensePlate = n;}
    boolean getIsTaken(){return isTaken;}
    String getSpotLicensePlate(){return spotLicensePlate;}


    @Override
    public String toString(){
        String str = spotLicensePlate + "\tisTaken = " + isTaken;
        return str;
    }

    @Override
    public int compareTo(Spot o) {
        return this.getSpotLicensePlate().compareTo(o.getSpotLicensePlate());
    }
}
