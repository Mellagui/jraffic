import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Traffic {
    private String greenDirection = null;
    private long last_change = System.currentTimeMillis();
    private static final long INTERVAL = 5000L;
    private static final long MAX_GREEN_TIME = 15000L; // 15 seconds
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
        if (isClearing) {
            return null;
        }
        return greenDirection;
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

        long greenTime = now - last_change;

        boolean shouldSwitch = false;
        if (greenTime >= MAX_GREEN_TIME) {
            shouldSwitch = true;
        } else if (greenTime >= INTERVAL) {
            Map<String, Integer> carsWaiting = cars.getCarsWaiting();
            int otherCarsWaiting = 0;
            for (Map.Entry<String, Integer> entry : carsWaiting.entrySet()) {
                if (!entry.getKey().equals(greenDirection)) {
                    otherCarsWaiting += entry.getValue();
                }
            }

            if (otherCarsWaiting > 0) {
                shouldSwitch = true;
            }
        }

        if (shouldSwitch) {
            isClearing = true;
        }
    }

    private void findNextGreenDirection(Cars cars) {
        Map<String, Integer> carsWaiting = cars.getCarsWaiting();
        
        for (String dir : waitingTimes.keySet()) {
            waitingTimes.put(dir, waitingTimes.get(dir) + 1);
        }

        double maxScore = -1;
        String nextGreen = null;

        for (Map.Entry<String, Integer> entry : carsWaiting.entrySet()) {
            String direction = entry.getKey();
            int waitingCount = entry.getValue();
            long waitingTime = waitingTimes.get(direction);
            
            if (waitingCount > 0) {
                double score = waitingCount * waitingTime;
                if (score > maxScore) {
                    maxScore = score;
                    nextGreen = direction;
                }
            }
        }

        if (nextGreen != null) {
            waitingTimes.put(nextGreen, 0L); // Reset waiting time for the new green direction
        }

        greenDirection = nextGreen;
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
                g.setColor(t.getKey().equals(greenDirection) ? java.awt.Color.GREEN : java.awt.Color.RED);
            }
            g.fillOval(p.x, p.y, size, size);
        }
    }
}