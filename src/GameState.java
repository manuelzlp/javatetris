import java.util.List;
import java.util.ArrayList;

public class GameState {
    
    public static Config.GameStates state = Config.GameStates.MAINMENU; // Start at Main Menu

    // Lists containing graphical elements
    public static List<Block> blocks = new ArrayList<Block>();
    public static List<Text> texts = new ArrayList<Text>();

    // Dynamic values collection
    public static List<Integer> gameValues = new ArrayList<Integer>();

    // Containers
    public static Map level = new Map();
    public static MainWindow window = new MainWindow();
    
    // Keyboard information
    public static boolean[] keys = new boolean[256];
    public static Integer[] keyLastFrame = new Integer[256];
    public static Integer currentFrame = 0;
    public static Integer keyboardDelay = 5;

    // Game High score
    public static Integer highScore = 0;
    public static Integer currentScore = 0;

    // Tetris pieces
    public static Piece currentPiece = new Piece();
    public static Piece nextPiece = new Piece();

    public static void clearScene() {
        if (!texts.isEmpty())
            texts.clear();

        if (!blocks.isEmpty())
            blocks.clear();

        if (!gameValues.isEmpty())
            gameValues.clear();

    }

}
