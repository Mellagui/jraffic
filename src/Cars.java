import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Cars {
    private final Map<String, List<Car>> cars = new HashMap<>();
    private final Random random = new Random();
    private final String[] dirs = {"north", "south", "east", "west"};

    public Cars() {
        for (String dir : dirs) cars.put(dir, new ArrayList<>());
    }

    public void add(String dir, Map<String, List<Point>> center, int w) {
        List<Car> c_list = cars.get(dir);
        if (!canAdd(c_list, w)) return;

        Car car = new Car(dir, center, w);
        if (c_list == null) {
            c_list = new ArrayList<>();
            cars.put(dir, c_list);
        }
        c_list.add(car);
    }

    public void random(Map<String, List<Point>> center, int w) {
        final int n = random.nextInt(4);
        add(dirs[n], center, w);
    }

    public void update(Map<String, List<Point>> center, int w) {
        for (Map.Entry<String, List<Car>> map : cars.entrySet()) {
            final String dir = map.getKey();
            final List<Car> c_list = map.getValue();

            for (int i = 0; i < c_list.size(); i++) {
                final Car car = c_list.get(i);
                
                if (i == 0) {
                    final Point c_pos = car.getPosition();
                    final List<Point> line = center.get(dir);
                    
                    final boolean moving = switch (dir) {
                        case "north" -> c_pos.y < line.get(0).y;
                        case "south" -> c_pos.y > line.get(0).y;
                        case "east"  -> c_pos.x < line.get(0).x;
                        default      -> c_pos.x > line.get(0).x;
                    };
                    System.out.println(moving);
                    
                    car.setMoving(moving);
                    continue;
                } else {
                    car.handleColisions(c_list.get(i - 1));
                }
                
                car.move();
            }
        }
    }

    public boolean canAdd(List<Car> c_list, int w) {
        if (c_list == null || c_list.isEmpty()) return true;

        final int distance = (w * 12) / 100;
        final int size = (w * 6) / 100;
        final int gap = (w * 3) / 100;

        final String c_dir = c_list.getLast().getDir();
        final Point c_pos = c_list.getLast().getPosition();

        final Boolean can = switch (c_dir) {
            case "north" -> c_pos.y >= size + gap;
            case "south" -> c_pos.y + (size * 2) + gap <= w;
            case "east" ->  c_pos.x >= size + gap;
            default ->      c_pos.x + (size * 2) + gap <= w;
        };

        if (c_list.size() * (size + gap) >= distance - (size + gap)) return false;

        return can && true;
    }

    public void draw(Graphics g) {
        for (Map.Entry<String, List<Car>> c_list : cars.entrySet()) {
            for (Car car : c_list.getValue()) {
                car.draw(g);
            }
        }
    }
}