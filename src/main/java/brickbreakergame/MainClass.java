package brickbreakergame;
import javax.swing.JFrame;

public class MainClass {
    public static void main(String [] args) {
    JFrame frame = new JFrame();
    GamePlay panel = new GamePlay();
    frame.setBounds(450,50,1000,900);
    frame.setTitle("Brick Breaker");
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    }

}
