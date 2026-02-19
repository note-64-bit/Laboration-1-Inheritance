
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CarController {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        cc.cars.get(0).setX(0); cc.cars.get(0).setY(0);
        cc.cars.get(1).setX(0); cc.cars.get(1).setY(100);
        cc.cars.get(2).setX(0); cc.cars.get(2).setY(200);


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        for(int i = 0; i < cc.cars.size(); i++){
            cc.frame.drawPanel.moveit(i,
                    (int) cc.cars.get(i).getX(),
                    (int) cc.cars.get(i).getY());
        }

        // Start the timer
        cc.timer.start();
    }

    private void invertDirection(Car car) {
        switch (car.getDirection()) {
            case NORTH -> car.setDirection(Car.Direction.SOUTH);
            case SOUTH -> car.setDirection(Car.Direction.NORTH);
            case EAST  -> car.setDirection(Car.Direction.WEST);
            case WEST  -> car.setDirection(Car.Direction.EAST);
        }
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);

                car.move();

                // ---- Wall collision ----
                int panelWidth = frame.drawPanel.getWidth();
                int panelHeight = frame.drawPanel.getHeight();
                int carWidth = 100;
                int carHeight = 60;

                // X-axis
                if (car.getX() < 0) {
                    car.stopEngine();
                    car.setX(0); // snap inside
                    invertDirection(car);
                    car.startEngine();
                } else if (car.getX() > panelWidth - carWidth) {
                    car.stopEngine();
                    car.setX(panelWidth - carWidth); // snap inside
                    invertDirection(car);
                    car.startEngine();
                }

                // Y-axis
                if (car.getY() < 0) {
                    car.stopEngine();
                    car.setY(0); // snap inside
                    invertDirection(car);
                    car.startEngine();
                } else if (car.getY() > panelHeight - carHeight) {
                    car.stopEngine();
                    car.setY(panelHeight - carHeight); // snap inside
                    invertDirection(car);
                    car.startEngine();
                }

                // ---- Workshop collision for Volvos ----
                if (car instanceof Volvo240) {
                    if (isAtWorkshop(car)) {
                        car.stopEngine();
                        // Snap to exact workshop coordinates
                        car.setPosition(frame.drawPanel.volvoWorkshopPoint.x, frame.drawPanel.volvoWorkshopPoint.y);
                        // Load into workshop if not already loaded
                        volvoWorkshop.loadCar((Volvo240) car);
                        // Prevent further movement by skipping move() calls until unloaded
                        continue;
                    }
                }


                // ---- Update DrawPanel points for all cars ----
                frame.drawPanel.moveit(i, (int) car.getX(), (int) car.getY());
            }

            // Repaint panel
            frame.drawPanel.repaint();
        }
    }


    private boolean isAtWorkshop(Car car) {

        int workshopX = 300;
        int workshopY = 200;

        return Math.abs(car.getX() - workshopX) < 50 &&
                Math.abs(car.getY() - workshopY) < 50;
    }


    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.gas(gas);
        }
    }

    void setTurboOn(){
        for (Car car: cars) {
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void setTurboOff(){
        for (Car car: cars){
            if (car instanceof Saab95){
                Saab95 saab = (Saab95) car;
                saab.setTurboOff();
            }
        }
    }

    void lowerFlak(double angle){
        for (Car car: cars){
            if (car instanceof Scania){
                Scania scania = (Scania) car;
                scania.lowerFlak(angle);
            }
        }
    }

    void raiseFlak (double angle) {
        for (Car car : cars) {
            if (car instanceof Scania) {
                Scania scania = (Scania) car;
                scania.raiseFlak(angle);
            }
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }

    void startAllCars(){
        for(Car car: cars) {
            car.startEngine();
        }
    }

    void stopAllCars(){
        for(Car car: cars) {
            car.stopEngine();
        }
    }

    private Workshop<Volvo240> volvoWorkshop = new Workshop<>(5);


}


