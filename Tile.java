package finalProject;

public class Tile {
    private int value;
    private int[] position;

    public Tile() {
        super();
        this.value = 0;
    }

    public Tile(int value, int[] position) {
        this.value = value;
        this.position = position;
    }

    public int getValue(){
        return this.value;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void moveTo(int[] position) {

    }

    public void mergeTiles(Tile otherTile) {

    }

    public boolean isZero() {
        return this.value == 0;
    }



    public String toString(){return "Tile is: " + this.value;}

    public static void main(String[] args) {
        Tile tile = new Tile();
        System.out.println(tile.getPosition());
    }

}
