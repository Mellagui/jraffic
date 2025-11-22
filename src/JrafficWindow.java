import javax.swing.*;

public class JrafficWindow {
    
    public JrafficWindow() {
        JFrame frame = new JFrame("Jaikin - Chaikin's Algorithm");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000, 1000);

        JrafficCanvas canvas = new JrafficCanvas();
        canvas.setSize(1000, 1000);
        frame.add(canvas);

        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
    }
}
