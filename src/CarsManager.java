import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class CarsManager {
    public ArrayList<Car> cars = new ArrayList<>();

    public void spawn(int w, int h, String greenDir) {
        char dir = switch (greenDir) {
            case "north" -> 'S'; // come from south moving north
            case "south" -> 'N';
            case "east" -> 'W';
            default -> 'E';
        };

        int x = w / 2;
        int y = h / 2;

        if (dir == 'N') {
            x += 70;
            y = h;
        }
        if (dir == 'S') {
            x -= 70;
            y = 0;
        }
        if (dir == 'E') {
            x = 0;
            y += 70;
        }
        if (dir == 'W') {
            x = w;
            y -= 70;
        }

        cars.add(new Car(x, y, dir, randomColor()));
    }

    public void updateAll(Traffic traffic, int w, int h) {
        Iterator<Car> it = cars.iterator();
        while (it.hasNext()) {
            Car c = it.next();

            boolean mustStop = !traffic.getGreenDirection()
                .substring(0,1).equalsIgnoreCase(Character.toString(c.dir));

            c.stopped = mustStop;
            c.update();

            if (c.isOffScreen(w, h)) it.remove();
        }
    }

    public void drawAll(Graphics g) {
        for (Car c : cars)
            c.draw(g);
    }

    private Color randomColor() {
        return new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255));
    }
}