import java.util.Arrays;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class JavaTetris {

     public static void main(String[] args) throws Exception {

        // Start of program

        // Configure main window
        GameState.window.setTitle("Java Tetris");
        GameState.window.setVisible(true);
        GameState.window.getContentPane().add(GameState.window.g);

        // Game loop
        final long fpsMs = 1000 / Config.FPS;
        long startLoopMs;
        long endLoopMs;

        while (true) { // Game loop start
            /**
             * Start game loop
             */

            startLoopMs = System.currentTimeMillis();
            GameState.currentFrame++;

            switch (GameState.state) {
                case MAINMENU:
                    /**
                     * Main menu
                     */
           

                        if (GameState.texts.isEmpty()) { // Initialize components

                            GameState.texts.add(new Text("TETRIS!", 0, 200, new Color(255, 128, 0), 120, true));

                            GameState.texts.add(new Text("Start", 0, 300, Color.white, 40, true));
                            GameState.texts.add(new Text("Quit", 0, 350, Color.white, 40, true));

                            GameState.texts.add(new Text("Game developed by Manu Gimenez :) github.com/manuelzlp", Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT - 100, Color.white, 18, false));

                            GameState.texts.add(new Text("High score: " + GameState.highScore, 0, 420, Color.white, 25, true));

                            GameState.gameValues.add(1); // Holds the selected option
                            GameState.gameValues.add(Config.WINDOW_WIDTH); // Holds the marquee X value

                        }

                        Integer marqueeX = GameState.gameValues.get(1);

                        marqueeX -= 1;

                        if (marqueeX < -800)
                            marqueeX = Config.WINDOW_WIDTH;

                        GameState.texts.get(3).x = marqueeX;
                        GameState.gameValues.set(1, marqueeX);
                        
                        Integer currentOption = GameState.gameValues.get(0);

                        GameState.texts.get(1).color = Color.white;
                        GameState.texts.get(2).color = Color.white;
                        GameState.texts.get(currentOption).color = new Color(255, 128, 0); // Highlight selected option

                        // Keyboard
    
                        if (GameState.keys[KeyEvent.VK_DOWN])
                            currentOption++;
                        if (GameState.keys[KeyEvent.VK_UP])
                            currentOption--;

                        if (currentOption.equals(0))
                            currentOption = 2;

                        if (currentOption.equals(3))
                            currentOption = 1;

                        GameState.gameValues.set(0, currentOption);

                        if (GameState.keys[KeyEvent.VK_ENTER]) {
                            if (currentOption.equals(1)) {
                                GameState.clearScene();
                                GameState.state = Config.GameStates.GAMEPLAY;
                            } else {
                                System.exit(0);
                            }
                        }

                    break;

                case GAMEPLAY:
                    /**
                     * Gameplay
                     */

                        if (GameState.texts.isEmpty()) {
                            // Declarations
                            GameState.texts.add(new Text("Next piece:", 600, 50, Color.white, 20, false));
                            int rnd = (int) (Math.random() * 7);
                            GameState.currentPiece.set(rnd);
                            rnd = (int) (Math.random() * 7);
                            GameState.nextPiece.set(rnd);
                            GameState.nextPiece.x = 620;
                            GameState.nextPiece.y = 90;
                            GameState.gameValues.add(GameState.currentFrame); // Store frame from last falling movement
                            Difficulty.setSpeed(30); // Current game speed. 30 == easy,  0 == impossible
                            GameState.currentScore = 0;
                            GameState.texts.add(new Text("Score", 25, 50, Color.white, 20, false));
                            GameState.texts.add(new Text("0", 25, 90, new Color(255, 128, 0), 23, false));

                            // Instructions
                            GameState.texts.add(new Text("Left, Right, Down:", 25, 200, Color.white, 20, false));
                            GameState.texts.add(new Text("Move the piece", 25, 230, new Color(255, 128, 0), 20, false));
                            GameState.texts.add(new Text("Spacebar:", 25, 300, Color.white, 20, false));
                            GameState.texts.add(new Text("Rotate the piece", 25, 330, new Color(255, 128, 0), 20, false));
                            GameState.texts.add(new Text("Escape:", 25, 400, Color.white, 20, false));
                            GameState.texts.add(new Text("Exit the game", 25, 430, new Color(255, 128, 0), 20, false));
                            
                        }

                        // CHECK - INCREASE DIFFICULTY
                        Difficulty.check();

                        // BLOCK FALLING
                        if (GameState.gameValues.get(0) + Difficulty.currentSpeed < GameState.currentFrame) {
                            
                            // Check if it can me moved down
                            if (GameState.level.canMove(GameState.currentPiece, 0, 1)) {
                                GameState.currentPiece.fall();
                                GameState.gameValues.set(0, GameState.currentFrame); // Reset timer
                            } else {
                                GameState.level.add(GameState.currentPiece); // Put piece on the level
                                GameState.currentScore += Config.POINTSPERPIECE; // Add points to highscore
                                GameState.level.checkBreaks(); // Check for line breaks

                                GameState.currentPiece.set(GameState.nextPiece.type); // Current piece is now the next piece
                               
                                int rnd = (int) (Math.random() * 7);
                                GameState.nextPiece.set(rnd);
                                GameState.nextPiece.x = 620;
                                GameState.nextPiece.y = 90;
                                GameState.gameValues.set(0, GameState.currentFrame); // Reset timer
                            }
                        }

                        // KEYS
                        if (GameState.keys[KeyEvent.VK_LEFT]) {
                            if (GameState.level.canMove(GameState.currentPiece, -1, 0))
                                GameState.currentPiece.move(-1, 0);
                        } 
                        
                        if (GameState.keys[KeyEvent.VK_RIGHT]) {
                            if (GameState.level.canMove(GameState.currentPiece, 1, 0))
                                GameState.currentPiece.move(1, 0);
                        } 
                        
                        if (GameState.keys[KeyEvent.VK_DOWN]) {
                            if (GameState.level.canMove(GameState.currentPiece, 0, 1))
                               GameState.currentPiece.move(0, 1);
                        } 
                        
                        if (GameState.keys[KeyEvent.VK_SPACE]) {
                            GameState.currentPiece.rotate();
                        }

                        // update current score
                        GameState.texts.get(2).text = GameState.currentScore.toString();

                        // recalculate blocks
                        GameState.blocks.clear();
                        GameState.level.paint();
                        GameState.currentPiece.paint();
                        GameState.nextPiece.paint();

                        // Check is the piece can move
                        if (!GameState.level.canMove(GameState.currentPiece, 0, 0)) {
                            // Game over!
                            GameState.state = Config.GameStates.GAMEOVER;

                            // Check if it's a new high score
                            if (GameState.currentScore > GameState.highScore)
                                GameState.highScore = GameState.currentScore;

                            if (Config.DEBUG)
                                System.out.println("Game over!");
                        }

                    break;

                case GAMEOVER:
                    /**
                     * Game over
                     */
                        if (GameState.texts.size() < 10) { // Initialize
                            GameState.texts.add(new Text("Game Over!!", -6, 294, Color.black, 100, true));
                            GameState.texts.add(new Text("Game Over!!", -3, 297, Color.red, 100, true));
                            GameState.texts.add(new Text("Game Over!!", 0, 300, Color.white, 100, true));

                            GameState.texts.add(new Text("Press", 630, 400, Color.white, 25, false));
                            GameState.texts.add(new Text("ENTER", 625, 440, new Color(255, 128, 0), 25, false));
                            GameState.texts.add(new Text("to try again!!", 600, 480, Color.white, 25, false));
                        }

                        if (GameState.keys[KeyEvent.VK_ENTER]) {
                            GameState.clearScene();
                            GameState.level = new Map();
                            GameState.state = Config.GameStates.MAINMENU;
                        }

                    break;
            } // End game switch

            // Check ESC key to end the game at any moment
            if (GameState.keys[27]) // Escape == quit
                System.exit(0);

            // Clear all keys on this loop
            Arrays.fill(GameState.keys, false);
           
            GameState.window.g.repaint();
            endLoopMs = System.currentTimeMillis() - startLoopMs;

            // If faster than expected then wait
            if (endLoopMs < fpsMs)
                Thread.sleep(fpsMs - endLoopMs);

            /**
             * End game loop
             */
        } // Game loop end


    }
}
