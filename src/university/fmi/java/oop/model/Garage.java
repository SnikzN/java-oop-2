package university.fmi.java.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Garage {
    private String name;
    private final List<Car> carList = new ArrayList<>();

    public void addCar(Car car) {
        carList.add(car);
    }
    public void updateCar(int selectedIndex, Car car) {
        carList.set(selectedIndex, car);
    }

    public void removeCar(int index) {
        carList.remove(index);
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

}
