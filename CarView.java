import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarView extends JFrame {

    private static final int X = 800;
    private static final int Y = 800;

    // Controller
    CarController carC;

    // Draw area
    DrawPanel drawPanel;

    // Panels
    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JPanel brakePanel = new JPanel();
    JSpinner brakeSpinner = new JSpinner();
    int brakeAmount = 0;
    JLabel brakeLabel = new JLabel("Brake");

    JPanel anglePanel = new JPanel();
    JSpinner lowerAngleSpinner = new JSpinner();
    double lowerAngle = 0;
    JLabel lowerAngleLabel = new JLabel("Scania lower bed");

    JPanel raiseAnglePanel = new JPanel();
    JSpinner raiseAngleSpinner = new JSpinner();
    double raiseAngle = 0;
    JLabel raiseAngleLabel = new JLabel("Scania raise bed");

    // Buttons (original)
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Buttons (Uppgift 5)
    JButton addCarButton = new JButton("Add car");
    JButton removeCarButton = new JButton("Remove car");

    // Constructor
    public CarView(String framename, CarController cc) {
        this.carC = cc;
        this.drawPanel = new DrawPanel(X, Y - 240, cc.sim.getCars()); // om du använder Simulation
        // this.drawPanel = new DrawPanel(X, Y - 240, cc.cars);

        initComponents(framename);
    }

    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        this.add(drawPanel);
        drawPanel.setBounds(0, 0, 800, 560);
        this.add(Box.createHorizontalStrut(800));

        // --- Spinners ---
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        SpinnerModel brakeModel = new SpinnerNumberModel(0, 0, 100, 1);
        brakeSpinner = new JSpinner(brakeModel);
        brakeSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                brakeAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        SpinnerModel raiseModel = new SpinnerNumberModel(0, 0, 70, 1);
        raiseAngleSpinner = new JSpinner(raiseModel);
        raiseAngleSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                raiseAngle = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        SpinnerModel lowerModel = new SpinnerNumberModel(0, 0, 70, 1);
        lowerAngleSpinner = new JSpinner(lowerModel);
        lowerAngleSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                lowerAngle = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        // --- Panels for spinners ---
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        raiseAnglePanel.setLayout(new BorderLayout());
        raiseAnglePanel.add(raiseAngleLabel, BorderLayout.PAGE_START);
        raiseAnglePanel.add(raiseAngleSpinner, BorderLayout.PAGE_END);
        this.add(raiseAnglePanel);

        anglePanel.setLayout(new BorderLayout());
        anglePanel.add(lowerAngleLabel, BorderLayout.PAGE_START);
        anglePanel.add(lowerAngleSpinner, BorderLayout.PAGE_END);
        this.add(anglePanel);

        brakePanel.setLayout(new BorderLayout());
        brakePanel.add(brakeLabel, BorderLayout.PAGE_START);
        brakePanel.add(brakeSpinner, BorderLayout.PAGE_END);
        this.add(brakePanel);

        // --- Control panel buttons ---
        controlPanel.setLayout(new GridLayout(2, 4));
        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);

        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        controlPanel.setBackground(Color.CYAN);
        this.add(controlPanel);

        // --- Start/Stop buttons ---
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(stopButton);

        // --- Uppgift 5: Add/Remove buttons ---
        addCarButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(addCarButton);

        removeCarButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(removeCarButton);

        // --- Action listeners ---
        gasButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.brake(brakeAmount);
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.setTurboOn();
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.setTurboOff();
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.raiseFlak(raiseAngle);
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.lowerFlak(lowerAngle);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.startAllCars();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.stopAllCars();
            }
        });

        // Uppgift 5
        addCarButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.addCar();
            }
        });

        removeCarButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                carC.removeCar();
            }
        });

        this.pack();
        this.setSize(800, 900);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}