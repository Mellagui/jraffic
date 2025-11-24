import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Car {

    private String nextDir;
    private Point turningPoint;

    private String dir;
    private boolean isMoving = true;
    private Point position;
    private Color color;
    private boolean isInIntersection = false;
    private boolean hasLeftIntersection = false;

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

    public boolean hasLeftIntersection() {
        return hasLeftIntersection;
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

    public void move(Traffic traffic, Intersection intersection, int w, Car carInFront) {
        final int size = (w * 6) / 100;
        final int safetyGap = (w * 3) / 100;

        Rectangle carBounds = new Rectangle(position.x, position.y, size, size);
        if (isInIntersection && !hasLeftIntersection && !intersection.getBounds().intersects(carBounds)) {
            hasLeftIntersection = true;
        }
        if (!isInIntersection && intersection.getBounds().intersects(carBounds)) {
            isInIntersection = true;
        }

        if (isInIntersection && !hasLeftIntersection) {

            setMoving(true);
        } else {
            setMoving(true);

            if (carInFront != null) {
                int delta = 0;
                if (this.dir.equals(carInFront.getDir())) {
                    switch (dir) {
                        case "north" -> delta = carInFront.getPosition().y - getPosition().y;
                        case "south" -> delta = getPosition().y - carInFront.getPosition().y;
                        case "east" -> delta = carInFront.getPosition().x - getPosition().x;
                        case "west" -> delta = getPosition().x - carInFront.getPosition().x;
                    }
                    if (delta > 0 && delta < size + safetyGap) {
                        setMoving(false);
                    }
                }
            }

            if (isMoving() && !isInIntersection) {
                String greenDirection = traffic.getGreenDirection();
                if (!dir.equals(greenDirection)) {
                    Point nextPos = new Point(position);
                    switch (dir) {
                        case "north" -> nextPos.translate(0, 1);
                        case "south" -> nextPos.translate(0, -1);
                        case "east" -> nextPos.translate(1, 0);
                        case "west" -> nextPos.translate(-1, 0);
                    }
                    Rectangle nextCarBounds = new Rectangle(nextPos.x, nextPos.y, size, size);
                    if (intersection.getBounds().intersects(nextCarBounds)) {
                        setMoving(false);
                    }
                }
            }
        }

        if (!isMoving() || dir == null || position == null) {
            return;
        }

        if (turningPoint != null) {
            boolean turn = false;
            switch (dir) {
                case "north" -> turn = position.y >= turningPoint.y;
                case "south" -> turn = position.y <= turningPoint.y;
                case "east" -> turn = position.x >= turningPoint.x;
                case "west" -> turn = position.x <= turningPoint.x;
            }
            if (turn) {
                setDir(nextDir);
                setTurningPoint(null); // Turn only once
            }
        }

        switch (dir) {
            case "north" -> position.translate(0, 1);
            case "south" -> position.translate(0, -1);
            case "east" -> position.translate(1, 0);
            case "west" -> position.translate(-1, 0);
        }
    }
}
