public class SUV extends Car {
    public SUV(String model, String colour, double price, double fuelLevel,String mileage, String engineDetails) {
        super(model, colour, price, fuelLevel, mileage, engineDetails);
    }

    @Override
    public void displayInfo() {
        System.out.println("SUV Model: " + getModel() + " , Color: " + getColour() + ", Price: $" + getPrice());
    }
}
