package university.fmi.java.oop.model;

import java.util.Date;

public class Car extends Vehicle {
    private final float fuelConsumption;
    private final int tankVolume;
    private final String fuelType;
    private final int power;
    private final String gearboxType;
    private final int manufactureYear;
    private final Date registrationDate;
    private final Date insuranceDate;
    private final Date inspectionDate;
    private final Date nextDateForTireChange;
    private final int kmUntilOilChange;

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

    public int getTankVolume() {
        return tankVolume;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getPower() {
        return power;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public Date getNextDateForTireChange() {
        return nextDateForTireChange;
    }

    public int getKilometersUntilOilChange() {
        return kmUntilOilChange;
    }

}
