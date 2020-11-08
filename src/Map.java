import java.awt.Color;
import java.util.Arrays;

public class Map {

    Integer[][] blocksMap = new Integer[Config.LEVEL_WIDTH][Config.LEVEL_HEIGHT];

    Map() {
        // Fill map
        for (Integer[] row : this.blocksMap) {
            Arrays.fill(row, 0);
        }
    }

    public void paint() { // Call this function after clearing all blocks
        GameState.blocks.add(new Block(250, 20, 300, 500, new Color(40, 40, 40))); // Draw background of map
       
        for (int x=0;x<Config.LEVEL_WIDTH;x++) {
            for (int y=0;y<Config.LEVEL_HEIGHT;y++) { // Run every Y of all the rows
                Integer cell = this.blocksMap[x][y];
                if (cell!=0) {
                    GameState.blocks.add(
                        new Block(x*Config.BLOCK_SIZE+Config.LEVEL_MARGIN_LEFT,
                        y*Config.BLOCK_SIZE+Config.LEVEL_MARGIN_TOP,
                        Config.BLOCK_SIZE,
                        Config.BLOCK_SIZE,
                        Config.COLORS[cell])
                        );
                }
            }
        }

    } // End function paint()

    public void checkBreaks() throws InterruptedException {
        // Check for break lines
        boolean breakLine = true;

        // We check the breaks from bottom to top
        for (int y=Config.LEVEL_HEIGHT-1;y>-1;y--) {
            breakLine = true;
            for (int x=Config.LEVEL_WIDTH-1;x>-1;x--) {
                if (this.blocksMap[x][y].equals(0)) { // If any value is 0, then the row is not filled. Then break the loop
                    breakLine = false;
                    break; // Quit the loop
                }
             }

            // Line already checked. If breakLine is true, then cut one line and breakt the loop to call the function again
            if (breakLine) {
                // Set all blocks as black, paint and wait
                for (int x=0;x<12;x++)
                    this.blocksMap[x][y] = 0;
                
                GameState.blocks.clear();
                GameState.level.paint();
                GameState.window.repaint();
                Thread.sleep(250);

                // Swap rest of rows down
                int restOfRows = y - 1;

                for (int y2=restOfRows;y2>-1;y2--) {
                    // Repaint every row
                    for (int x=0;x<12;x++)
                        this.blocksMap[x][y2+1] = this.blocksMap[x][y2]; // Copy current row into next one
                    for (int x=0;x<12;x++)
                        this.blocksMap[x][y2] = 0; // Delete current row
                }

                GameState.blocks.clear();
                GameState.level.paint();
                GameState.window.repaint();
                GameState.currentScore += Config.POINTSPERLINE; // Add points to highscore
                Thread.sleep(250); // Repaint in case of coming back

                // Stop loop
                break;
            }
        }

        if (breakLine)
            this.checkBreaks(); // We check again for new line breaks

    } // End checkBreaks

    public Integer whatsOn(int x, int y) {
        return this.blocksMap[x][y];
    }

    public void putOn(int x, int y, Integer what) {
        this.blocksMap[x][y] = what;
    }

    boolean canMove(Piece piece, int movX, int movY) {

        int initX = (piece.x - Config.LEVEL_MARGIN_LEFT) / Config.BLOCK_SIZE + movX; // Already consider the movement inside the map
        int initY = (piece.y - Config.LEVEL_MARGIN_TOP) / Config.BLOCK_SIZE + movY; 

        boolean occupied = false;

        for (int x=0;x<piece.microBlocks.length;x++) { // Loop over all rows of microblocks
            for (int y=0;y<piece.microBlocks[x].length;y++) {
                int cell = piece.microBlocks[x][y];

                if (cell!=0) { // Check if cell is not empty

                    int newX = initX + x;
                    int newY = initY + y;

                    if (newX < 0 || newX > Config.LEVEL_WIDTH - 1 || newY > Config.LEVEL_HEIGHT - 1) { // Check if it's out of bounds
                        occupied = true;
                        break;
                    }

                    if (this.whatsOn(newX, newY) != 0) { // Check if it's empty
                        occupied = true;
                        break;
                    }
                }
            }
            
            if (occupied)
                break;
        }

       return !occupied; 
    }

    void add(Piece piece) { // Add piece to level

        int pieceX = (piece.x - Config.LEVEL_MARGIN_LEFT) / Config.BLOCK_SIZE;
        int pieceY = (piece.y - Config.LEVEL_MARGIN_TOP) / Config.BLOCK_SIZE;

        for (int x=0;x<piece.microBlocks.length;x++) { // Loop over all rows of microblocks
            for (int y=0;y<piece.microBlocks[x].length;y++) {
                int cell = piece.microBlocks[x][y];

                if (cell!=0) {
                    this.putOn(pieceX+x, pieceY+y, cell);
                }
            }
        }

    }

}