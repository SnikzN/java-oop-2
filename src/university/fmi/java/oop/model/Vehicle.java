package university.fmi.java.oop.model;

public abstract class Vehicle {
    private String type;
    private String licensePlate;
    private String brand;
    private String model;

    public Vehicle(String type, String licensePlate, String brand, String model) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
