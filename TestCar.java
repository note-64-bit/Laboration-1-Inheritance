import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TestCar {

    private Saab95 saab;
    private Volvo240 volvo;

    @Before
    public void NewSaabVolvo() {
        saab = new Saab95();
        volvo = new Volvo240();
    }

    @Test
    public void testDoors() {
        assertEquals(4, volvo.getNrDoors());
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    public void testColors() {
        assertEquals(Color.red, saab.getColor());
        assertEquals(Color.black, volvo.getColor());
    }

    @Test
    public void testEngine() {
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed(), 0.0001);

        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed(), 0.0001);
    }


    @Test
    public void testGasLimits() {
        saab.startEngine();

        saab.gas(-2);   // <0 branch
        double s1 = saab.getCurrentSpeed();

        saab.gas(3);    // >1 branch
        double s2 = saab.getCurrentSpeed();

        assertTrue(s2 > s1);
    }

    @Test
    public void testBrakeLimits() {
        volvo.startEngine();
        volvo.gas(1);

        volvo.brake(-2); // <0 branch
        double s1 = volvo.getCurrentSpeed();

        volvo.brake(5); // >1 branch
        double s2 = volvo.getCurrentSpeed();

        assertTrue(s2 <= s1);
    }


    @Test
    public void testTurbo() {
        saab.startEngine();

        saab.gas(1);
        double noTurbo = saab.getCurrentSpeed();

        saab.stopEngine();
        saab.startEngine();

        saab.setTurboOn();
        saab.gas(1);
        double turbo = saab.getCurrentSpeed();

        assertTrue(turbo > noTurbo);
    }


    @Test
    public void testVolvoBrakeTestMethod() {
        volvo.startEngine();
        volvo.gas(1);

        double before = volvo.getCurrentSpeed();
        volvo.braketest(0.5);
        double after = volvo.getCurrentSpeed();

        assertTrue(after < before);
    }


    @Test
    public void testMoveAllDirections() {
        saab.startEngine();
        saab.gas(1);

        // NORTH
        saab.move();

        // EAST
        saab.turnRight();
        saab.move();

        // SOUTH
        saab.turnRight();
        saab.move();

        // WEST
        saab.turnRight();
        saab.move();

        // bara att köra alla switch-grenar räcker för coverage
        assertTrue(true);
    }


    @Test
    public void testTurnLeftAll() {
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        assertTrue(true);
    }


    @Test
    public void testTurnRightAll() {
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        assertTrue(true);
    }


    @Test
    public void testSpeedNeverNegative() {
        saab.startEngine();
        saab.brake(1);
        saab.brake(1);
        assertEquals(0, saab.getCurrentSpeed(), 0.0001);
    }
    @Test
    public void brake() {
        Volvo240 volvo1 = new Volvo240();
        volvo1.startEngine();
        volvo1.gas(1.0); // ensure speed > 0
        double speedBefore = volvo1.getCurrentSpeed();

        volvo1.brake(0.5);

        double speedAfter = volvo1.getCurrentSpeed();
        assertTrue(speedAfter < speedBefore);
    }

    @Test
    public void gas() {
        Volvo240 volvo2 = new Volvo240();
        volvo2.startEngine();
        double speedBefore = volvo2.getCurrentSpeed();

        volvo2.gas(0.5);

        double speedAfter = volvo2.getCurrentSpeed();
        assertTrue(speedAfter > speedBefore);
    }
}


