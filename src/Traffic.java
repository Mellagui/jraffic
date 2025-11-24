import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Traffic {
    private final String[] dirs = {"north", "east", "south", "west"};
    private int g_idx = 0;
    private long last_change = System.currentTimeMillis();
    private static final long INTERVAL = 5000L;
    private boolean isClearing = false;

    private Map<String, Point> t_pos = new HashMap<>();

    public String getGreenDirection() {
        if (isClearing) {
            return null;
        }
        return dirs[g_idx];
    }

    public boolean isClearing() {
        return isClearing;
    }

    public void setTrafficPositions(Map<String, Point> traffic_positions) {
        t_pos = traffic_positions;
    }

    public void update(Cars cars) {
        final long now = System.currentTimeMillis();
        if (isClearing) {
            if (cars.isIntersectionClear()) {
                switchToNextGreen();
            }
        } else {
            if (now - last_change >= INTERVAL) {
                isClearing = true;
            }
        }
    }

    private void switchToNextGreen() {
        g_idx = (g_idx + 1) % dirs.length;
        isClearing = false;
        last_change = System.currentTimeMillis();
    }

    public void draw(Graphics g, int w) {
        final int size = (w * 6) / 100;

        for (Map.Entry<String, Point> t : t_pos.entrySet()) {
            final Point p = t.getValue();
            if (isClearing) {
                g.setColor(java.awt.Color.RED);
            } else {
                g.setColor(t.getKey().equals(dirs[g_idx]) ? java.awt.Color.GREEN : java.awt.Color.RED);
            }
            g.fillOval(p.x, p.y, size, size);
        }
    }
}