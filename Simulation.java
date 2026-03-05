import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Car> cars = new ArrayList<>();
    private final CollisionService collision;
    private final WorkshopZone workshopZone;

    public Simulation(CollisionService collision, WorkshopZone workshopZone) {
        this.collision = collision;
        this.workshopZone = workshopZone;
    }

    public List<Car> getCars() { return cars; }

    public void addCar(Car c) { cars.add(c); }

    public Car removeCar() {
        if (cars.isEmpty()) return null;
        return cars.remove(cars.size() - 1);
    }

    public void tick(int width, int height) {
        for (Car c : cars) {
            c.move();
            collision.bounceOnWalls(c, width, height);
        }
        workshopZone.handle(cars);
    }
}