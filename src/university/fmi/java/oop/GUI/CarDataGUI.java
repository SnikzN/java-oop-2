package university.fmi.java.oop.GUI;

import university.fmi.java.oop.model.Car;
import university.fmi.java.oop.model.Garage;
import university.fmi.java.oop.model.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class CarDataGUI {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private int selectedIndex;
    String fuelType;
    String gearBoxType;
    private final CarDataModel carDataModel = new CarDataModel();
    private final Garage garage = new Garage();
    private JFrame frame;
    private JPanel mainPanel;
    private JComboBox<String> dropdownMenu;

    private JTextField licensePlateInput;
    private JTextField carTypeInput;
    private JTextField carBrandInput;
    private JTextField carModelInput;
    private JTextField fuelConsumptionInput;
    private JTextField tankVolumeInput;
    private JComboBox<String> fuelTypeInput;
    private JTextField powerInput;
    private JComboBox<String> gearBoxTypeInput;
    private JTextField manufactureYearInput;
    private JTextField registrationDateInput;
    private JTextField insuranceDateInput;
    private JTextField inspectionDateInput;
    private JTextField nextDateForTireChangeInput;
    private JTextField kmUntilOilChangeInput;
    private JButton addButton;
    private JButton updateButton;

    public void createAndShowGUI() {
        frame = new JFrame("Гаража на бай Иван");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        JLabel nameLabelTitlePanel = new JLabel("Гаража на бай Иван", JLabel.CENTER);
        Font labelFont = nameLabelTitlePanel.getFont();
        nameLabelTitlePanel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        titlePanel.setBackground(Color.white);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.add(nameLabelTitlePanel);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.white);
        menuPanel.setPreferredSize(new Dimension(200, 0));

        dropdownMenu = new JComboBox<>();
        dropdownMenu.setPreferredSize(new Dimension(200, 50));
        dropdownMenu.setBackground(Color.white);
        dropdownMenu.addItem("Избери кола");

        dropdownMenu.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                addButton.setVisible(false);
                updateButton.setVisible(false);
                selectedIndex = dropdownMenu.getSelectedIndex();
                if (selectedIndex > 0) {
                    Car selectedCar = garage.getCarList().get(selectedIndex - 1);
                    updateFieldsForSelectedCar(selectedCar);
                } else {
                    updateFieldsForSelectedCar(null);
                }
            }
        });

        menuPanel.add(dropdownMenu);

        JButton buttonAddCar = new JButton("Добави");
        buttonAddCar.setPreferredSize(new Dimension(130, 50));
        buttonAddCar.setBackground(Color.white);

        JButton buttonRemoveCar = new JButton("Премахни");
        buttonRemoveCar.setPreferredSize(new Dimension(130, 50));
        buttonRemoveCar.setBackground(Color.white);

        JButton buttonSearchCar = new JButton("Потърси");
        buttonSearchCar.setPreferredSize(new Dimension(130, 50));
        buttonSearchCar.setBackground(Color.white);

        JButton buttonUpdateCarInfo = new JButton("Обнови");
        buttonUpdateCarInfo.setPreferredSize(new Dimension(130, 50));
        buttonUpdateCarInfo.setBackground(Color.white);

        JButton buttonShowAvailableCars = new JButton("Покажи всички");
        buttonShowAvailableCars.setPreferredSize(new Dimension(130, 50));
        buttonShowAvailableCars.setBackground(Color.white);

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setPreferredSize(new Dimension(200, 700));
        buttonsPanel.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;


        buttonsPanel.add(buttonAddCar, gbc);

        buttonAddCar.addActionListener(e -> {
            replaceMainPanel(openCarPanel());
            selectedIndex = 0;
            dropdownMenu.setSelectedIndex(0);
            updateButton.setVisible(false);
            addButton.setVisible(true);
        });
        gbc.gridy = 1;
        buttonsPanel.add(buttonRemoveCar, gbc);
        buttonRemoveCar.addActionListener(e -> {
            if(selectedIndex > 0) {
                garage.removeCar(selectedIndex - 1);
                dropdownMenu.removeItemAt(selectedIndex);
                replaceMainPanel(openCarPanel());
            } else {
                JOptionPane.showMessageDialog(null, "Няма избрана кола!", "Грешка", JOptionPane.WARNING_MESSAGE);
            }
        });

        gbc.gridy = 2;
        buttonsPanel.add(buttonSearchCar,gbc);
        buttonSearchCar.addActionListener(e -> replaceMainPanel(openCarSearchPanel()));

        gbc.gridy = 3;
        buttonsPanel.add(buttonUpdateCarInfo, gbc);
        buttonUpdateCarInfo.addActionListener(e -> {
            if(selectedIndex > 0) {
                Car selectedCar = garage.getCarList().get(selectedIndex - 1);
                replaceMainPanel(openCarPanel());
                updateFieldsForSelectedCar(selectedCar);
                addButton.setVisible(false);
                updateButton.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Няма избрана кола!", "Грешка", JOptionPane.WARNING_MESSAGE);
            }
        });

        gbc.gridy = 4;
        buttonsPanel.add(buttonShowAvailableCars, gbc);

        buttonShowAvailableCars.addActionListener(e -> displayCarsInATable(garage.getCarList()));

        mainPanel = new JPanel();
        JLabel nameLabelMainPanel = new JLabel("Здравейте, моля изберете кола от гаража!", JLabel.RIGHT);
        nameLabelMainPanel.setFont(new Font(labelFont.getName(), Font.BOLD, 30));
        mainPanel.add(nameLabelMainPanel);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.setBackground(Color.white);

        menuPanel.add(buttonsPanel);

        frame.setLayout(new BorderLayout());
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public JPanel openCarPanel() {

        JPanel carPanel = new JPanel();
        carPanel.setBackground(Color.white);
        carPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel addPanelHeader = new JPanel();
        addPanelHeader.setBackground(Color.white);

        JLabel addPanelLabel = new JLabel("ИМЕ И МАРКА НА КОЛА", JLabel.CENTER);
        Font labelFont = addPanelLabel.getFont();
        addPanelLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 30));

        addPanelHeader.add(addPanelLabel);

        JPanel addPanelInputFields = new JPanel();
        addPanelInputFields.setBackground(Color.white);

        JLabel licensePlateLabel = new JLabel("Регистрационен номер:", JLabel.CENTER);
        licensePlateInput = new JTextField();

        JLabel carTypeLabel = new JLabel("Вид кола:", JLabel.CENTER);
        carTypeInput = new JTextField();

        JLabel carBrandLabel = new JLabel("Марка:", JLabel.CENTER);
        carBrandInput = new JTextField();

        JLabel carModelLabel = new JLabel("Модел:", JLabel.CENTER);
        carModelInput = new JTextField();

        JLabel fuelConsumptionLabel = new JLabel("Разход на гориво:", JLabel.CENTER);
        fuelConsumptionInput = new JTextField();

        JLabel tankVolumeLabel = new JLabel("Обем на резервоара:", JLabel.CENTER);
        tankVolumeInput = new JTextField();

        JLabel fuelTypeLabel = new JLabel("Тип гориво:", JLabel.CENTER);
        fuelTypeInput = new JComboBox<>();
        fuelTypeInput.setBackground(Color.white);
        fuelTypeInput.addItem("Дизел");
        fuelTypeInput.addItem("Бензин/Газ");

        fuelType = (String) fuelTypeInput.getSelectedItem();

        JLabel powerLabel = new JLabel("Мощност:", JLabel.CENTER);
        powerInput = new JTextField();

        JLabel gearBoxTypeLabel = new JLabel("Вид скоростна кутия:", JLabel.CENTER);
        gearBoxTypeInput = new JComboBox<>();
        gearBoxTypeInput.setBackground(Color.white);
        gearBoxTypeInput.addItem("Ръчна");
        gearBoxTypeInput.addItem("Автоматична");

        gearBoxType = (String) gearBoxTypeInput.getSelectedItem();

        JLabel manufactureYearLabel = new JLabel("Година на създаване:", JLabel.CENTER);
        manufactureYearInput = new JTextField();

        JLabel registrationDateLabel = new JLabel("Дата на регистрация:", JLabel.CENTER);
        registrationDateInput = new JTextField();

        JLabel insuranceDateLabel = new JLabel("Дата на застраховка:", JLabel.CENTER);
        insuranceDateInput = new JTextField();

        JLabel inspectionDateLabel = new JLabel("Дата на преглед:", JLabel.CENTER);
        inspectionDateInput = new JTextField();

        JLabel nextDateForTireChangeLabel = new JLabel("Дата за смяна на гуми:", JLabel.CENTER);
        nextDateForTireChangeInput = new JTextField();

        JLabel kmUntilOilChangeLabel = new JLabel("КМ до смяна на масло:", JLabel.CENTER);
        kmUntilOilChangeInput = new JTextField();

        licensePlateInput.setPreferredSize(new Dimension(150, 30));
        carTypeInput.setPreferredSize(new Dimension(150, 30));
        carBrandInput.setPreferredSize(new Dimension(150, 30));
        carModelInput.setPreferredSize(new Dimension(150, 30));
        fuelConsumptionInput.setPreferredSize(new Dimension(150, 30));
        tankVolumeInput.setPreferredSize(new Dimension(150, 30));
        fuelTypeInput.setPreferredSize(new Dimension(150, 30));
        powerInput.setPreferredSize(new Dimension(150, 30));
        gearBoxTypeInput.setPreferredSize(new Dimension(150, 30));
        manufactureYearInput.setPreferredSize(new Dimension(150, 30));
        registrationDateInput.setPreferredSize(new Dimension(150, 30));
        insuranceDateInput.setPreferredSize(new Dimension(150, 30));
        inspectionDateInput.setPreferredSize(new Dimension(150, 30));
        nextDateForTireChangeInput.setPreferredSize(new Dimension(150, 30));
        kmUntilOilChangeInput.setPreferredSize(new Dimension(150, 30));

        addPanelInputFields.add(licensePlateLabel);
        addPanelInputFields.add(licensePlateInput);
        addPanelInputFields.add(carTypeLabel);
        addPanelInputFields.add(carTypeInput);
        addPanelInputFields.add(carBrandLabel);
        addPanelInputFields.add(carBrandInput);
        addPanelInputFields.add(carModelLabel);
        addPanelInputFields.add(carModelInput);
        addPanelInputFields.add(fuelConsumptionLabel);
        addPanelInputFields.add(fuelConsumptionInput);
        addPanelInputFields.add(tankVolumeLabel);
        addPanelInputFields.add(tankVolumeInput);
        addPanelInputFields.add(fuelTypeLabel);
        addPanelInputFields.add(fuelTypeInput);
        addPanelInputFields.add(powerLabel);
        addPanelInputFields.add(powerInput);
        addPanelInputFields.add(gearBoxTypeLabel);
        addPanelInputFields.add(gearBoxTypeInput);
        addPanelInputFields.add(manufactureYearLabel);
        addPanelInputFields.add(manufactureYearInput);
        addPanelInputFields.add(registrationDateLabel);
        addPanelInputFields.add(registrationDateInput);
        addPanelInputFields.add(insuranceDateLabel);
        addPanelInputFields.add(insuranceDateInput);
        addPanelInputFields.add(inspectionDateLabel);
        addPanelInputFields.add(inspectionDateInput);
        addPanelInputFields.add(nextDateForTireChangeLabel);
        addPanelInputFields.add(nextDateForTireChangeInput);
        addPanelInputFields.add(kmUntilOilChangeLabel);
        addPanelInputFields.add(kmUntilOilChangeInput);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 50));
        buttonPanel.setBackground(Color.white);
        addButton = new JButton("Добави кола");
        addButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(addButton);

        addButton.addActionListener(addButtonClick -> {
            Car car = Validator.createAndValidateCar(carTypeInput.getText(), licensePlateInput.getText(), carBrandInput.getText(), carModelInput.getText(),
                    fuelConsumptionInput.getText(), tankVolumeInput.getText(), fuelType, powerInput.getText(), gearBoxType, manufactureYearInput.getText(),
                    registrationDateInput.getText(), insuranceDateInput.getText(), inspectionDateInput.getText(), nextDateForTireChangeInput.getText(),
                    kmUntilOilChangeInput.getText());
            if (car != null) {
                garage.addCar(car);
                dropdownMenu.addItem(car.getBrand() + " " + car.getModel() + " (" + car.getLicensePlate() + ")");
                replaceMainPanel(openCarPanel());
            }
        });

        updateButton = new JButton("Обнови кола");
        updateButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(updateButton);

        updateButton.addActionListener(updateClick -> {
            Car car = Validator.createAndValidateCar(carTypeInput.getText(), licensePlateInput.getText(), carBrandInput.getText(), carModelInput.getText(),
                    fuelConsumptionInput.getText(), tankVolumeInput.getText(), fuelType, powerInput.getText(), gearBoxType, manufactureYearInput.getText(),
                    registrationDateInput.getText(), insuranceDateInput.getText(), inspectionDateInput.getText(), nextDateForTireChangeInput.getText(),
                    kmUntilOilChangeInput.getText());
            if (car != null) {
                garage.updateCar(selectedIndex - 1, car);
                dropdownMenu.insertItemAt(car.getBrand() + " " + car.getModel() + " (" + car.getLicensePlate() + ")", selectedIndex);
                dropdownMenu.removeItemAt(selectedIndex + 1);

                updateFieldsForSelectedCar(garage.getCarList().get(selectedIndex - 1));
                updateButton.setVisible(true);
            }
        });
        updateButton.setVisible(false);

        addPanelInputFields.setLayout(new GridLayout(8, 2, 10, 10));
        JPanel emptyPanel = new JPanel();
        emptyPanel.setVisible(false);
        addPanelInputFields.add(emptyPanel);

        addPanelHeader.add(addPanelLabel);

        carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS));
        carPanel.add(addPanelHeader, BorderLayout.NORTH);
        carPanel.add(addPanelInputFields, BorderLayout.SOUTH);
        carPanel.add(buttonPanel, BorderLayout.SOUTH);

        return carPanel;
    }

    private void replaceMainPanel(JPanel newMainPanel) {
        frame.getContentPane().remove(mainPanel);
        mainPanel = newMainPanel;
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void displayCarsInATable(List<Car> listOfCars) {
        JPanel tablePanel = new JPanel(new BorderLayout());

        JTable carTable = new JTable(carDataModel);
        JScrollPane scrollPane = new JScrollPane(carTable);

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        replaceMainPanel(tablePanel);

        carDataModel.setCars(listOfCars);
    }

    private void updateFieldsForSelectedCar(Car selectedCar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (selectedCar != null) {
            licensePlateInput.setText(selectedCar.getLicensePlate());
            carTypeInput.setText(selectedCar.getType());
            carBrandInput.setText(selectedCar.getBrand());
            carModelInput.setText(selectedCar.getModel());
            fuelConsumptionInput.setText(String.valueOf(selectedCar.getFuelConsumption()));
            tankVolumeInput.setText(String.valueOf(selectedCar.getTankVolume()));
            fuelTypeInput.setSelectedItem(selectedCar.getFuelType());
            powerInput.setText(String.valueOf(selectedCar.getPower()));
            gearBoxTypeInput.setSelectedItem(selectedCar.getGearboxType());
            manufactureYearInput.setText(String.valueOf(selectedCar.getManufactureYear()));
            registrationDateInput.setText(dateFormat.format(selectedCar.getRegistrationDate()));
            insuranceDateInput.setText(dateFormat.format(selectedCar.getInsuranceDate()));
            inspectionDateInput.setText(dateFormat.format(selectedCar.getInspectionDate()));
            nextDateForTireChangeInput.setText(dateFormat.format(selectedCar.getNextDateForTireChange()));
            kmUntilOilChangeInput.setText(String.valueOf(selectedCar.getKilometersUntilOilChange()));
        } else {
            licensePlateInput.setText("");
            carTypeInput.setText("");
            carBrandInput.setText("");
            carModelInput.setText("");
            fuelConsumptionInput.setText("");
            tankVolumeInput.setText("");
            fuelTypeInput.setSelectedItem(fuelTypeInput.getItemAt(0));
            powerInput.setText("");
            gearBoxTypeInput.setSelectedItem(gearBoxTypeInput.getItemAt(0));
            manufactureYearInput.setText("");
            registrationDateInput.setText("");
            insuranceDateInput.setText("");
            inspectionDateInput.setText("");
            nextDateForTireChangeInput.setText("");
            kmUntilOilChangeInput.setText("");
        }
    }
    private JPanel openCarSearchPanel() {
        JPanel carSearchPanel = new JPanel();
        carSearchPanel.setBackground(Color.white);
        carSearchPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        carSearchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel searchPanelLabel = new JLabel("Потърсете кола");
        Font labelFont = searchPanelLabel.getFont();
        searchPanelLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        carSearchPanel.add(searchPanelLabel, gbc);

        gbc.gridwidth = 1;
        JLabel searchByLicensePlate = new JLabel("Търсене по регистрационен номер:", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        carSearchPanel.add(searchByLicensePlate, gbc);

        JTextField licensePlateSearchTextField = new JTextField();
        licensePlateSearchTextField.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        carSearchPanel.add(licensePlateSearchTextField, gbc);

        JLabel searchByBrand = new JLabel("Търсене по марка:", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        carSearchPanel.add(searchByBrand, gbc);

        JTextField brandSearchTextField = new JTextField();
        brandSearchTextField.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        carSearchPanel.add(brandSearchTextField, gbc);

        JLabel searchByModel = new JLabel("Търсене по модел:", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        carSearchPanel.add(searchByModel, gbc);

        JTextField modelSearchTextField = new JTextField();
        modelSearchTextField.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        carSearchPanel.add(modelSearchTextField, gbc);

        JLabel searchByInsuranceDate = new JLabel("Търсене по дата на застраховане:", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        carSearchPanel.add(searchByInsuranceDate, gbc);

        JTextField insuranceDateSearchTextField = new JTextField();
        insuranceDateSearchTextField.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        carSearchPanel.add(insuranceDateSearchTextField, gbc);

        JLabel searchByRegistrationDate = new JLabel("Търсене по дата на регистрация:", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 5;
        carSearchPanel.add(searchByRegistrationDate, gbc);

        JTextField registrationDateSearchTextField = new JTextField();
        registrationDateSearchTextField.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        carSearchPanel.add(registrationDateSearchTextField, gbc);

        JButton searchButton = new JButton("Търси");
        searchButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        carSearchPanel.add(searchButton, gbc);

        searchButton.addActionListener(e -> {
            String licensePlate = licensePlateSearchTextField.getText();
            String brand = brandSearchTextField.getText();
            String model = modelSearchTextField.getText();
            String insuranceDate = insuranceDateSearchTextField.getText();
            String registrationDate = registrationDateSearchTextField.getText();

            List<Car> matchingCars = searchCars(licensePlate, brand, model, insuranceDate, registrationDate);

            if (!matchingCars.isEmpty()) {
                displayCarsInATable(matchingCars);
            } else {
                JOptionPane.showMessageDialog(null, "Няма намерени коли.", "Резултат от търсене", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        return carSearchPanel;
    }

    private List<Car> searchCars(String licensePlate, String brand, String model, String insuranceDate, String registrationDate) {

        return garage.getCarList().stream()
                .filter(car ->
                        (licensePlate.isEmpty() || car.getLicensePlate().contains(licensePlate)) &&
                                (brand.isEmpty() || car.getBrand().contains(brand)) &&
                                (model.isEmpty() || car.getModel().contains(model)) &&
                                (insuranceDate.isEmpty() || dateFormat.format(car.getInsuranceDate()).contains(insuranceDate) &&
                                        (registrationDate.isEmpty() || dateFormat.format(car.getRegistrationDate()).contains(registrationDate)))
                ).toList();
    }
}