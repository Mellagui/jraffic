import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Car {
    private String dir;
    private final String next_dir;
    
    private Point position;
    private Point turn_point;

    private final int color;
    private final int w;
    
    private boolean moving;
    
    public Car(String direction, Map<String, List<Point>> center, int width) {
        moving = true;
        dir = direction;
        w = width;
        color = randomColor();
        position = startPosition(center);
        turn_point = turningPoint(center);
        next_dir = nextDirection();
    }

    public String getDir() {
        return dir;
    }
    public void setDir(String direction) {
        dir = direction;
    }

    public boolean getMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public String getNextDir() {
        return next_dir;
    }

    public Point getTurningPoint() {
        return turn_point;
    }
    public void setTurningPoint(Point turn_point) {
        this.turn_point = turn_point;
    }

    private int randomColor() {
        final Random random = new Random();
        return random.nextInt(3);
    }

    public void move() {
        if (!moving) return;

        final int x = position.x;
        final int y = position.y;

        switch (dir) {
            case "north" -> {
                if (x >= turn_point.x && y >= turn_point.y) setDir(next_dir);
                position.setLocation(x, y + 1);
            }
            case "south" -> {
                if (x <= turn_point.x && y <= turn_point.y) setDir(next_dir);
                position.setLocation(x, y - 1);
            }
            case "east" -> {
                if (x >= turn_point.x && y >= turn_point.y) setDir(next_dir);
                position.setLocation(x + 1, y);
            }
            default -> {
                if (x <= turn_point.x && y <= turn_point.y) setDir(next_dir);
                position.setLocation(x - 1, y);
            }
        }
    }

    public void handleColisions(Car car) {
        final String c_dir = car.getDir();
        final Point c_pos = car.getPosition();
        final int gap = (w * 3) / 100;

        switch (c_dir) {
            case "north" -> moving = position.y < c_pos.y - gap;
            case "south" -> moving = position.y > c_pos.y + gap;
            case "east"  -> moving = position.x < c_pos.x - gap;
            default      -> moving = position.x > c_pos.x + gap;
        }
    }

    public void draw(Graphics g) {
        final int size = (w * 6) / 100;
        g.setColor(color < 2 ? color < 1 ? Color.RED : Color.BLUE : Color.GREEN);
        g.fillRect(position.x, position.y, size, size);
    }

    private String nextDirection() {
        return switch (dir) {
            case "north" -> color < 2 ? color < 1 ? "west" : dir : "east";
            case "south" -> color < 2 ? color < 1 ? "east" : dir : "west";
            case "east" -> color < 2 ? color < 1 ? "north" : dir : "south";
            default -> color < 2 ? color < 1 ? "south" : dir : "north";
        };
    }

    private Point turningPoint(Map<String, List<Point>> center) {
        final List<Point> line = center.get(dir);
        final int size = (w * 6) / 100;
        final int gap = (w * 3) / 100;

        return switch (dir) {
            case "north" -> color < 2 ? color < 1 ? new Point(line.get(0).x + gap, line.get(0).y + gap) : position : new Point(line.get(0).x + gap, line.get(0).y + gap + (size*2));
            case "south" -> color < 2 ? color < 1 ? new Point(line.get(1).x - (gap+size), w/2 + gap/2) : position : new Point(line.get(1).x - (gap+size), w/2 - (size*2 - gap/2));
            case "east" -> color < 2 ? color < 1 ? new Point(line.get(0).x + gap, w/2 - (size*2 - gap/2)) : position : new Point(w/2 + gap, w/2 - (size*2 - gap/2));
            default -> color < 2 ? color < 1 ? new Point(w/2 + gap, line.get(0).y + gap) : position : new Point(w/2 - (gap+size), line.get(0).y + gap + (size*2));
        };
    }

    private Point startPosition(Map<String, List<Point>> center) {
        final List<Point> line = center.get(dir);
        final int size = (w * 6) / 100;
        final int gap = (w * 3) / 100;

        return switch (dir) {
            case "north" -> new Point(line.get(0).x + gap, 0);
            case "south" -> new Point(line.get(1).x - (gap+size), w);
            case "east" -> new Point(0, line.get(1).y - (gap+size));
            default -> new Point(w, line.get(0).y + gap);
        };
    }
}
