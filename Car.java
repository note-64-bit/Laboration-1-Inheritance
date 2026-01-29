import java.awt.*;

public abstract class Car implements Movable {

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    protected double x;
    protected double y;
    protected Direction direction;


    protected Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.currentSpeed = 0;
        this.modelName = modelName;
        this.x = 0;
        this.y = 0;
        this.direction = Direction.NORTH;
    }


    protected int getNrDoors() {
        return nrDoors;
    }

    protected double getEnginePower() {
        return enginePower;
    }

    protected double getCurrentSpeed() {
        return currentSpeed;
    }

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color clr) {
        color = clr;
    }

    protected void startEngine() {
        currentSpeed = 0.1;
    }

    protected void stopEngine() {
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    protected abstract void incrementSpeed(double amount);

    protected abstract void decrementSpeed(double amount);
//Abstract används för att car vet inte hur hastigheten
// ska minskas bara att den måste minskas.

    public void gas(double amount) {
        if (amount < 0) {
            amount = 0;
        } else if (amount > 1) {
            amount = 1;
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0) {
            amount = 0;
        } else if (amount > 1) {
            amount = 1;
        }
        decrementSpeed(amount);
    }


    @Override
    public void move() {
        switch (direction) {
            case Direction.NORTH -> y += currentSpeed;
            case Direction.SOUTH -> y -= currentSpeed;
            case Direction.EAST -> x += currentSpeed;
            case Direction.WEST -> x -= currentSpeed;


        }
    }

    @Override
    public void turnLeft() {
        switch (direction) {
            case Direction.NORTH -> direction = Direction.WEST;
            case Direction.EAST -> direction = Direction.SOUTH;
            case Direction.SOUTH -> direction = Direction.EAST;
            case Direction.WEST -> direction = Direction.NORTH;
        }

    }

    @Override
    public void turnRight() {
        switch (direction) {
            case Direction.NORTH -> direction = Direction.EAST;
            case Direction.EAST -> direction = Direction.SOUTH;
            case Direction.SOUTH -> direction = Direction.WEST;
            case Direction.WEST -> direction = Direction.NORTH;
        }
    }
}

