
import java.awt.Graphics;
import java.awt.Point;
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

    public void add(String direction, Map<String, List<Point>> center, int w) {

        final int gap = (w * 3) / 100;
        if (!canadd(direction, center, w)) {
            return;
        }
        Car car = new Car();
        car.setDir(direction);
        final int size = (w * 6) / 100;
        int n = car.RandomColor();
        String car_dir =car.getDir();
        Point pos =car.getPosition();
        switch (car_dir) {
            case "north" -> {
                car.setNextDir(n < 2 ? n < 1 ? "west" : car_dir : "east");
                car.setTurningPoint(n < 2 ? n < 1 ? new Point(center.get(car_dir).get(0).x + gap, center.get(car_dir).get(0).y + gap) : pos : new Point(center.get(car_dir).get(0).x + gap,center.get(car_dir).get(0).y + gap + (size*2)));
            }
            case "south" -> {
                car.setNextDir(n < 2 ? n < 1 ? "east" : car_dir : "west");
                car.setTurningPoint(n < 2 ? n < 1 ? new Point(center.get(car_dir).get(1).x - (gap+size),w/2+ gap/2) : pos : new Point(center.get(car_dir).get(1).x - (gap+size),w/2-(size*2 - gap/2)));
            }
            case "east" -> {
                car.setNextDir(n < 2 ? n < 1 ? "north" : car_dir : "south"); 
                car.setTurningPoint(n < 2 ? n < 1 ? new Point(center.get(car_dir).get(0).x+ gap, w/2-(size*2 - gap/2)) : pos : new Point(w/2 + gap, w/2-(size*2 - gap/2)));
            }
            default -> {
                car.setTurningPoint(n < 2 ? n < 1 ? new Point(w/2 + gap, center.get(car_dir).get(0).y + gap) : pos : new Point(w/2 - (gap+size) ,center.get(car_dir).get(0).y + gap + (size*2)));
                 car.setNextDir(n < 2 ? n < 1 ? "south" : car_dir : "north");
            }
        }
        switch (direction) {
            case "north" ->
                car.setPosition(new Point(center.get("north").get(0).x + gap, 0));
            case "south" ->
                car.setPosition(new Point(center.get("south").get(1).x - (gap + size), w));
            case "west" ->
                car.setPosition(new Point(w, center.get("west").get(0).y + gap));
            default ->
                car.setPosition(new Point(0, center.get("west").get(1).y - (gap + size)));
        }

        List<Car> carsInDirection = mapCar.get(direction);
        if (carsInDirection == null) {
            carsInDirection = new ArrayList<>();
            mapCar.put(direction, carsInDirection);
        }

        carsInDirection.add(car);
    }

    public void random(Map<String, List<Point>> center, int w) {
        int n = random.nextInt(4);

        switch (n) {
            case 1 ->
                add("south", center, w);
            case 2 ->
                add("north", center, w);
            case 3 ->
                add("west", center, w);
            default ->
                add("east", center, w);
        }
    }

    // Optional: get cars by direction
    public List<Car> getCars(String direction) {
        return mapCar.getOrDefault(direction, new ArrayList<>());
    }

    public void update(Map<String, List<Point>> center, int w, Traffic traffic, Intersection intersection) {
        for (Map.Entry<String, List<Car>> en : mapCar.entrySet()) {
            for (Car car : en.getValue()) {
                car.move(traffic, intersection, w);
            }
        }
    }

    public boolean canadd(String deriction, Map<String, List<Point>> center, int w) {
        List<Point> line = center.get("north");
        int distinse = line.get(0).y;
        final int size = (w * 6) / 100;
        final int gap = (w * 3) / 100;
        if (mapCar.get(deriction).isEmpty()) {
            return true;
        }
        Point last = mapCar.get(deriction).get(mapCar.get(deriction).size() - 1).getPosition();
        //    Boolean can =  true ;
        Boolean can = switch (deriction) {
            case "north" ->
                last.y >= size + gap;
            case "south" ->
                last.y + (size * 2) + gap <= w;
            case "east" ->
                last.x >= size + gap;
            default ->
                last.x + (size * 2) + gap <= w;
        };
        if (getCars(deriction).size() * (size + gap) >= distinse - (size + gap)) {
            return false;

        }
        return can && true;

    }

    public void draw(Graphics g, int w) {
        final int size = (w * 6) / 100;
        for (Map.Entry<String, List<Car>> en : mapCar.entrySet()) {

            for (Car car : en.getValue()) {
                g.setColor(car.getColor());
                g.fillRect(car.getPosition().x, car.getPosition().y, size, size);

            }

        }

    }
}
