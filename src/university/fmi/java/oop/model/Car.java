package university.fmi.java.oop.model;

import java.util.Date;

public class Car extends Vehicle {
    private float fuelConsumption;
    private int tankVolume;
    private String fuelType;
    private int power;
    private String gearboxType;
    private int manufactureYear;
    private Date registrationDate;
    private Date insuranceDate;
    private Date inspectionDate;
    private Date nextDateForTireChange;
    private int kmUntilOilChange;

    public Car(String type, String licensePlate, String brand, String model) {
        super(type, licensePlate, brand, model);
    }

    public Car(String type, String licensePlate, String brand, String model, float fuelConsumption,
               int tankVolume, String fuelType, int power, String gearboxType, int manufactureYear,
               Date registrationDate, Date insuranceDate, Date inspectionDate, Date nextDateForTireChange, int kmUntilOilChange) {
        super(type, licensePlate, brand, model);
        this.fuelConsumption = fuelConsumption;
        this.tankVolume = tankVolume;
        this.fuelType = fuelType;
        this.power = power;
        this.gearboxType = gearboxType;
        this.manufactureYear = manufactureYear;
        this.registrationDate = registrationDate;
        this.insuranceDate = insuranceDate;
        this.inspectionDate = inspectionDate;
        this.nextDateForTireChange = nextDateForTireChange;
        this.kmUntilOilChange = kmUntilOilChange;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(int tankVolume) {
        this.tankVolume = tankVolume;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Date getNextDateForTireChange() {
        return nextDateForTireChange;
    }

    public void setNextDateForTireChange(Date nextDateForTireChange) {
        this.nextDateForTireChange = nextDateForTireChange;
    }

    public int getKilometersUntilOilChange() {
        return kmUntilOilChange;
    }

    public void setKilometersUntilOilChange(int kilometersUntilOilChange) {
        this.kmUntilOilChange = kilometersUntilOilChange;
    }
}
