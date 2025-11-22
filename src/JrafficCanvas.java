import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class JrafficCanvas extends JPanel {

    private Timer animation;

    // window size
    public int width, heigth;

    // rectangle : 4 points represant center of roads
    public Map<String, List<Point>> road_center = new HashMap<>();

    @Override
    public void setSize(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    public JrafficCanvas() {
        setBackground(Color.BLACK);
        setFocusable(true);

        // Keyboard handling
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                // Press ENTER
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                }

                // Press SPACE
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                }

                // Press ESC to exit the app
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

                startAnimation();
                repaint();
            }
        });
    }

    public void startAnimation() {
        animation = new Timer(16, e -> {
            // call the logic !!!!!!

            // update();

            repaint();
        });
        animation.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        g.drawString("You must create minimum 3 points to run Chaikin algorithm.", 20, 20);
        g.drawString("Press <Space> to restart.", 20, 40);

        Roads roads = new Roads();
        roads.roads(g);

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
    }
}
