import java.util.ArrayList;
import java.util.List;

public class Showroom {
    private List<Car> cars;
    private String lastPurchasedCarModel;

    public String getLastPurchasedCarModel() {
        return lastPurchasedCarModel;
    }

    public void setLastPurchasedCarModel(String lastPurchasedCarModel) {
        this.lastPurchasedCarModel = lastPurchasedCarModel;
    }

    public Showroom() {
        cars = new ArrayList<>();
        cars.add(new Sedan("Toyota Corolla", "Silver Metallic", 24392, 50.0,"30 MPG","2.5L 4-Cylinder"));
        cars.add(new SUV("Honda Pilot", "Radiant Red Metallic", 39900, 60 ,"20 MPG", "3.5L V6"));
        cars.add(new Hatchback("Cupra Born", "Dark Forest", 20000, 40, "40 MPG", "150 HP Electric Motor"));
        cars.add(new Sedan("Honda Accord", "Crystal Black Pearl", 56000, 55, "30 MPG", "2.0L Turbo I4"));
        cars.add(new SUV("Chevrolet Tahoe", "Midnight Blue", 66695, 65, "18 MPG", "5.3L V8"));
        cars.add(new Hatchback("Maruti Celerio", "Caffeine Brown", 55400, 45,"45 MPG", "1.0L 3-Cylinder"));
    }

    public String getAvailableCars() {
        StringBuilder availableCars = new StringBuilder();
        
        for (Car car : cars) {
            
            availableCars.append(car.getModel()).append(" - ").append(car.getColour()).append(" - $").append(car.getPrice()).append(" - ").append(car.getMileage()).append(" - ").append(car.getEngineDetails()).append("\n");
        }
        return availableCars.toString();
    }

    public Car buyCar(String model) throws CarNotFoundException {
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model)) {
                cars.remove(car);
                return car;
            }
        }
        throw new CarNotFoundException("Car model '" + model + "' not found!");
    }
   

    public List<String> getCarModels() {
        List<String> carModels = new ArrayList<>();
        for (Car car : cars) {
            carModels.add(car.getModel());
        }
        return carModels;
    }

    Car getCarDetails(String selectedModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
}
