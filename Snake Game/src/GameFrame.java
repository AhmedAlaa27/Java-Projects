import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        ImageIcon icon = new ImageIcon("E:\\My Career\\Courses\\JAVA\\Projects\\Snake Game\\snake.png");
        this.add(new GamePanel());
        this.setTitle("Snake Game");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
