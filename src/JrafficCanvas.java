import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class JrafficCanvas extends JPanel {
    private final CarsManager cars = new CarsManager();
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
                    cars.spawn(getWidth(), getHeight(), traffic.getGreenDirection());
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
        animation = new Timer(16, e -> {
            cars.updateAll(traffic, getWidth(), getHeight()); // MOVE CARS HERE
            repaint(); // THEN DRAW
        });
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
        cars.drawAll(g);

    }
}
