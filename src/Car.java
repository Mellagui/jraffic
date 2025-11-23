import java.awt.Color;
import java.awt.Point; 
import java.util.Random;


public class Car {
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

    public void RandomColor() {
      final Random random = new Random();
       int n = random.nextInt(3);
        

        this.color = n < 2? n <1 ? Color.RED: Color.BLUE: Color.GREEN  ;
    }
    public void move() {
        if (!isMoving || dir == null || position == null) return;

        int x = position.x;
        int y = position.y;

        switch (dir) {
            case "north" -> position.setLocation(x, y + 2);
            case "south" -> position.setLocation(x, y - 2);
            case "east"  -> position.setLocation(x + 2, y);
            case "west"  -> position.setLocation(x - 2, y);
        }
    }

}
