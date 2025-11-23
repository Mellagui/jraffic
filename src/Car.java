
import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Car {

    private String nextDir;
    private Point turningPoint ;

    private String dir;
    private boolean isMoving = true;
    private Point position;
    private Color color;

    public Car() {
    }

    // Getter and Setter for dir
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    // Getter and Setter for isMoving
    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    // Getter and Setter for position
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    // Getter and Setter for color
    public Color getColor() {
        return color;
    }

    public String getNextDir() {
        return nextDir;
    }

    public Point getTurningPoint() {
        return turningPoint;
    }

    public void setNextDir(String nextDir) {
        this.nextDir = nextDir;
    }

    public void setTurningPoint(Point turningPoint) {
        this.turningPoint = turningPoint;
    }

    public int RandomColor() {
        final Random random = new Random();
        int n = random.nextInt(3);

        this.color = n < 2 ? n < 1 ? Color.RED : Color.BLUE : Color.GREEN;

        return n;
    }

    public void move() {
        if (!isMoving || dir == null || position == null) {
            return;
        }

        int x = position.x;
        int y = position.y;

        switch (dir) {
            case "north" -> {
                if (turningPoint != null &&x >= turningPoint.x && y >= turningPoint.y) {
                    setDir(nextDir);
                }
                position.setLocation(x, y + 1);
            }
            case "south" -> {
                if (turningPoint != null &&x <= turningPoint.x && y <= turningPoint.y) {
                    setDir(nextDir);
                }
                position.setLocation(x, y - 1);
            }
            case "east" -> {
                if (turningPoint != null &&x >= turningPoint.x && y >= turningPoint.y) {
                    setDir(nextDir);
                }

                position.setLocation(x + 1, y);
            }
            default -> {
                if (turningPoint != null &&x <= turningPoint.x && y <= turningPoint.y) {
                    setDir(nextDir);
                }
                position.setLocation(x - 1, y);
            }
        }
    }

}
