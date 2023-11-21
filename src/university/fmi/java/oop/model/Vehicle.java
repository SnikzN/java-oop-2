package university.fmi.java.oop.model;

public abstract class Vehicle {
    private final String type;
    private final String licensePlate;
    private final String brand;
    private final String model;

    public Vehicle(String type, String licensePlate, String brand, String model) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

}
