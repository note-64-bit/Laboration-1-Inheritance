import java.awt.*;

public class Scania extends Car {

    private double flakAngle;


    public Scania() {
        super(2, Color.BLACK, 500, "SCANIA");
        this.flakAngle = 0;

    }
    public double getflakAngle() {
        return flakAngle;
    }

    //public speedfactor:
    public void raiseFlak(double angle) {
        if (currentSpeed == 0) {
            flakAngle += angle;
            if (angle > 70) {
                flakAngle = 70;

            }
        }
    }

    public void lowerFlak(double angle) {
        if (currentSpeed == 0) {
            flakAngle -= angle;
            if (angle < 0) {
                flakAngle = 0;
            }
        }
    }

    @Override
    public void gas(double amount) {
        if (flakAngle == 0) {
            super.gas(amount);
        }
    }


    @Override
    protected double speedFactor() {
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
