
import java.awt.Rectangle;
import java.util.List;
import java.awt.Point;
import java.util.Map;

public class Intersection {

    private Rectangle bounds;

    public Intersection(Map<String, List<Point>> roadCenters) {
        if (roadCenters.isEmpty()) {
            bounds = new Rectangle(0, 0, 0, 0);
            return;
        }
        Point north_west = roadCenters.get("east").get(0);
        Point south_east = roadCenters.get("west").get(1);
        int x = north_west.x;
        int y = north_west.y;
        int width = south_east.x - x;
        int height = south_east.y - y;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isInside(Point p) {
        return bounds.contains(p);
    }
}
