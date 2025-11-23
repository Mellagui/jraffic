import javax.swing.JFrame;

public class JrafficWindow {
    
    public JrafficWindow() {
        final JFrame frame = new JFrame("Jaikin - Chaikin's Algorithm");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000, 1000);

        final JrafficCanvas canvas = new JrafficCanvas();
        canvas.setSize(1000, 1000);
        frame.add(canvas);

        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
    }
}
