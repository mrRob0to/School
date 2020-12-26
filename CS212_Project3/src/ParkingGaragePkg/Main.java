//Robert Antenorcruz
//CS212 Prof. Fried
package ParkingGaragePkg;

public class Main extends Garage {

    public static void main(String[] args) throws InterruptedException {

        AutoScanner ps = new AutoScanner();
        Car a = new Car("Acura", "Blue", "mx5-1234");
        Car b = new Car("Honda", "Red", "dc3-2156");
        Car c = new Car("Ford", "Red", "sav-3333");
        Car d = new Car("Honda", "Silver", "abe-2312");
        Suv e = new Suv("Mazda", "Red", "kyd-1263");
        Suv f = new Suv("Acura", "Silver", "as1-5124");
        Suv g = new Suv("Ford", "Black", "ace-8953");
        Motorcycle h = new Motorcycle("Harley", "Black", "vsp32");

        GenericVehicle gv = new GenericVehicle("Car", "Tesla", "Black", "saf-1364");
        GenericVehicle boat = new GenericVehicle("Boat", "Waves", "White", "boatface");


        ps.autoVehicleScanner(gv);
        ps.autoVehicleScanner(a);
        ps.autoVehicleScanner(g);
        ps.autoVehicleScanner(boat);
        ps.autoVehicleScanner(b);
        Garage.vehicleLeave(a);
        ps.autoVehicleScanner(e);
        ps.autoVehicleScanner(c);
        Garage.vehicleLeave(b);
        ps.autoVehicleScanner(h);
        ps.autoVehicleScanner(d);
        ps.autoVehicleScanner(f);


        Garage.listSpots();

        //Uncomment to sort by license plate lexicographical order
/*      Collections.sort(parkingSpots);
        Garage.listSpots();
*/


    }


}
