import java.util.Arrays;

public class Piece {

    int x;
    int y;
    Integer[][] microBlocks = new Integer[4][4];
    Integer type;
    Integer rotation; // 0, 1, 2, 3

    void paint() { // To call after clearing all blocks
        // Add all microBlocks to squares
      
        for (int x=0;x<4;x++) {
            for (int y=0;y<4;y++) { // Run every Y of all the rows
                Integer cell = this.microBlocks[x][y];
                if (cell!=0) {
                    GameState.blocks.add(new Block(
                        this.x + x*Config.BLOCK_SIZE, 
                        this.y + y*Config.BLOCK_SIZE, 
                        Config.BLOCK_SIZE, 
                        Config.BLOCK_SIZE, 
                        Config.COLORS[cell]));
                }
            }
        }
    } // end function paint()

    void setDesign() {

        // Fill microblocks
        for (Integer[] row : this.microBlocks) {
            Arrays.fill(row, 0);
        }

        if (this.type.equals(0)) { // Square
            microBlocks[0][0] = 4;
            microBlocks[1][0] = 4;
            microBlocks[0][1] = 4;
            microBlocks[1][1] = 4;
        }

        if (this.type.equals(1)) { // L

            switch (this.rotation) {

                case 0:
                    microBlocks[0][0] = 1;
                    microBlocks[0][1] = 1;
                    microBlocks[0][2] = 1;
                    microBlocks[1][2] = 1;
                    break;

                case 1:
                    microBlocks[0][1] = 1;
                    microBlocks[0][0] = 1;
                    microBlocks[1][0] = 1;
                    microBlocks[2][0] = 1;
                    break;

                case 2:
                    microBlocks[1][0] = 1;
                    microBlocks[2][0] = 1;
                    microBlocks[2][1] = 1;
                    microBlocks[2][2] = 1;
                    break;

                case 3:
                    microBlocks[0][2] = 1;
                    microBlocks[1][2] = 1;
                    microBlocks[2][2] = 1;
                    microBlocks[2][1] = 1;
                    break;

            } 
        }// Fin L

        if (this.type.equals(2)) { // L Invertida

            switch (this.rotation) {

                case 0:
                    microBlocks[2][0] = 2;
                    microBlocks[2][1] = 2;
                    microBlocks[2][2] = 2;
                    microBlocks[1][2] = 2;
                    break;

                case 1:
                    microBlocks[2][2] = 2;
                    microBlocks[1][2] = 2;
                    microBlocks[0][2] = 2;
                    microBlocks[0][1] = 2;
                    break;

                case 2:
                    microBlocks[0][0] = 2;
                    microBlocks[1][0] = 2;
                    microBlocks[0][1] = 2;
                    microBlocks[0][2] = 2;
                    break;

                case 3:
                    microBlocks[0][0] = 2;
                    microBlocks[1][0] = 2;
                    microBlocks[2][0] = 2;
                    microBlocks[2][1] = 2;
                    break;

            }
        } // Fin L Invertida

        if (this.type.equals(3)) { // I

            switch (this.rotation) {

                case 0:
                    microBlocks[0][0] = 3;
                    microBlocks[0][1] = 3;
                    microBlocks[0][2] = 3;
                    microBlocks[0][3] = 3;
                    break;

                case 1:
                    microBlocks[0][0] = 3;
                    microBlocks[1][0] = 3;
                    microBlocks[2][0] = 3;
                    microBlocks[3][0] = 3;
                    break;

                case 2:
                    microBlocks[0][0] = 3;
                    microBlocks[0][1] = 3;
                    microBlocks[0][2] = 3;
                    microBlocks[0][3] = 3;
                    break;

                case 3:
                    microBlocks[0][0] = 3;
                    microBlocks[1][0] = 3;
                    microBlocks[2][0] = 3;
                    microBlocks[3][0] = 3;
                    break;

            }
        } // Fin I

        if (this.type.equals(4)) { // Z

            switch (this.rotation) {

                case 0:
                    microBlocks[0][1] = 5;
                    microBlocks[1][1] = 5;
                    microBlocks[1][0] = 5;
                    microBlocks[2][0] = 5;
                    break;

                case 1:
                    microBlocks[0][0] = 5;
                    microBlocks[0][1] = 5;
                    microBlocks[1][1] = 5;
                    microBlocks[1][2] = 5;
                    break;

                case 2:
                    microBlocks[0][1] = 5;
                    microBlocks[1][1] = 5;
                    microBlocks[1][0] = 5;
                    microBlocks[2][0] = 5;
                    break;

                case 3:
                    microBlocks[0][0] = 5;
                    microBlocks[0][1] = 5;
                    microBlocks[1][1] = 5;
                    microBlocks[1][2] = 5;
                    break;

            }
        } // Fin Z

        if (this.type.equals(5)) { // Z Invertida

            switch (this.rotation) {

                case 0:
                    microBlocks[0][0] = 6;
                    microBlocks[1][0] = 6;
                    microBlocks[1][1] = 6;
                    microBlocks[2][1] = 6;
                    break;

                case 1:
                    microBlocks[1][0] = 6;
                    microBlocks[1][1] = 6;
                    microBlocks[0][1] = 6;
                    microBlocks[0][2] = 6;
                    break;

                case 2:
                    microBlocks[0][0] = 6;
                    microBlocks[1][0] = 6;
                    microBlocks[1][1] = 6;
                    microBlocks[2][1] = 6;
                    break;

                case 3:
                    microBlocks[1][0] = 6;
                    microBlocks[1][1] = 6;
                    microBlocks[0][1] = 6;
                    microBlocks[0][2] = 6;
                    break;

            } 

        } // Fin Z Invertida

        if (this.type.equals(6)) { // T

            switch (this.rotation) {

                case 0:
                    microBlocks[0][0] = 7;
                    microBlocks[1][0] = 7;
                    microBlocks[2][0] = 7;
                    microBlocks[1][1] = 7;
                    break;

                case 1:
                    microBlocks[1][0] = 7;
                    microBlocks[1][1] = 7;
                    microBlocks[1][2] = 7;
                    microBlocks[0][1] = 7;
                    break;

                case 2:
                    microBlocks[1][0] = 7;
                    microBlocks[0][1] = 7;
                    microBlocks[1][1] = 7;
                    microBlocks[2][1] = 7;
                    break;

                case 3:
                    microBlocks[0][0] = 7;
                    microBlocks[0][1] = 7;
                    microBlocks[0][2] = 7;
                    microBlocks[1][1] = 7;
                    break;

            } 

        } // Fin T

    } // End set function

    void rotate() {

        // Check if it's possible to rotate
        // Use the function from map and create a new piece with the new position
        Piece temporalPiece = new Piece();
        temporalPiece.x = this.x;
        temporalPiece.y = this.y;
        temporalPiece.type = this.type;
        temporalPiece.rotation = (this.rotation.equals(3)) ? 0 : this.rotation+1;
        temporalPiece.setDesign();

        if (GameState.level.canMove(temporalPiece, 0, 0)) { // Check if piece in next rotation position is ok 
            this.rotation++;
            if (this.rotation.equals(4))
                this.rotation = 0;

            this.setDesign();
        }


    }

    void set(Integer type) { // Reset the piece and set type
        this.x = (Config.LEVEL_WIDTH * Config.BLOCK_SIZE) / 2 + Config.LEVEL_MARGIN_LEFT;
        this.y = Config.LEVEL_MARGIN_TOP;

        this.rotation = 0;

        this.type = type;
        this.setDesign();

    }

    boolean fall() {

        this.y += Config.BLOCK_SIZE;

        return true;
    }

    void move(int x, int y) { // Relative values to blocks
        this.x += x * Config.BLOCK_SIZE;
        this.y += y * Config.BLOCK_SIZE;
    }

}