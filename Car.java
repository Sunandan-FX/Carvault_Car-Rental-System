
public abstract class Car {
    private String model;
    private String colour;
    private double price;
    private double fuelLevel;
    private String mileage;
    private String engineDetails;

    public Car(String model, String colour, double price, double fuelLevel,String mileage, String engineDetails) {
        this.model = model;
        this.colour = colour;
        this.price = price;
        this.fuelLevel = fuelLevel;
         this.mileage = mileage;
        this.engineDetails = engineDetails;
    }
    public String getMileage() {
        return mileage;
    }
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    public String getEngineDetails() {
        return engineDetails;
    }
    public void setEngineDetails(String engineDetails) {
        this.engineDetails = engineDetails;
    }
    public double getFuelLevel() {
        return fuelLevel;
    }
    public String getColour() {
        return colour;
    }
    public String getModel() {
        return model;
    }
    public double getPrice() {
        return price;
    }
    public abstract void displayInfo();
}
