import java.awt.Color;
import java.awt.Graphics;

public class Car {
    public int x, y;
    public int speed = 3;
    public char dir; // 'N', 'S', 'E', 'W'
    public boolean stopped = false;
    public Color color;

    public Car(int x, int y, char dir, Color color) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.color = color;
    }
}