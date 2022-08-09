/**
 * CS5004 Summer 2022 Final Project - GAME 2048
 * Implement a Tile class to create tile objects on the game board
 * @author Qian Lang; Xiaodong Li
 * @version 0.0
 * 09 Aug 2022
 */
public class Tile {

    private int value;
    private int[] position;

    /**
     * non-argument constructor; Default value for tile is zero;
     */
    public Tile() {
        this.value = 0;
    }

    /**
     * constructor to create a tile object with value and position given
     * @param value : int | tile value, eg. 2, 4, 8, 16 ... 2048
     * @param position : int[] | the row and col on game board
     */
    public Tile(int value, int[] position) {
        this.value = value;
        this.position = position;
    }

    /**
     * return the value of the tile
     * @return int | tile value, eg. 2, 4, 8, 16 ... 2048
     */
    public int getValue(){
        return this.value;
    }

    /**
     * return the position of the tile on game board
     * @return int[] | the row and col on game board
     */
    public int[] getPosition() {
        return this.position;
    }

    /**
     * assign the value for a tile
     * @param value int | tile value, eg. 2, 4, 8, 16 ... 2048
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * assign the position for a tile
     * @param position int[] | the row and col on game board
     */
    public void setPosition(int[] position) {
        this.position = position;
    }

    /**
     * check if the tile has been moved to a new position
     * @param row : int | new row that the tile moved to
     * @param col : int | new col that the tile moved to
     * @return boolean | return true if the tile position has been changed
     */
    public boolean hasMoved(int row, int col) {
        return (row != position[0] || col != position[1]);
    }

    /**
     * display the tile data, including value and position
     * @return String | tile data, including value and position
     */
    public String toString() {
        int row = position[0];
        int col = position[1];
        String output = "Tile is: " + this.value + "\n";
        output += "Position: [" + row + ", " + col + "]";
        return output;
    }

    public static void main(String[] args) {
        Tile tile = new Tile(4, new int[] {2, 3});
        System.out.println(tile);
    }

}
