import javax.swing.*;
import java.awt.*;

public class PongFrame extends JFrame {
    ImageIcon icon = new ImageIcon("E:\\My Career\\Courses\\JAVA\\Projects\\Pong Game\\ping-pong.png");

    PongFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new PongPanel());
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setIconImage(icon.getImage());
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
