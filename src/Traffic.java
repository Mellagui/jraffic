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

        // If lights are clearing → wait until intersection empties
        if (isClearing) {
            if (cars.isIntersectionClear()) {
                findNextGreenDirection(cars);
            }
            return;
        }

        // First cycle → choose a green
        if (greenDirection == null) {
            findNextGreenDirection(cars);
            return;
        }

        // Normal running: should we switch?
        if (now - last_change >= INTERVAL) {

            int capacity = cars.getLaneCapacity(w);
            int waiting = cars.getCarsWaiting().get(greenDirection);

            // Extension allowed but NEVER reset timer (avoids infinite green)
            if (waiting >= capacity && (now - last_change) < MAX_GREEN) {
                return;
            }

            // Otherwise → start clearing phase
            isClearing = true;
        }
    }

    private void findNextGreenDirection(Cars cars) {
        Map<String, Integer> carsWaiting = cars.getCarsWaiting();

        // Increase starvation counters
        for (String dir : waitingTimes.keySet()) {
            waitingTimes.put(dir, waitingTimes.get(dir) + 1);
        }

        double maxScore = -1;
        String nextGreen = null;

        String previous = this.greenDirection;

        for (Map.Entry<String, Integer> entry : carsWaiting.entrySet()) {
            String direction = entry.getKey();
            int count = entry.getValue();

            if (count > 0) {
                long time = waitingTimes.get(direction);

                // Balanced score: number × waiting time
                double score = count * time;

                // Slight penalty for repeating the same direction
                if (direction.equals(previous)) {
                    score *= 0.7;
                }

                if (score > maxScore) {
                    maxScore = score;
                    nextGreen = direction;
                }
            }
        }

        if (nextGreen != null) {
            waitingTimes.put(nextGreen, 0L); // reset starvation timer
        }

        greenDirection = nextGreen;
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
                g.setColor(t.getKey().equals(greenDirection)
                        ? java.awt.Color.GREEN
                        : java.awt.Color.RED);
            }

            g.fillOval(p.x, p.y, size, size);
        }
    }
}
