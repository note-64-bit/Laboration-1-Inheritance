 import java.util.ArrayList;
import java.util.List;

public class Workshop<T extends Car> { //T en typparameter, en platshållare.

    private final int capacity;
    private final List<T> cars = new ArrayList<>();

    public Workshop(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity() {return capacity;} //Hur många platser totalt
    public int getCount() {return cars.size();} //Hur många bilar finns just nu

    public void addCar(T car){
        if(car == null) return;
        if(cars.size() >= capacity) return;
        cars.add(car);
    }
    // Last in first out
    public T removeCar(){
        if(cars.isEmpty()) return null;
        return cars.remove(cars.size() - 1);
    }
}
