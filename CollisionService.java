public class CollisionService {

    public void bounceOnWalls(Car car, int width, int height) {
        boolean hit = false;

        if (car.getX() < 0) { car.setX(0); hit = true; }
        else if (car.getX() > width - WorldConfig.CAR_W) { car.setX(width - WorldConfig.CAR_W); hit = true; }

        if (car.getY() < 0) { car.setY(0); hit = true; }
        else if (car.getY() > height - WorldConfig.CAR_H) { car.setY(height - WorldConfig.CAR_H); hit = true; }

        if (hit) {
            car.stopEngine();
            invertDirection(car);
            car.startEngine();
        }
    }

    private void invertDirection(Car car) {
        switch (car.getDirection()) {
            case NORTH -> car.setDirection(Car.Direction.SOUTH);
            case SOUTH -> car.setDirection(Car.Direction.NORTH);
            case EAST  -> car.setDirection(Car.Direction.WEST);
            case WEST  -> car.setDirection(Car.Direction.EAST);
        }
    }
}