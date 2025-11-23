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

    public void update() {
        if (stopped)
            return;
        switch (dir) {
            case 'N' -> y -= speed;
            case 'S' -> y += speed;
            case 'E' -> x += speed;
            case 'W' -> x -= speed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x - 10, y - 10, 20, 20);
    }

    public boolean isOffScreen(int w, int h) {
        return x < -50 || x > w + 50 || y < -50 || y > h + 50;
    }
}