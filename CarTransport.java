import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CarTransport extends Car {
    private boolean rampDown;
    private final int MAX_CARS;
    private final int maxDoorsAllowed;
    private final List<Car> loadedCars;

    public CarTransport(int MAX_CARS, int maxDoorsAllowed) {
        super(2, Color.PINK, 300, "CarTransport");
        this.MAX_CARS = MAX_CARS;
        this.maxDoorsAllowed = maxDoorsAllowed;
        this.rampDown = false;
        this.loadedCars = new ArrayList<>();

    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            rampDown = true;
        }
    }

    public void raiseRamp() {
        if (getCurrentSpeed() == 0) {
            rampDown = false;

        }

    }

    public boolean isRampDown () {
        return rampDown;
    }

    public void loadCars (Car car) {
        if (car != null &&
                rampDown &&
                getCurrentSpeed() == 0 &&
                loadedCars.size() < MAX_CARS &&
                car.getNrDoors() <= maxDoorsAllowed &&
                !(car instanceof CarTransport) &&
                Math.abs(car.getX() - getX()) < 1 &&
                Math.abs(car.getY() - getY()) < 1) {
            loadedCars.add(car);
            car.setX(getX());
            car.setY(getY());

        }
    }

    public Car carUnloading() {
        if (rampDown &&
                getCurrentSpeed() == 0 &&
                !loadedCars.isEmpty()) {

            Car car = loadedCars.remove(loadedCars.size() -1);
            car.setX(getX());
            car.setY(getY());
            return car;
        }
        return null;
    }

    @Override
    public void move(){
        super.move();

        for (Car car: loadedCars) {
            car.setX(getX());
            car.setY(getY());
        }
    }

    @Override
    public void gas(double amount) {
        if (!rampDown) {
            super.gas(amount);
        }
    }

    @Override
    protected double speedFactor(){

        return enginePower * 0.01;
    }

    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    @Override
    protected void decrementSpeed(double amount) {

        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }


}










