
import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame{

    public static final int WIDTH = 374;
    public static final int HEIGHT = 460;

    public GameWindow() {
        super("Welcome to Game 2048!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout( ));

        setFocusable(true); // enable Keylistener

        add(new GamePanel());
        setVisible(true);
    }
    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }


    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        System.out.println(gameWindow.getHeight());
    }

}
