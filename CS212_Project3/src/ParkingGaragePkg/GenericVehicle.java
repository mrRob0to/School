package ParkingGaragePkg;

public class GenericVehicle<T> {

    protected T type;
    protected T make;
    protected T color;
    protected T licensePlate;


    void setType(T n){type = n;}
    void setMake(T n){make = n;}

    T getType(){return type;}
    T getMake(){return make;}
    T getColor(){return color;}
    T getLicensePlate(){return licensePlate;}


    GenericVehicle(T type, T make, T color, T licensePlate){
        this.type = type;
        this.make = make;
        this.color= color;
        this.licensePlate = licensePlate;
    }


}
