import java.awt.Rectangle;
import java.util.List;

public class WorkshopZone {
    private final Rectangle bounds;
    private final Workshop<Volvo240> workshop;

    public WorkshopZone(Rectangle bounds, Workshop<Volvo240> workshop) {
        this.bounds = bounds;
        this.workshop = workshop;
    }

    public Rectangle getBounds() { return bounds; }

    // Strategi: När Volvo krockar -> lasta in och TA BORT från världen (cars-listan)
    public void handle(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            Car c = cars.get(i);
            if (c instanceof Volvo240 v && intersects(v)) {
                v.stopEngine();
                v.setPosition(bounds.getX(), bounds.getY());
                workshop.loadCar(v);
                cars.remove(i);
                i--;
            }
        }
    }

    private boolean intersects(Car car) {
        // enkel “nära nog”-krock
        return Math.abs(car.getX() - bounds.getX()) < 50
                && Math.abs(car.getY() - bounds.getY()) < 50;
    }
}