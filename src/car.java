import java.awt.Color; 


// Position class
public class Pos {
    private double x;
    private double y;

    public Pos() {
    }

    public Pos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

public class Car {
    private String dir;
    private boolean isMoving;
    private Pos position;
    private Color color;

    // Constructor
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
    public Pos getPosition() {
        return position;
    }

    public void setPosition(Pos position) {
        this.position = position;
    }

    // Getter and Setter for color
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
