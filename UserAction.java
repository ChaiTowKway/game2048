import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserAction implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        int keyVal = e.getKeyCode();

        switch (keyVal) {
            case KeyEvent.VK_UP:
                GameTrigger.GAME_LOGIC.shiftUp();
                break;
            case KeyEvent.VK_DOWN:
                GameTrigger.GAME_LOGIC.shiftDown();
                break;
            case KeyEvent.VK_LEFT:
                GameTrigger.GAME_LOGIC.shiftTowardsLeft();
                break;
            case KeyEvent.VK_RIGHT:
                GameTrigger.GAME_LOGIC.shiftTowardsRight();
                break;
            default:
                break;
        }

        GameTrigger.GAME_LOGIC.isGameOver();
        GameTrigger.GAME_WINDOW.repaint(); //will call on paint method with param Graphics

    }

    public void invokeKey() {
        GameTrigger.GAME_WINDOW.addKeyListener(this);
    }

    public void closeKey() {
        GameTrigger.GAME_WINDOW.removeKeyListener(this);
    }
}
