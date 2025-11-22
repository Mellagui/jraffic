// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.Point;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Map.Entry;

// import javax.swing.JPanel;
// import javax.swing.Timer;

// public class JrafficCanvas extends JPanel {

//     // global properties
//     private Timer animation;
//     private final String[] directions = {"north", "east", "south", "west"};
    
//     // window size
//     public int width, heigth;

//     // road
//     public final Map<String, List<Point>> road_center = new HashMap<>(); // rectangle : 4 points represant center of roads

//     // trafic
//     public final Map<String, Point> trafic_place = new HashMap<>();
//     public int green_trafic_index = 0;
//     private long lastTrafficChangeTime = System.currentTimeMillis();
//     private static final long TRAFFIC_CHANGE_INTERVAL_MS = 5000L; // 5 seconds

//     @Override
//     public void setSize(int width, int heigth) {
//         this.width = width;
//         this.heigth = heigth;
//     }

//     public JrafficCanvas() {
//         setBackground(Color.BLACK);
//         setFocusable(true);

//         // Keyboard handling
//         addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {

//                 // Press SPACE
//                 if (e.getKeyCode() == KeyEvent.VK_SPACE) {

//                 }

//                 // Press ESC to exit the app
//                 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                     System.exit(0);
//                 }

//                 startAnimation();
//                 repaint();
//             }
//         });
//     }

//     public void startAnimation() {
//         animation = new Timer(16, e -> {

//             // System.out.println("animation here:-----");
            
//             // update();

//             repaint();
//         });

//         animation.start();
//     }

//     public void roads(Graphics g) {
//         final List<List<Point[]>> roads_directions = new ArrayList<>();

//         final int middle_w = this.width / 2;
//         final int middle_h = this.heigth / 2;
//         final int road_w = (this.width * 12)/100;
//         final int road_h = (this.width * 12)/100;
//         final int trafic_gap = (this.width * 3)/100;

//         final List<Point[]> north = new ArrayList<>();
//         final Point[] n1 = {new Point(middle_w - road_w, 0), new Point(middle_w - road_w, middle_h - road_h)}; north.add(n1); //
//         final Point[] n2 = {new Point(middle_w, 0), new Point(middle_w, middle_h - road_h)}; north.add(n2);
//         final Point[] n3 = {new Point(middle_w + road_w, 0), new Point(middle_w + road_w, middle_h - road_h)}; north.add(n3); //
//         roads_directions.add(north);

//         final List<Point[]> south = new ArrayList<>();
//         final Point[] s1 = {new Point(middle_w - road_w, middle_h * 2), new Point(middle_w - road_w, middle_h + road_h)}; south.add(s1); //
//         final Point[] s2 = {new Point(middle_w, middle_h * 2), new Point(middle_w, middle_h + road_h)}; south.add(s2);
//         final Point[] s3 = {new Point(middle_w + road_w, middle_h * 2), new Point(middle_w + road_w, middle_h + road_h)}; south.add(s3); //
//         roads_directions.add(south);

//         final List<Point[]> east = new ArrayList<>();
//         final Point[] e1 = {new Point(0, middle_h - road_h), new Point(middle_w - road_w, middle_h - road_h)}; north.add(e1); //
//         final Point[] e2 = {new Point(0, middle_h), new Point(middle_w - road_w, middle_h)}; east.add(e2);
//         final Point[] e3 = {new Point(0, middle_h + road_h), new Point(middle_w - road_w, middle_h + road_h)}; east.add(e3); //
//         roads_directions.add(east);

//         final List<Point[]> west = new ArrayList<>();
//         final Point[] w1 = {new Point(middle_w * 2, middle_h - road_h), new Point(middle_w + road_w, middle_h - road_h)}; west.add(w1); //
//         final Point[] w2 = {new Point(middle_w * 2, middle_h), new Point(middle_w + road_w, middle_h)}; west.add(w2);
//         final Point[] w3 = {new Point(middle_w * 2, middle_h + road_h), new Point(middle_w + road_w, middle_h + road_h)}; west.add(w3); //
//         roads_directions.add(west);

//         // draw roads
//         for (final List<Point[]> dir : roads_directions) {
//             for (final Point[] line : dir) {
//                 final Point p1 = line[0];
//                 final Point p2 = line[1];
//                 g.drawLine(p1.x, p1.y, p2.x, p2.y);
//             }
//         }

//         // target : center road lines (rectangle)
//         this.road_center.put("north", Arrays.asList(n1[1], n3[1]));
//         this.road_center.put("south", Arrays.asList(s1[1], s3[1]));
//         this.road_center.put("east", Arrays.asList(e1[1], e3[1]));
//         this.road_center.put("west", Arrays.asList(w1[1], w3[1]));

//         // target : get trafic position
//         this.trafic_place.put("north", new Point(n1[1].x - (trafic_gap * 3), n1[1].y - (trafic_gap * 3)));
//         this.trafic_place.put("south", new Point(s3[1].x + trafic_gap, s3[1].y + trafic_gap));
//         this.trafic_place.put("east", new Point(s1[1].x - (trafic_gap * 3), s1[1].y + trafic_gap));
//         this.trafic_place.put("west", new Point(n3[1].x + trafic_gap, n3[1].y - (trafic_gap * 3)));
//     }

//     private void trafic(Graphics g) {
//         final int size = (this.width * 6) / 100;

//         for (final Entry<String, Point> trafic : trafic_place.entrySet()) {
//             g.setColor(trafic.getKey().equals(directions[green_trafic_index]) ? Color.GREEN : Color.RED); // should change color by time or by length of cars in road !!!!
//             g.fillOval(trafic.getValue().x, trafic.getValue().y, size, size);
//         }
//     }

//     // implement this method in your class
//     private void updateTrafic() {
//         long now = System.currentTimeMillis();

//         // rotate every TRAFFIC_CHANGE_INTERVAL_MS
//         if (now - lastTrafficChangeTime >= TRAFFIC_CHANGE_INTERVAL_MS) {
//             // advance index and wrap
//             green_trafic_index = (green_trafic_index + 1) % directions.length;

//             // optional: reset last change time
//             lastTrafficChangeTime = now;
//         }
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         g.setColor(Color.WHITE);

//         g.drawString("Press <ESCAPE> to exit the app", 20, 20);
//         g.drawString("Press <SPACE> to add viehcle.", 20, 40);
        
//         roads(g);

//         updateTrafic();

//         trafic(g);

//         // draw cars

//         // System.out.println("paint:-----");
//         repaint();
//     }
// }

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public final class JrafficCanvas extends JPanel {

    private Timer animation;
    // public int width, heigth;

    private final Roads roads = new Roads();
    private final Traffic traffic = new Traffic();

    public JrafficCanvas() {
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // add vehicles here !!!
                }
            }
        });

        startAnimation();
    }

    @Override
    public void setSize(int width, int heigth) {
        // this.width = width;
        // this.heigth = heigth;
        super.setSize(width, heigth);
    }

    public void startAnimation() {
        if (animation != null && animation.isRunning()) return;
        animation = new Timer(16, e -> repaint()); // ~60 FPS
        animation.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawString("Press <ESCAPE> to exit the app", 20, 20);
        g.drawString("Press <SPACE> to add vehicle.", 20, 40);

        roads.draw(g, getWidth(), getHeight());
        traffic.setTrafficPositions(roads.getTrafficPlace());

        // update and draw traffic
        traffic.update();
        traffic.draw(g, getWidth());
    }
}