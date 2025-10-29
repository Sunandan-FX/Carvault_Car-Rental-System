public class Sedan extends Car {
    public Sedan(String model, String colour, double price, double fuelLevel,String mileage, String engineDetails) {
        super(model, colour, price, fuelLevel, mileage, engineDetails);
    }

    @Override
    public void displayInfo() {
        System.out.println("Sedan Model: " + getModel() + " , Color: " + getColour() + ", Price: $" + getPrice() + ", Fuel Level: " + getFuelLevel() + "L");
    }
}
