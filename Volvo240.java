import java.awt.*;

public class Volvo240 extends Car {

    public final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, Color.black, 100, "Volvo240");


    }

    @Override
    protected double speedFactor(){

        return enginePower * 0.01 * trimFactor;
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
// Att det ska aldrig = 0

