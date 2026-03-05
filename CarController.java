import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController {

    private final int delay = 50;
    private final Timer timer = new Timer(delay, new TimerListener());

    private final Workshop<Volvo240> volvoWorkshop = new Workshop<>(5);
    private final CarFactory factory = new CarFactory();

    Simulation sim;
    CarView frame; // view

    public static void main(String[] args) {
        CarController cc = new CarController();

        // Bygg model services
        CollisionService collision = new CollisionService();
        WorkshopZone wz = new WorkshopZone(WorldConfig.VOLVO_WORKSHOP_BOUNDS, cc.volvoWorkshop);
        cc.sim = new Simulation(collision, wz);

        // Lägg bilar (startpos)
        Volvo240 v = new Volvo240(); v.setPosition(0, 0);
        Saab95 s = new Saab95(); s.setPosition(0, 100);
        Scania sc = new Scania(); sc.setPosition(0, 200);

        cc.sim.addCar(v);
        cc.sim.addCar(s);
        cc.sim.addCar(sc);

        // Starta view sist
        cc.frame = new CarView("CarSim 1.0", cc);
        cc.frame.drawPanel.setCars(cc.sim.getCars());

        cc.timer.start();
    }

    private class TimerListener implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            int w = frame.drawPanel.getWidth();
            int h = frame.drawPanel.getHeight();

            sim.tick(w, h);

            frame.drawPanel.setCars(sim.getCars());
            frame.drawPanel.repaint();
        }
    }

    // ----- GUI-kommandon -----

    void gas(int amount) {
        double a = amount / 100.0;
        for (Car c : sim.getCars()) c.gas(a);
    }

    void brake(int amount) {
        double a = amount / 100.0;
        for (Car c : sim.getCars()) c.brake(a);
    }

    void startAllCars() {
        for (Car c : sim.getCars()) c.startEngine();
    }

    void stopAllCars() {
        for (Car c : sim.getCars()) c.stopEngine();
    }

    void setTurboOn() {
        for (Car c : sim.getCars()) if (c instanceof Saab95 s) s.setTurboOn();
    }

    void setTurboOff() {
        for (Car c : sim.getCars()) if (c instanceof Saab95 s) s.setTurboOff();
    }

    void raiseFlak(double angle) {
        for (Car c : sim.getCars()) if (c instanceof Scania sc) sc.raiseFlak(angle);
    }

    void lowerFlak(double angle) {
        for (Car c : sim.getCars()) if (c instanceof Scania sc) sc.lowerFlak(angle);
    }

    // ----- Uppgift 5 -----
    void addCar() {
        if (sim.getCars().size() >= WorldConfig.MAX_CARS) return;

        Car c = factory.createRandomCar();
        int idx = sim.getCars().size();
        c.setPosition(0, idx * 100);

        sim.addCar(c);
    }

    void removeCar() {
        if (sim.getCars().isEmpty()) return;
        sim.removeCar();
    }
}