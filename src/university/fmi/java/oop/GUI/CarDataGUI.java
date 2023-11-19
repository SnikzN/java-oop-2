package university.fmi.java.oop.GUI;

import university.fmi.java.oop.model.Car;
import university.fmi.java.oop.model.Garage;
import university.fmi.java.oop.model.Validator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarDataGUI {
    CarDataModel carDataModel = new CarDataModel();
    Garage garage = new Garage();
    JFrame frame;
    JPanel titlePanel;
    JPanel menuPanel;
    JPanel mainPanel;
    JTable carTable;
    JComboBox<String> dropdownMenu;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarDataGUI().createAndShowGUI());

    }

    public void createAndShowGUI() {
        frame = new JFrame("Гаража на бай Иван");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        titlePanel = new JPanel();
        JLabel nameLabelTitlePanel = new JLabel("Гаража на бай Иван", JLabel.CENTER);
        Font labelFont = nameLabelTitlePanel.getFont();
        nameLabelTitlePanel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        titlePanel.setBackground(Color.white);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.add(nameLabelTitlePanel);

        menuPanel = new JPanel();
        menuPanel.setBackground(Color.white);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        menuPanel.setPreferredSize(new Dimension(200, 0));

        dropdownMenu = new JComboBox<>();
        dropdownMenu.setPreferredSize(new Dimension(200, 50));
        dropdownMenu.setBackground(Color.white);

        for (Car car : garage.getCarList()) {
            dropdownMenu.addItem(car.getBrand() + " " + car.getModel());
        }

        menuPanel.add(dropdownMenu);


        JButton buttonAddCar = new JButton("Добави");
        buttonAddCar.setPreferredSize(new Dimension(130,50));
        buttonAddCar.setBackground(Color.white);

        JButton buttonRemoveCar = new JButton("Премахни");
        buttonRemoveCar.setPreferredSize(new Dimension(130,50));
        buttonRemoveCar.setBackground(Color.white);

        JButton buttonUpdateCarInfo = new JButton("Обнови");
        buttonUpdateCarInfo.setPreferredSize(new Dimension(130,50));
        buttonUpdateCarInfo.setBackground(Color.white);

        JButton buttonShowAvailableCars = new JButton("Покажи всички");
        buttonShowAvailableCars.setPreferredSize(new Dimension(130,50));
        buttonShowAvailableCars.setBackground(Color.white);

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setPreferredSize(new Dimension(200, 700));
        buttonsPanel.setBackground(Color.blue);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;


        buttonsPanel.add(buttonAddCar, gbc);
        buttonAddCar.addActionListener(e -> replaceMainPanel(openAddPanel()));
        gbc.gridy = 1;
        buttonsPanel.add(buttonRemoveCar, gbc);
        // TODO: buttonRemoveCar.addActionListener(e -> garage.removeCar());

        buttonShowAvailableCars.addActionListener(e -> garage.printListOfCars());

        gbc.gridy = 2;
        buttonsPanel.add(buttonUpdateCarInfo, gbc);

        gbc.gridy = 3;
        buttonsPanel.add(buttonShowAvailableCars, gbc);

        buttonAddCar.addActionListener(e -> replaceMainPanel(openAddPanel()));
//        buttonAddCar.addActionListener(e -> replaceMenuPanel(menuWithAllButtons()));

        buttonShowAvailableCars.addActionListener(e -> displayAllCars());

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

    public JPanel openAddPanel() {

        JPanel addPanelHeader = new JPanel();
        addPanelHeader.setBackground(Color.blue);
        addPanelHeader.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel addPanelLabel = new JLabel("ИМЕ И МАРКА НА КОЛА", JLabel.CENTER);
        Font labelFont = addPanelLabel.getFont();
        addPanelLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 30));

        addPanelHeader.add(addPanelLabel);

        JPanel addPanelInputFields = new JPanel();
        addPanelInputFields.setBackground(Color.green);

        JLabel licensePlateLabel = new JLabel("Регистрационен номер:", JLabel.CENTER);
        JTextField licensePlateInput = new JTextField();

        JLabel carTypeLabel = new JLabel("Вид кола:", JLabel.CENTER);
        JTextField carTypeInput = new JTextField();

        JLabel carBrandLabel = new JLabel("Марка:", JLabel.CENTER);
        JTextField carBrandInput = new JTextField();

        JLabel carModelLabel = new JLabel("Модел:", JLabel.CENTER);
        JTextField carModelInput = new JTextField();

        JLabel fuelConsumptionLabel = new JLabel("Разход на гориво:", JLabel.CENTER);
        JTextField fuelConsumptionInput = new JTextField();

        JLabel tankVolumeLabel = new JLabel("Обем на резервоара:", JLabel.CENTER);
        JTextField tankVolumeInput = new JTextField();

        JLabel fuelTypeLabel = new JLabel("Тип гориво:", JLabel.CENTER);
        JComboBox<String> fuelTypeInput = new JComboBox<>();
        fuelTypeInput.setBackground(Color.white);
        fuelTypeInput.addItem("Дизел");
        fuelTypeInput.addItem("Бензин/Газ");

        String fuelType = (String) fuelTypeInput.getSelectedItem();

        JLabel powerLabel = new JLabel("Мощност:", JLabel.CENTER);
        JTextField powerInput = new JTextField();

        JLabel gearBoxTypeLabel = new JLabel("Вид скоростна кутия:", JLabel.CENTER);
        JComboBox<String> gearBoxTypeInput = new JComboBox<>();
        gearBoxTypeInput.setBackground(Color.white);
        gearBoxTypeInput.addItem("Ръчна");
        gearBoxTypeInput.addItem("Автоматична");

        String gearBoxType = (String) gearBoxTypeInput.getSelectedItem();

        JLabel manufactureYearLabel = new JLabel("Година на създаване:", JLabel.CENTER);
        JTextField manufactureYearInput = new JTextField();

        JLabel registrationDateLabel = new JLabel("Дата на регистрация:", JLabel.CENTER);
        JTextField registrationDateInput = new JTextField();

        JLabel insuranceDateLabel = new JLabel("Дата на застраховка:", JLabel.CENTER);
        JTextField insuranceDateInput = new JTextField();

        JLabel inspectionDateLabel = new JLabel("Дата на преглед:", JLabel.CENTER);
        JTextField inspectionDateInput = new JTextField();

        JLabel nextDateForTireChangeLabel = new JLabel("Дата за смяна на гуми:", JLabel.CENTER);
        JTextField nextDateForTireChangeInput = new JTextField();

        JLabel kmUntilOilChangeLabel = new JLabel("КМ до смяна на масло:", JLabel.CENTER);
        JTextField kmUntilOilChangeInput = new JTextField();

        JButton addButton = new JButton("Добави");
        addButton.setPreferredSize(new Dimension(100,50));
        addButton.addActionListener(e -> {
            Car car = Validator.createAndValidateCar(carTypeInput.getText(),licensePlateInput.getText(),carBrandInput.getText(),carModelInput.getText(),
                    fuelConsumptionInput.getText(),tankVolumeInput.getText(),fuelType,powerInput.getText(),gearBoxType, manufactureYearInput.getText(),
                    registrationDateInput.getText(),insuranceDateInput.getText(),inspectionDateInput.getText(),nextDateForTireChangeInput.getText(),
                    kmUntilOilChangeInput.getText());
            if (car != null) {
                garage.addCar(car);
                dropdownMenu.addItem(car.getBrand() + " " + car.getModel());
                replaceMainPanel(openAddPanel());
            } else {
                JOptionPane.showMessageDialog(null, "Невалидни данни на кола", "Грешка", JOptionPane.WARNING_MESSAGE);
            }
        });



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

        addPanelInputFields.add(addButton,BorderLayout.SOUTH);

        // TODO: carModelInput.setAction();

        //TODO: TESST LAYOSTS
        addPanelHeader.setLayout(new BorderLayout());

        addPanelHeader.add(addPanelLabel, BorderLayout.NORTH);

        addPanelHeader.add(addPanelInputFields);

        return addPanelHeader;
    }


    public void replaceMainPanel(JPanel newMainPanel) {
        frame.getContentPane().remove(mainPanel);
        mainPanel = newMainPanel;
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void displayAllCars() {
        List<Car> allCars = garage.getCarList();

        JPanel tablePanel = new JPanel(new BorderLayout());

        carTable = new JTable(carDataModel);
        JScrollPane scrollPane = new JScrollPane(carTable);

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        replaceMainPanel(tablePanel);

        carDataModel.setCars(allCars);
    }

}
