package university.fmi.java.oop.model;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {

    private static final Date today = new Date();

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public static Car createAndValidateCar(String type, String licensePlate, String brand, String model, String fuelConsumption,
                                           String tankVolume, String fuelType, String power, String gearboxType, String manufactureYear, String registrationDate,
                                           String insuranceDate, String inspectionDate, String nextDateForTireChange, String kmUntilOilChange) {
        float fuelConsumptionValue;
        int tankVolumeValue;
        int powerValue;
        int manufactureYearValue;
        int kmUntilOilChangeValue;
        Date registrationDateValue;
        Date insuranceDateValue;
        Date inspectionDateValue;
        Date nextDateForTireChangeValue;


        try {
            fuelConsumptionValue = Float.parseFloat(fuelConsumption);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за разход на гориво", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            tankVolumeValue = Integer.parseInt(tankVolume);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за размер на резервоар", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            powerValue = Integer.parseInt(power);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за мощност на колата", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            manufactureYearValue = Integer.parseInt(manufactureYear);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за година на производство", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            kmUntilOilChangeValue = Integer.parseInt(kmUntilOilChange);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за километри за следваща смяна на масло", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            registrationDateValue = formatter.parse(registrationDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за дата на регистрация", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            insuranceDateValue = formatter.parse(insuranceDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за дата на застраховка", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            inspectionDateValue = formatter.parse(inspectionDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за дата на преглед", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            nextDateForTireChangeValue = formatter.parse(nextDateForTireChange);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за дата за следваща смяна на гуми", "Грешка", JOptionPane.WARNING_MESSAGE);

            return null;
        }

        if (type.isEmpty() || type.length() > 100) {
            JOptionPane.showMessageDialog(null, "Невалидни данни, типът е празен или надвишава 100 символа", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (licensePlate.isEmpty() || licensePlate.length() > 10) {
            JOptionPane.showMessageDialog(null, "Невалидни данни, регистрационният номер е празен или надвшава 10 символа", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (brand.isEmpty() || brand.length() > 20) {
            JOptionPane.showMessageDialog(null, "Невалидни данни, марката е празен или надвишава 20 символа", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (model.isEmpty() || model.length() > 20) {
            JOptionPane.showMessageDialog(null, "Невалидни данни, моделът е празен или надвишава 20 символа", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (fuelConsumptionValue <= 0) {
            JOptionPane.showMessageDialog(null, "Невалидни данни, консумацията на гориво не може да бъде отрицателно число", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (tankVolumeValue <= 0) {
            JOptionPane.showMessageDialog(null, "Невалидни данни, размера на резервоара не може да бъде отрицателно число", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (powerValue < 0) {
            JOptionPane.showMessageDialog(null, "Невалидни данни, мощността не може да бъде отрицателно число", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (manufactureYearValue <= 0 || manufactureYearValue > 2023) {
            JOptionPane.showMessageDialog(null, "Невалидни година на производство", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (isValidDate(registrationDateValue)) {
            JOptionPane.showMessageDialog(null, "Невалидни дата на регистрация", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (isValidDate(inspectionDateValue)) {
            JOptionPane.showMessageDialog(null, "Невалидни дата на преглед", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (isValidDate(insuranceDateValue)) {
            JOptionPane.showMessageDialog(null, "Невалидни дата на застраховка", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (!isValidFutureDate(nextDateForTireChangeValue)) {
            JOptionPane.showMessageDialog(null, "Невалидни дата за смяна на гуми", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (kmUntilOilChangeValue <= 0) {
            JOptionPane.showMessageDialog(null, "Невалидни данни за км до смяна на масло", "Грешка", JOptionPane.WARNING_MESSAGE);
            return null;
        }


        return new Car(type, licensePlate, brand, model, fuelConsumptionValue, tankVolumeValue, fuelType, powerValue, gearboxType, manufactureYearValue,
                registrationDateValue, insuranceDateValue, inspectionDateValue, nextDateForTireChangeValue, kmUntilOilChangeValue);
    }

    public static boolean isValidDate(Date date) {
        return date == null || !date.before(today);
    }

    public static boolean isValidFutureDate(Date date) {
        return date != null && date.after(today);
    }
}
