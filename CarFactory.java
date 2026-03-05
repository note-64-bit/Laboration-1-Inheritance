import java.util.Random;

public class CarFactory {
    private final Random rnd = new Random();

    public Car createRandomCar() {
        int r = rnd.nextInt(3);
        return switch (r) {
            case 0 -> new Volvo240();
            case 1 -> new Saab95();
            default -> new Scania();
        };
    }
}