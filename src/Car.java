import java.awt.Color;
import java.awt.Graphics;

public class Car {
    public int x, y;
    public int speed = 2;
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
        int carLength = 30;
        int carWidth = 16;

        if (dir == 'N' || dir == 'S') {
            // Car body (vertical)
            g.setColor(color);
            g.fillRoundRect(x - carWidth / 2, y - carLength / 2, carWidth, carLength, 6, 6);

            // Wheels
            g.setColor(Color.BLACK);
            g.fillRect(x - carWidth / 2 - 3, y - carLength / 4, 4, 8);
            g.fillRect(x + carWidth / 2 - 1, y - carLength / 4, 4, 8);
            g.fillRect(x - carWidth / 2 - 3, y + carLength / 4 - 8, 4, 8);
            g.fillRect(x + carWidth / 2 - 1, y + carLength / 4 - 8, 4, 8);

            // Front indicator
            g.setColor(Color.WHITE);
            if (dir == 'N')
                g.fillRect(x - 4, y - carLength / 2 + 2, 8, 4);
            else
                g.fillRect(x - 4, y + carLength / 2 - 6, 8, 4);

        } else {
            // Car body (horizontal)
            g.setColor(color);
            g.fillRoundRect(x - carLength / 2, y - carWidth / 2, carLength, carWidth, 6, 6);

            // Wheels
            g.setColor(Color.BLACK);
            g.fillRect(x - carLength / 4, y - carWidth / 2 - 3, 8, 4);
            g.fillRect(x - carLength / 4, y + carWidth / 2 - 1, 8, 4);
            g.fillRect(x + carLength / 4 - 8, y - carWidth / 2 - 3, 8, 4);
            g.fillRect(x + carLength / 4 - 8, y + carWidth / 2 - 1, 8, 4);

            // Front indicator
            g.setColor(Color.WHITE);
            if (dir == 'E')
                g.fillRect(x + carLength / 2 - 6, y - 4, 4, 8);
            else
                g.fillRect(x - carLength / 2 + 2, y - 4, 4, 8);
        }
    }

    public boolean isOffScreen(int w, int h) {
        return x < -50 || x > w + 50 || y < -50 || y > h + 50;
    }
}