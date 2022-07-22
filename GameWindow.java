package finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayDeque;

public class GameWindow extends JFrame{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private JButton[] buttons;
    private JLabel label;

    public GameWindow() {
        super("2048");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout( ));
        label = new JLabel("Game Board");
        JPanel gameBoard = new JPanel();
        add(label, BorderLayout.NORTH);
        add(gameBoard, BorderLayout.CENTER);
        gameBoard.setLayout(new GridLayout(4, 4));
    }
//
//    public static void main(String[] args) {
//        GameWindow gameWindow = new GameWindow();
//        gameWindow.setVisible(true);
//    }

}
