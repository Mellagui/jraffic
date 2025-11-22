<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
=======
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
>>>>>>> 74484fa (add roads, ta l reda)

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
<<<<<<< HEAD
=======

                // Press SPACE
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                }

                // Press ESC to exit the app
>>>>>>> 74484fa (add roads, ta l reda)
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

    public void roads(Graphics g) {
        
        List<List<Point[]>> roads_directions = new ArrayList<>();

        final int middle_w = this.width / 2;
        final int middle_h = this.heigth / 2;
        final int road_w = (this.width * 12)/100;
        final int road_h = (this.width * 12)/100;
        
        List<Point[]> north = new ArrayList<>();
        Point[] n1 = {new Point(middle_w - road_w, 0), new Point(middle_w - road_w, middle_h - road_h)}; north.add(n1); //
        Point[] n2 = {new Point(middle_w, 0), new Point(middle_w, middle_h - road_h)}; north.add(n2);
        Point[] n3 = {new Point(middle_w + road_w, 0), new Point(middle_w + road_w, middle_h - road_h)}; north.add(n3); //
        roads_directions.add(north);
        this.road_center.put("north", Arrays.asList(n1[1], n3[1]));
        
        List<Point[]> south = new ArrayList<>();
        Point[] s1 = {new Point(middle_w - road_w, middle_h * 2), new Point(middle_w - road_w, middle_h + road_h)}; south.add(s1); //
        Point[] s2 = {new Point(middle_w, middle_h * 2), new Point(middle_w, middle_h + road_h)}; south.add(s2);
        Point[] s3 = {new Point(middle_w + road_w, middle_h * 2), new Point(middle_w + road_w, middle_h + road_h)}; south.add(s3); //
        roads_directions.add(south);
        this.road_center.put("south", Arrays.asList(s1[1], s3[1]));
        
        List<Point[]> east = new ArrayList<>();
        Point[] e1 = {new Point(0, middle_h - road_h), new Point(middle_w - road_w, middle_h - road_h)}; north.add(e1); //
        Point[] e2 = {new Point(0, middle_h), new Point(middle_w - road_w, middle_h)}; east.add(e2);
        Point[] e3 = {new Point(0, middle_h + road_h), new Point(middle_w - road_w, middle_h + road_h)}; east.add(e3); //
        roads_directions.add(east);
        this.road_center.put("east", Arrays.asList(e1[1], e3[1]));

        List<Point[]> west = new ArrayList<>();
        Point[] w1 = {new Point(middle_w * 2, middle_h - road_h), new Point(middle_w + road_w, middle_h - road_h)}; west.add(w1); //
        Point[] w2 = {new Point(middle_w * 2, middle_h), new Point(middle_w + road_w, middle_h)}; west.add(w2);
        Point[] w3 = {new Point(middle_w * 2, middle_h + road_h), new Point(middle_w + road_w, middle_h + road_h)}; west.add(w3); //
        roads_directions.add(west);
        this.road_center.put("west", Arrays.asList(w1[1], w3[1]));

        for (List<Point[]> dir : roads_directions) {
            for (Point[] line : dir) {
                Point p1 = line[0];
                Point p2 = line[1];
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawString("Press <ESCAPE> to exit the app", 20, 20);
        g.drawString("Press <SPACE> to add vehicle.", 20, 40);

        roads.draw(g, getWidth(), getHeight());
        traffic.setTrafficPositions(roads.getTrafficPlace());

<<<<<<< HEAD
        // update and draw traffic
        traffic.update();
        traffic.draw(g, getWidth());
=======
        roads(g);

        // Draw the first and last points filled to distinguish them
        // if (points.size() > 0) {
        //     Point firstPoint = points.get(0);
        //     g.fillOval(firstPoint.x - 3, firstPoint.y - 3, pointSize, pointSize);
        // }

        // if (points.size() >= 2) {
        //     Point lastPoint = points.get(points.size() - 1);
        //     g.fillOval(lastPoint.x - 3, lastPoint.y - 3, pointSize, pointSize);
        // }



        // --- DRAW LINES / ANIMATION ---
        // if (runAnimation) {
        //     // Display current step
        //     g.drawString(String.format("Step number: %s", currentStep), 20, 20);

        //     // Draw smooth polyline during animation
        //     for (int i = 0; i < points.size() - 1; i++) {
        //         Point p1 = points.get(i);
        //         Point p2 = points.get(i + 1);
        //         g.drawLine(p1.x, p1.y, p2.x, p2.y);
        //     }

        // } else {

        //     // Draw simple line when ENTER is pressed with 2 points
        //     if (drawLines) {
        //         for (int i = 0; i < points.size() - 1; i++) {
        //             Point p1 = points.get(i);
        //             Point p2 = points.get(i + 1);
        //             g.drawLine(p1.x, p1.y, p2.x, p2.y);
        //         }
        //     }
        // }
>>>>>>> 74484fa (add roads, ta l reda)
    }
}
