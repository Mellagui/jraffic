
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Cars {

    private Map<String, List<Car>> mapCar = new HashMap<>();
    final Random random = new Random();

    public Cars() {
        mapCar.put("south", new ArrayList<>());
        mapCar.put("north", new ArrayList<>());
        mapCar.put("west", new ArrayList<>());
        mapCar.put("east", new ArrayList<>());
    }

public void add(String direction) {
    Car car = new Car();
    car.setDir(direction);
    car.RandomColor();

    switch (direction) {
        case "north" -> car.setPosition(new Pos(500, 0));      
        case "south" -> car.setPosition(new Pos(500, 1000));  
        case "west"  -> car.setPosition(new Pos(1000, 500));   
        default -> car.setPosition(new Pos(500, 500));         
    }

    List<Car> carsInDirection = mapCar.get(direction);
    if (carsInDirection == null) {
        carsInDirection = new ArrayList<>();
        mapCar.put(direction, carsInDirection);
    }

    carsInDirection.add(car);
}

    public void random() {
        int n = random.nextInt(4);

        switch (n) {
            case 1 ->
                add("south");
            case 2 ->
                add("north");
            case 3 ->
                add("west");
            default ->
                add("east");
        }
    }

    // Optional: get cars by direction
    public List<Car> getCars(String direction) {
        return mapCar.getOrDefault(direction, new ArrayList<>());
    }
}
