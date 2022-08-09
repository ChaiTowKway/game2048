/**
 * This is the View class which display results to user
 */

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    public static final int WIDTH = 374;
    public static final int HEIGHT = 460;

    public GameWindow() {
        //create and set up the window frame
        super("Welcome to Game 2048!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout( ));

        setFocusable(true); // enable Keylistener

        add(new GamePanel()); //add a Panel object to window
        setVisible(true);
    }

    /**
     * return the width of the window
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * return the height of the window
     */
    public int getHeight() {
        return HEIGHT;
    }



}
