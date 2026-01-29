public class CarTest {
    public static void main(String[] args) {

        Car volvo = new Volvo240();
        Car saab = new Saab95();

        // Starta motorerna
        volvo.startEngine();
        saab.startEngine();

        System.out.println("Volvo start: " + volvo.getCurrentSpeed());
        System.out.println("Saab start: " + saab.getCurrentSpeed());

        // Testa gas
        volvo.gas(1);
        saab.gas(1);

        System.out.println("Volvo efter gas: " + volvo.getCurrentSpeed());
        System.out.println("Saab efter gas: " + saab.getCurrentSpeed());

        // Testa brake
        volvo.brake(1);
        saab.brake(1);

        System.out.println("Volvo efter broms: " + volvo.getCurrentSpeed());
        System.out.println("Saab efter broms: " + saab.getCurrentSpeed());
    }
}


