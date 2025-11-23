import java.awt.Color; 
import java.util.Random;

// Position class
 class Pos {
    private int x;
    private int y;

    public Pos() {
    }

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Car {
    private String dir;
    private boolean isMoving;
    private Pos position;
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

    public void RandomColor(Color color) {
      final Random random = new Random();
       int n = random.nextInt(3);
        

        this.color = n < 2? n <1 ? Color.RED: Color.BLUE: Color.GREEN  ;
    }
}
