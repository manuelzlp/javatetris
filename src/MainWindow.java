import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.awt.Rectangle;

public class MainWindow extends JFrame implements KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 2L;

    public Drawing g;

    public void keyPressed(KeyEvent e) {

        int k = e.getKeyCode();

        if (GameState.keyLastFrame[k] == null)
        GameState.keyLastFrame[k] = -GameState.keyboardDelay; // First time pressed

        if (GameState.keyLastFrame[k] + GameState.keyboardDelay <= GameState.currentFrame) {
            GameState.keys[k] = true;
            GameState.keyLastFrame[k] = GameState.currentFrame;
        } else {
            GameState.keys[k] = false;
        }

    }

    public void keyReleased(KeyEvent e) {
        GameState.keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {

    }

    public MainWindow() {
        this.g = new Drawing();
        this.setBounds(new Rectangle(800, 600));
        this.setResizable(false);
        this.setLocationRelativeTo(null);        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
}