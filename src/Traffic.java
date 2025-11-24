import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Traffic {
    private String greenDirection = null;
    private long last_change = System.currentTimeMillis();
    private static final long INTERVAL = 5000L; // base green duration
    private static final long MAX_GREEN = 15000L; // absolute max allowed green

    private boolean isClearing = false;
    private Map<String, Long> waitingTimes = new HashMap<>();

    private Map<String, Point> t_pos = new HashMap<>();

    public Traffic() {
        waitingTimes.put("north", 0L);
        waitingTimes.put("east", 0L);
        waitingTimes.put("south", 0L);
        waitingTimes.put("west", 0L);
    }

    public String getGreenDirection() {
        return isClearing ? null : greenDirection;
    }

    public boolean isClearing() {
        return isClearing;
    }

    public void setTrafficPositions(Map<String, Point> traffic_positions) {
        t_pos = traffic_positions;
    }

    public void update(Cars cars, int w) {
        final long now = System.currentTimeMillis();

        if (isClearing) {
            if (cars.isIntersectionClear()) {
                findNextGreenDirection(cars);
            }
            return;
        }

        if (greenDirection == null) {
            findNextGreenDirection(cars);
            return;
        }

        if (now - last_change >= INTERVAL) {
            int capacity = cars.getLaneCapacity(w);
            int waiting = cars.getCarsWaiting().get(greenDirection);

            if (waiting >= capacity && (now - last_change) < MAX_GREEN) {
                return;
            }

            isClearing = true;
        }
    }

    private void findNextGreenDirection(Cars cars) {
        Map<String, Integer> carsWaiting = cars.getCarsWaiting();

        // Increase waiting time counters
        for (String dir : waitingTimes.keySet()) {
            waitingTimes.put(dir, waitingTimes.get(dir) + 1);
        }

        String next = null;
        long bestScore = -1;

        for (String dir : waitingTimes.keySet()) {

            int carCount = carsWaiting.get(dir);

            if (carCount == 0)
                continue;

            // FAIR SCORE = waitingTime dominates heavily
            long score = waitingTimes.get(dir) * 100L + carCount;

            if (score > bestScore) {
                bestScore = score;
                next = dir;
            }
        }

        if (next != null) {
            waitingTimes.put(next, 0L);
        }

        greenDirection = next;
        isClearing = false;
        last_change = System.currentTimeMillis();
    }

    public void draw(Graphics g, int w) {
        final int size = (w * 6) / 100;

        for (Map.Entry<String, Point> t : t_pos.entrySet()) {
            Point p = t.getValue();

            if (isClearing) {
                g.setColor(java.awt.Color.RED);
            } else {
                g.setColor(
                        t.getKey().equals(greenDirection)
                                ? java.awt.Color.GREEN
                                : java.awt.Color.RED);
            }

            g.fillOval(p.x, p.y, size, size);
        }
    }
}
