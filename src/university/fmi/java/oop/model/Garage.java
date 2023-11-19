package university.fmi.java.oop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Garage {

    // TODO: Vehicle and car object here ? to invoke them in CarDataGUI later trough garage.etc
    private String name;
    private List<Car> carList = new ArrayList<>();

    public void addCar(Car car) {
        carList.add(car);
    }

    public void removeCar(int index) {
        carList.remove(index);
    }

    public void removeCar(Car car) {
        carList.remove(car);
    }

    // Use for searching a car
    public List<Car> searchByLicensePlate(String licensePlate) {
        List<Car> searchResults = new ArrayList<>();

        for (Car car : carList) {
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                searchResults.add(car);
            }
        }
        return searchResults;
    }

    public List<Car> searchByBrand(String brand) {
        List<Car> searchResults = new ArrayList<>();

        for (Car car : carList) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                searchResults.add(car);
            }
        }
        return searchResults;
    }

    public List<Car> searchByModel(String model) {
        List<Car> searchResults = new ArrayList<>();

        for (Car car : carList) {
            if (car.getModel().equalsIgnoreCase(model)) {
                searchResults.add(car);
            }
        }
        return searchResults;
    }
    public List<Car> searchByInsuranceDate(Date insuranceDate) {
        List<Car> searchResults = new ArrayList<>();

        for (Car car : carList) {
            if (car.getInsuranceDate().equals(insuranceDate)) {
                searchResults.add(car);
            }
        }
        return searchResults;
    }
    public List<Car> searchByRegistrationDate(Date registrationDate) {
        List<Car> searchResults = new ArrayList<>();

        for (Car car : carList) {
            if (car.getRegistrationDate().equals(registrationDate)) {
                searchResults.add(car);
            }
        }
        return searchResults;
    }

    // Use for updating car info
    public Car getByLicensePlate(String licensePlate) {
        for (Car car : carList) {
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return car;
            }
        }
        return null;
    }

    public void printListOfCars() {
        for (Car car : carList) {
            System.out.println(car);
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
