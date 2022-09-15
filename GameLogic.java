import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

/**
 * CS5004 Summer 2022 Final Project - GAME 2048
 * Implement a Tile class to create tile objects on the game board
 * @author Qian Lang; Xiaodong Li
 * @version 0.0
 * 09 Aug 2022
 */
public class GameLogic {
    private int size;                   //size of the game board
    private int totalScore;             //total score achieved
    private int numOfZeroTiles;         //number of empty slots available on board
    private boolean gameOver;           //boolean variable to determine if game over
    private boolean winWith2048;        //boolean variable to determine if a player gets a tile with 2048
    private int maxValueOnTile;         //maximum value for a tile displayed on the board so far
    private boolean boardStatusChanged; //detemine if there is any status change on the board after the current user action
    private Tile[][] board;             //2D array game board to store tiles generated so far

    /**
     * non-argument constructor to create a new game board
     */
    public GameLogic() {
        size = 4;
        totalScore = 0;
        numOfZeroTiles = 16;
        gameOver = false;
        maxValueOnTile = 0;
        boardStatusChanged = true;
        board = new Tile[size][size];

        //two tiles would be initialized for a new game randomly
        for (int i = 0 ; i < 2; i++) {
            boardStatusChanged = true;
            generateRandomTile();
        }
        GameTrigger.USER_ACTION.invokeKey();
    }

    /**
     * generate a random tile with value 2 or 4 (80% : 20%) at an empty slot on game board
     */
    public void  generateRandomTile() {
        if (isGameOver() || !boardStatusChanged) {
            return;
        }

        int value = 0;
        if (Math.random() < 0.8) {
            value = 2;
        } else {
            value = 4;
        }
        int row = 0;
        int col = 0;
        Random random = new Random();
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (board[row][col] != null);
        board[row][col] = new Tile(value, new int[]{row, col});
        maxValueOnTile = Math.max(maxValueOnTile, value);
        numOfZeroTiles--;
        boardStatusChanged = false;
    }

    /**
     * return a list of tiles located on the given row, skipping all empty slots
     * @param row : int | row on 2D game board array
     * @return List<Tile> | a list of tiles located on the given row of the game board
     */
    public List<Tile> getTileValueListInRow(int row) {
        List<Tile> tileList = new ArrayList<>();
        for (int col = 0; col < size; col++) {
            if (board[row][col] != null) {
                tileList.add(board[row][col]);
            }
        }
        return tileList;
    }

    /**
     * return a list of tiles located on the given col, skipping all empty slots
     * @param col : int | col on 2D game board array
     * @return List<Tile> | a list of tiles located on the given col of the game board
     */
    public List<Tile> getTileValueListInCol(int col) {
        List<Tile> tileList = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            if (board[row][col] != null) {
                tileList.add(board[row][col]);
            }
        }
        return tileList;
    }

    /**
     * combine a list of tiles shifting towards left and up directions
     * @param tileList List<Tile> | a list of tiles located on the given row/col of the game board
     * @return List<Tile> | a list of tiles after merging two tiles with same value
     */
    public List<Tile> mergeTileList(List<Tile> tileList) {
        for (int i = 0; i < tileList.size() - 1; i++) {
            if (tileList.get(i) == null) {
                continue;
            }
            tileList = mergeTwoTiles(tileList, i, i + 1);
        }
        return tileList;
    }

    /**
     * combine a list of tiles shifting towards right and down directions
     * @param tileList List<Tile> | a list of tiles located on the given row/col of the game board
     * @return List<Tile> | a list of tiles after merging two tiles with same value
     */
    public List<Tile> mergeTileListReversed(List<Tile> tileList) {
        for (int i = tileList.size() - 1; i > 0; i--) {
            if (tileList.get(i) == null) {
                continue;
            }
            tileList = mergeTwoTiles(tileList, i, i - 1);
        }
        return tileList;
    }

    /**
     * merge a tile with adjacent tile which has the same value, and update all related instance variables
     * @param tileList List<Tile> | a list of tiles located on the given row/col of the game board
     * @param tileOne tile | first tile
     * @param tileTwo tile | second tile
     * @return List<Tile> | a list of tiles after merging
     */
    public List<Tile> mergeTwoTiles(List<Tile> tileList, int tileOne, int tileTwo) {
        int currentValue = tileList.get(tileOne).getValue();
        if (currentValue == tileList.get(tileTwo).getValue()) {
            tileList.get(tileOne).setValue(currentValue * 2);
            tileList.set(tileTwo, null);
            numOfZeroTiles++;
            totalScore += currentValue;
            maxValueOnTile = Math.max(maxValueOnTile, tileList.get(tileOne).getValue());
            boardStatusChanged = true;
            if (maxValueOnTile >= 2048) {
                winWith2048 = true;
                gameOver = true;
            }
        }
        return tileList;
    }

    /**
     * place tiles in the given col back to the game board from up to down direction
     * @param tileList List<Tile> | a list of tiles
     * @param col int | given col on the board
     */
    public void updateBoardInUpDirection(List<Tile> tileList, int col) {
        int tileAdded = 0;
        int index = 0;
        for (int row = 0; row < tileList.size(); row++) {
            Tile tile = tileList.get(row);
            if (tile == null) {
                continue;
            }
            if (tile.hasMoved(index, col)) {
                boardStatusChanged = true;
                tile.setPosition(new int[]{index, col});
                board[index][col] = tile;
            }

            index++;
            tileAdded++;
        }
        for (int i = tileAdded; i < size; i++) {
            board[index][col] = null;
            index++;
        }
    }

    /**
     * place tiles in the given col back to the game board from down to up direction
     * @param tileList List<Tile> | a list of tiles
     * @param col int | given col on the board
     */
    public void updateBoardInDownDirection(List<Tile> tileList, int col) {
        int tileAdded = 0;
        int index = size - 1;
        for (int row = tileList.size() - 1; row >= 0; row--) {
            Tile tile = tileList.get(row);
            if (tile == null) {
                continue;
            }
            if (tile.hasMoved(index, col)) {
                boardStatusChanged = true;
                tile.setPosition(new int[]{index, col});
                board[index][col] = tile;
            }

            index--;
            tileAdded++;
        }
        for (int i = tileAdded; i < size; i++) {
            board[index][col] = null;
            index--;
        }
    }

    /**
     * place tiles in the given row back to the game board from left to right direction
     * @param tileList List<Tile> | a list of tiles
     * @param row int | given row on the board
     */
    public void updateBoardInLeftDirection(List<Tile> tileList, int row) {
        int tileAdded = 0;
        int index = 0;
        for (int col = 0; col < tileList.size(); col++) {
            Tile tile = tileList.get(col);
            if (tile == null) {
                continue;
            }
            if (tile.hasMoved(row, index)) {
                boardStatusChanged = true;
                tile.setPosition(new int[]{row, index});
                board[row][index] = tile;
            }

            index ++;
            tileAdded++;
        }
        for (int i = tileAdded; i < size; i++) {
            board[row][index] = null;
            index ++;
        }
    }

    /**
     * place tiles in the given row back to the game board from right to left direction
     * @param tileList List<Tile> | a list of tiles
     * @param row int | given row on the board
     */
    public void updateBoardInRightDirection(List<Tile> tileList, int row) {
        int tileAdded = 0;
        int index = size - 1;
        for (int col = tileList.size() - 1; col >= 0; col--) {
            Tile tile = tileList.get(col);
            if (tile == null) {
                continue;
            }

            if (tile.hasMoved(row, index)) {
                boardStatusChanged = true;
                tile.setPosition(new int[]{row, index});
                board[row][index] = tile;
            }

            index--;
            tileAdded++;
        }
        for (int i = tileAdded; i < size; i++) {
            board[row][index] = null;
            index--;
        }
    }

    /**
     * slide tiles on the game board in upwards direction to merge two tiles with same value
     */
    public void shiftUp() {
        for (int col = 0; col < size; col++) {
            List<Tile> tileList = getTileValueListInCol(col);
            tileList = mergeTileList(tileList);
            updateBoardInUpDirection(tileList, col);
        }
        generateRandomTile();
    }

    /**
     * slide tiles on the game board in downwards direction to merge two tiles with same value
     */
    public void shiftDown() {
        for (int col = 0; col < size; col++) {
            List<Tile> tileList = getTileValueListInCol(col);
            tileList = mergeTileListReversed(tileList);
            updateBoardInDownDirection(tileList, col);
        }
        generateRandomTile();
    }

    /**
     * slide tiles on the game board in left direction to merge two tiles with same value
     */
    public void shiftTowardsLeft() {
        for (int row = 0; row < size; row++) {
            List<Tile> tileList = getTileValueListInRow(row);
            tileList = mergeTileList(tileList);
            updateBoardInLeftDirection(tileList, row);
        }
        generateRandomTile();
    }

    /**
     * slide tiles on the game board in right direction to merge two tiles with same value
     */
    public void shiftTowardsRight(){
        for (int row = 0; row < size; row++) {
            List<Tile> tileList = getTileValueListInRow(row);
            tileList = mergeTileListReversed(tileList);
            updateBoardInRightDirection(tileList, row);
        }
        generateRandomTile();
    }

    /**
     * check if there are any two tiles with same values are next to each other and can be merged
     * @return boolean | return true if the game can be continued
     */
    public boolean canStillMerge() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {

                if ((board[i][j] != null && board[i][j + 1] != null)
                        && board[i][j].getValue() == board[i][j + 1].getValue()) {
                    return true;
                }
                if ((board[j][i] != null && board[j + 1][i] != null)
                        && board[j][i].getValue() == board[j + 1][i].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * return if a player gets a tile with 2048
     * @return true | return true if a player gets a tile with 2048
     */
    public boolean isWinWith2048() {
        return winWith2048;
    }

    /**
     * check if the board has no more empty slot
     * @return true | return true if the board is filled up with tiles
     */
    public boolean isBoardFull() {
        return this.numOfZeroTiles == 0;
    }

    /**
     * game over if the board is filled up with tiles which cannot be merged any more
     * and all tiles have value less than 2048
     * @return true | return true if game over
     */
    public boolean isGameOver() {
        if ((isBoardFull() && !canStillMerge())|| isWinWith2048()) {
            gameOver = true;
        }
        return gameOver;
    }

    /**
     * get the total score accumulated
     * @return int | total score accumulated
     */
    public int getTotalScore() {
        return this.totalScore;
    }

    /**
     * get the tile located on the given position
     * @param row int | row on the game board
     * @param col int | col on the game board
     * @return Tile | tile located on the given position
     */
    public Tile getTile(int row, int col) {
        return board[row][col];
    }

    /**
     * return the current game status, including tile values on respective position, total score,
     * max tile value and the number of empty slots
     * @return String | the board display with tile values and other related information
     */
    public String toString() {
        Formatter boardPattern = new Formatter();
        boardPattern.format("---------------------\n");
        for (int r = 0; r < this.size; r++) {
            for (int c = 0; c < this.size; c++) {
                if (board[r][c] == null) {
                    boardPattern.format("|    ");
                } else {
                    boardPattern.format("|%4d", board[r][c].getValue());
                }
            }
            boardPattern.format("|\n");
            boardPattern.format("---------------------\n");
        }
        String output = boardPattern.toString();
        output += "Total score: " + totalScore + "\n";
        output += "Max tile value: " + maxValueOnTile + "\n";
        output += "Number of zero tiles: " + numOfZeroTiles + "\n";
        return output;
    }

    public static void main(String[] args) {
        GameLogic gameBoard = new GameLogic();
        System.out.println(gameBoard);

        System.out.println("Left");
        gameBoard.shiftTowardsLeft();
        System.out.println(gameBoard);

        System.out.println("Up");
        gameBoard.shiftUp();
        System.out.println(gameBoard);

        System.out.println("Down");
        gameBoard.shiftDown();
        System.out.println(gameBoard);

        System.out.println("Right");
        gameBoard.shiftTowardsRight();
        System.out.println(gameBoard);

        for (int i = 0; i < 50; i++) {
            System.out.println(i);
            System.out.println("Left");
            gameBoard.shiftTowardsLeft();
            System.out.println(gameBoard);
            System.out.println("Up");
            gameBoard.shiftUp();
            System.out.println(gameBoard);
            System.out.println("Down");
            gameBoard.shiftDown();
            System.out.println(gameBoard);
            System.out.println("Right");
            gameBoard.shiftTowardsRight();
            System.out.println(gameBoard);
        }
        System.out.println(gameBoard);
    }
}
