import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

public class CarTransport extends Car {

    private boolean rampDown;
    private final int capacity;
    private final int maxDoorsAllowed;
    private final ArrayList<Car> loadedCars = new ArrayList<>();

    // avstånd <= 2.0
    private static final double Max_Distance = 2.0;

    public CarTransport(int capacity, int maxDoorsAllowed){
        super(2, Color.gray, 300, "CarTransport");
        this.capacity = capacity;
        this.maxDoorsAllowed = maxDoorsAllowed;
        this.rampDown = false;
    }

    public boolean isRampDown(){
        return rampDown;
    }

    public int getLoadedCount(){
        return loadedCars.size();
    }

    public void lowerRamp(){
        if(getCurrentSpeed() != 0) return;
        rampDown = true;
    }

    public void raiseRamp(){
        rampDown = false;
    }

    public void loadCar(Car car){
        if(!rampDown) return;
        if(getCurrentSpeed() !=0) return;
        if(car == null) return;

        //får inte lasta annan transport
        if(car instanceof CarTransport) return;

        if(loadedCars.size() >= capacity) return;

        //Storleksantagande
        if(car.getNrDoors() > maxDoorsAllowed) return;

        //nära krav
        if(!isNear(car)) return;

        loadedCars.add(car);

        //När lastad: samma position som transporten
        car.setPosition(this.getX(), this.getY());
        car.stopEngine();

    }
    public Car unloadCar(){
        if(!rampDown) return null;
        if(getCurrentSpeed() !=0) return null;
        if(loadedCars.isEmpty()) return null;

        Car car = loadedCars.remove(loadedCars.size()-1);

        //placera "rimligt nära", t.ex, bakom transporten
        car.setPosition(this.getX() - 1.0, this.getY());

        return car;
    }
    @Override
    public void gas(double amount){
        if(rampDown) return;
        super.gas(amount);
    }
    @Override
    public void move(){
        if(rampDown) return;
        super.move();

        //lastade bilar följer alltid exakt transportens postion
        for (Car c: loadedCars){
            c.setPosition(this.getX(), this.getY());

        }
    }
    private boolean isNear(Car car){
        double dx = car.getX() - this.getX();
        double dy = car.getY() - this.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist <= Max_Distance;
    }

    @Override
    public String getSpriteKey() {
        return "";
    }

    @Override
    //lite långsammare acceleration för tung transport
    protected double speedFactor(){
        return enginePower * 0.005;
    }
    @Override
    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    @Override
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}