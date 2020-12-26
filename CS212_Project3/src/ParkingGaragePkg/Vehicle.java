package ParkingGaragePkg;

public abstract class Vehicle{

   protected String make;
   protected String color;
   protected String vehicleLicensePlate;
   protected String type;

   //Setters & Getters
   void setMake(String n){make = n;}
   void setColor(String n){color = n;}
   void setVehicleLicensePlate(String n){vehicleLicensePlate = n;}
   void setType(String n){type = n;}

   String getMake(){return make;}
   String getColor(){return color;}
   String getVehicleLicensePlate(){return vehicleLicensePlate;}
   String getType(){return type;}
}


class Car extends Vehicle {

   Car(){
      type = "Car";
      make = null;
      color = null;
      vehicleLicensePlate = null;
   }
   Car(String make, String color, String vehicleLicensePlate){
      type = "Car";
      this.make = make;
      this.color = color;
      this.vehicleLicensePlate = vehicleLicensePlate;
   }

}


class Suv extends Vehicle {

   Suv(){
      type = "Suv";
      make = null;
      color = null;
      vehicleLicensePlate= null;
   }

   Suv(String make, String color, String vehicleLicensePlate){
      type = "Suv";
      this.make = make;
      this.color = color;
      this.vehicleLicensePlate = vehicleLicensePlate;
   }

}

class Motorcycle extends Vehicle{

   Motorcycle(){
      type = "Motorcycle";
      make = null;
      color = null;
      vehicleLicensePlate = null;
   }

   Motorcycle(String make, String color, String vehicleLicensePlate){
      type = "Motorcycle";
      this.make = make;
      this.color = color;
      this.vehicleLicensePlate = vehicleLicensePlate;
   }

}