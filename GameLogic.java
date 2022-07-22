package finalProject;

public class GameLogic {
    private int totalScore;
    private int numOfZeroTiles;
    private boolean gameOver;
    private int maxValueOnTile;
    private Tile[][] tiles;

    public GameLogic() {
        super();
        this.totalScore = 0;
        this.numOfZeroTiles = 14; //two tiles with value 2 would be initialized for a new game
        this.gameOver = false;
        this.maxValueOnTile = 2;
        this.tiles = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile(0, new int[]{i, j});
            }
        }
        // generate two random tiles
    }

//generate a random tile with value 2 or 4 (80% : 20%) at an zero position
  public Tile generateRandomTile() {
        return new Tile();
    }

    public void isGameOver() {
        if (this.numOfZeroTiles == 0 || this.maxValueOnTile >= 2048) {
            this.gameOver = true;
        }
    }

    public void shiftUp() {

    }

    public void shiftDown() {

    }

    public void shiftTowardsLeft() {

    }

    public void shiftTowardsRight(){

    }

    public boolean getTile2048() {
        return this.maxValueOnTile >= 2048;
    }

    public boolean isBoardFull() {
        return this.numOfZeroTiles == 0;
    }

    public int getTotalScore() {
        return this.totalScore;
    }


}
