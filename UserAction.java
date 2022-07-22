package finalProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAction implements ActionListener {

    private JButton buttonUp;
    private JButton buttonDown;
    private JButton buttonLeft;
    private JButton buttonRight;

    public UserAction() {
        buttonUp = new JButton();
        buttonUp.setActionCommand("UP");
        buttonUp.addActionListener(this);

        buttonDown = new JButton();
        buttonDown.setActionCommand("DOWN");
        buttonDown.addActionListener(this);

        buttonLeft = new JButton();
        buttonLeft.setActionCommand("LEFT");
        buttonLeft.addActionListener(this);

        buttonRight = new JButton();
        buttonRight.setActionCommand("RIGHT");
        buttonRight.addActionListener(this);
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String direction = e.getActionCommand();
        switch (direction) {
            case "UP":
                break;
            case "DOWN":
                break;
            case "LEFT":
                break;
            case "RIGHT":
                break;
        }
    }
}
