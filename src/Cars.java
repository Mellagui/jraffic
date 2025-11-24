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
        for (final Map.Entry<String, List<Car>> map : cars.entrySet()) {
            final String dir = map.getKey();

            final List<Car> c_list_after = new ArrayList<>();
            final List<Car> c_list_before = new ArrayList<>();

            for (final Car car : map.getValue()) {
                if (car.getPassed()) c_list_after.add(car);
                else c_list_before.add(car);
            }

            for (final Car car : c_list_after) car.move();

            for (int i = 0; i < c_list_before.size(); i++) {
                final Car car = c_list_before.get(i);

                car.move();
                
                if (i == 0) {
                    final Point c_pos = car.getPosition();
                    final List<Point> line = center.get(dir);
                    
                    final boolean moving = switch (dir) {
                        case "north" -> c_pos.y < line.get(0).y - ((w * 6) / 100);
                        case "south" -> c_pos.y > line.get(0).y;
                        case "east"  -> c_pos.x < line.get(0).x - ((w * 6) / 100);
                        default      -> c_pos.x > line.get(0).x;
                    };
                    System.out.println(line.toString());
                    
                    car.setMoving(moving);
                    continue;
                }

                car.handleColisions(c_list_before.get(i - 1));    
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
            case "east"  -> c_pos.x >= size + gap;
            default      -> c_pos.x + (size * 2) + gap <= w;
        };

        // if (c_list.size() * (size + gap) >= distance - (size + gap)) return false;

        return can;
    }

    public void draw(Graphics g) {
        for (final Map.Entry<String, List<Car>> c_list : cars.entrySet()) {
            for (final Car car : c_list.getValue()) {
                car.draw(g);
            }
        }
    }
}