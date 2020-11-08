import java.awt.Color;

public class Config {

    final static boolean DEBUG = true; // Set debug mode
    final static Integer WINDOW_WIDTH = 800;
    final static Integer WINDOW_HEIGHT = 600;    
    final static Integer BLOCK_SIZE = 25; // Number of pixels of the block width and height
    final static Integer LEVEL_WIDTH = 12; // Width of the level counted in blocks
    final static Integer LEVEL_HEIGHT = 20; // Height of the level counted in blocks
    final static Integer LEVEL_MARGIN_LEFT = 250; // Pixels of margin to the left of the game square
    final static Integer LEVEL_MARGIN_TOP = 20; // Pixels of margin at top of game square
    final static Integer FPS = 60;
    final static Integer DIFFICULTY = 300; // After how many frames the game will get faster. 300 = 5 segundos
    final static Integer POINTSPERPIECE = 10;
    final static Integer POINTSPERLINE = 100;

    final static Color[] COLORS = {
        Color.black,
        Color.red,
        Color.green,
        Color.blue,
        new Color(255, 255, 0),
        new Color(255, 51, 178),
        Color.cyan,
        new Color(255, 128, 0)
    };

    enum GameStates {
        MAINMENU, GAMEPLAY, GAMEOVER
    }
    
}
