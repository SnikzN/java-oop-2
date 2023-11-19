package university.fmi.java.oop.GUI;

import university.fmi.java.oop.model.Car;
import university.fmi.java.oop.model.Vehicle;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CarDataModel extends AbstractTableModel {

    Vehicle vehicle;

    private List<Car> cars;

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public int getRowCount() {
        return cars == null ? 0 : cars.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (cars == null) {
            return null;
        }

        Car car = cars.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> car.getBrand();
            case 1 -> car.getModel();
            case 2 -> car.getPower();
            case 3 -> car.getManufactureYear();
            default -> "N/A";
        };
    }

    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Марка";
            case 1 -> "Модел";
            case 2 -> "Мощност";
            case 3 -> "Година на производство";
            default -> super.getColumnName(columnIndex);
        };
    }

    // TODO: Wtf ?
    public String[] getCarModelAndBrand(String brand, String model) {
        String[] comboBoxContent = new String[cars.size()];
        for (int i = 0; i< cars.size();i++) {
            comboBoxContent[i] = vehicle.getBrand().concat(" ").concat(vehicle.getModel());
        }
        return comboBoxContent;
    }

}
