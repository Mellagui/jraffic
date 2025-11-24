
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
    private final Cars cars = new Cars();
    private Intersection intersection;

    public JrafficCanvas() {
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    cars.add("east", roads.getRoadCenter(),getWidth());
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    cars.add("south",roads.getRoadCenter(),getWidth());
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    cars.add("west", roads.getRoadCenter(),getWidth());
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    cars.add("north",roads.getRoadCenter(), getWidth());
                }
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    cars.random(roads.getRoadCenter(), getWidth());
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

        if (intersection == null) {
            intersection = new Intersection(roads.getRoadCenter());
        }

        // update and draw traffic
        traffic.update();
        traffic.draw(g, getWidth());
        if (intersection != null) {
            cars.update(roads.getRoadCenter(), getWidth(), traffic, intersection);
        }
        cars.draw(g, getWidth());

        if (intersection != null) {
            g.setColor(Color.CYAN);
            g.drawRect(intersection.getBounds().x, intersection.getBounds().y, intersection.getBounds().width, intersection.getBounds().height);
        }
    }
}

