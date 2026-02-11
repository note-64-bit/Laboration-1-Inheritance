import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TestCar {
    private CarTransport transport;
    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;

    @Before
    public void Tests() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();

        transport = new CarTransport(2,4);

        transport.setX(0);
        transport.setY(0);

        saab.setX(0);
        saab.setY(0);

        volvo.setX(0);
        volvo.setY(0);

    }

    @Test
    public void testDoors() {
        assertEquals(4, volvo.getNrDoors());
        assertEquals(2, saab.getNrDoors());
    }

    // COLOR

    @Test
    public void testSetColor(){
        saab.setColor(Color.pink);
        assertEquals(Color.pink, saab.getColor());

    }

    @Test
    public void testColors() {
        assertEquals(Color.red, saab.getColor());
        assertEquals(Color.black, volvo.getColor());
    }


    // MOVE:
    @Test
    public void testMoveNorth(){
        saab.startEngine();
        saab.gas(1);
        double yBefore = saab.getY();
        saab.move();
        double yAfter = saab.getY();
        assertTrue(yAfter > yBefore);
    }
    @Test
    public void testMoveEast(){
        saab.startEngine();
        saab.gas(1);
        saab.turnRight(); // EAST

        double xBefore = saab.getX();
        saab.move();
        double xAfter = saab.getX();

        assertTrue(xAfter > xBefore);
    }

    @Test
    public void testMoveWest() {
        saab.startEngine();
        saab.gas(1);
        saab.turnLeft(); // WEST
        double xBefore = saab.getX();
        saab.move();
        double xAfter = saab.getX();
        assertTrue(xAfter < xBefore);
    }
    @Test
    public void testMoveSouth() {
        saab.startEngine();
        saab.gas(1);
        saab.turnRight();
        saab.turnRight();
        double yBefore = saab.getY();
        saab.move();
        double yAfter = saab.getY();
        assertTrue(yAfter < yBefore);
    }

            //ENGINE
    @Test
    public void testEngine() {
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed(), 0.0001);

        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed(), 0.0001);
    }

        //GAS
    @Test
    public void testGasLimits() {
        saab.startEngine();

        saab.gas(-2);   // <0
        double s1 = saab.getCurrentSpeed();

        saab.gas(3);    // >1
        double s2 = saab.getCurrentSpeed();

        assertTrue(s2 > s1);


        volvo.startEngine();
        double speedBefore = volvo.getCurrentSpeed();

        volvo.gas(0.5);

        double speedAfter = volvo.getCurrentSpeed();
        assertTrue(speedAfter > speedBefore);
    }


        //BRAKE
@   Test
    public void brake(){
        volvo.startEngine();
        volvo.gas(1.0); // ensure speed > 0
        double speedBefore = volvo.getCurrentSpeed();

        volvo.brake(0.5);

        double speedAfter = volvo.getCurrentSpeed();
        assertTrue(speedAfter < speedBefore);
    }

       // SAAB TURBO ON AND OFF
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
    public void testTurboOff() {
        saab.setTurboOn();
        saab.startEngine();
        saab.gas(1);

        double turboSpeed = saab.getCurrentSpeed();

        saab.stopEngine();
        saab.startEngine();

        saab.setTurboOff();
        saab.gas(1);
        double noTurboSpeed = saab.getCurrentSpeed();

        assertTrue(turboSpeed > noTurboSpeed);

    }

    // Negative speed

    @Test
    public void testNegativeSpeed() {
        saab.startEngine();
        saab.brake(1);
        saab.brake(1);
        assertEquals(0, saab.getCurrentSpeed(), 0.0001);
    }

    @Test
    public void testFlakAngle(){
        scania.raiseFlak(100);


        assertEquals(70.0, scania.getflakAngle(), 0.0001);
    }
    @Test
    public void testFlakAngleLower(){
        scania.startEngine();
        scania.gas(1);
        scania.raiseFlak(40);

        scania.lowerFlak(40);

        assertEquals(0.0, scania.getflakAngle(), 0.0001);
    }

    @Test
    public void testRaiseWhileMoving(){
        scania.startEngine();
        scania.gas(1);
        scania.raiseFlak(50);

        assertEquals(0, scania.getflakAngle(), 0.0001);

    }

    @Test
    public void testloadCars(){

        transport.lowerRamp();
        transport.loadCars(saab);

        Car unloaded = transport.carUnloading();
        assertEquals(saab, unloaded);
    }

    @Test
    public void testMaxCarsLimit() {
        CarTransport transport = new CarTransport(1, 4);

        transport.lowerRamp();

        transport.loadCars(saab);
        transport.loadCars(volvo); // ska INTE lastas (maxCars = 1)

        // Enda bilen som KAN ligga där är saab
        Car unloaded = transport.carUnloading();

        assertEquals(saab, unloaded);
        assertNull(transport.carUnloading());
    }

    @Test
    public void testLowerRamp() {
        transport.lowerRamp();
        assertTrue(transport.isRampDown());

    }
    @Test
    public void testLIFO() {
        transport.lowerRamp();
        transport.loadCars(saab);
        transport.loadCars(volvo);

        Car first = transport.carUnloading();
        Car second = transport.carUnloading();

        assertEquals(volvo, first);
        assertEquals(saab, second);
    }

}




