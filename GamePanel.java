import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int TILE_ARC = 15;
    private static final int BOARD_MARGIN = 20;
    private static final int TILE_SIZE = 65;
    private static final int TILE_MARGIN = 15;
    private static final String FONT = "Arial";

    public GamePanel(){}

    /**
     * overriding paint() to define what we want to paint/show to the screen
     * @param graphics  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics graphics){
        Graphics2D graph = ((Graphics2D) graphics); // cast graphics to type Graphics2D which has more method than its parent class
        paintBackground(graph);
        setLogo(graph);
        showScoreBoard(graph);
        drawGameBoard(graph);
        graph.dispose(); // free memory

    }

    private void setLogo(Graphics graph) {
        graph.setFont( new Font(FONT, Font.BOLD, 38) );
        graph.setColor( Color.YELLOW );
        graph.drawString("Fun2048!", BOARD_MARGIN, 50);
    }

    private void paintBackground(Graphics graph){
        graph.setColor(Color.BLUE);
        graph.fillRect(0,0, GameTrigger.GAME_WINDOW.getWidth(), GameTrigger.GAME_WINDOW.getHeight());
    }
    private void showScoreBoard(Graphics graph){
        int widthOfScoreBoard = 100;
        int heightOfScoreBoard = 80;
        int x = GameTrigger.GAME_WINDOW.getWidth() - BOARD_MARGIN - widthOfScoreBoard;
//        int x = GameTrigger.GAME_WINDOW.getWidth() * (2/3);
        int y = 10;

        //draw score board
        graph.setColor(Color.white);
        graph.fillOval(x, y, widthOfScoreBoard, heightOfScoreBoard);
        graph.setFont( new Font(FONT, Font.BOLD, 15) );
        graph.setColor(Color.RED);
        graph.drawString("SCORE: ", x + 20, y + 35);

        //display current score to the score board
        graph.setFont( new Font(FONT, Font.BOLD, 18) );
        graph.setColor(Color.magenta);
        String score = String.valueOf(GameTrigger.GAME_LOGIC.getTotalScore());
        graph.drawString(score, x + 40, y + 55);


    }


    private static void drawGameBoard(Graphics graph){
        //set the game board
        graph.translate(BOARD_MARGIN, 100); //place the left-top of game board and all tiles at this coordination
        int widthOfBoard = GameTrigger.GAME_WINDOW.getWidth() - 2 * BOARD_MARGIN;
        int heightOfBoard = TILE_SIZE * 4  + (4 + 1) * TILE_MARGIN;
        graph.fillRect(0,0, widthOfBoard, heightOfBoard );
        graph.setColor(Color.RED);

        //lay out 4*4 tiles on the game board
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                drawTile(graph, GameTrigger.GAME_LOGIC.getTile(row, col), col, row);
            }
        }
    }

    private static void drawTile(Graphics graph, Tile tile, int x, int y) {
        //draw each tile(without value)
        int xOffset = x * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
        int yOffset = y * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
        graph.setColor(Color.GRAY);
        graph.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, TILE_ARC, TILE_ARC);

        //display value of each tile
        int value = 0;
        if (tile != null){
        value = tile.getValue();}

        graph.setColor(Color.orange);
        final Font font = new Font(FONT, Font.BOLD, 40);
        graph.setFont(font);


        final FontMetrics fm = graph.getFontMetrics(font);
        String s = String.valueOf(value);
        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, graph).getBaselineOffsets()[2];
        if (value != 0) {
            graph.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
        }

        //if game is over, display 'win' or 'lost' to the window
        if (GameTrigger.GAME_LOGIC.isGameOver()) {
            graph.setColor(Color.BLACK);
            graph.fillRect(0, 0, GameTrigger.GAME_WINDOW.getWidth(), GameTrigger.GAME_WINDOW.getWidth());
            graph.setColor(Color.white);
            graph.setFont(new Font(FONT, Font.BOLD, 40));
            if(GameTrigger.GAME_LOGIC.isWinWith2048()){
            graph.drawString("Congrats!You WIN!", 2 * BOARD_MARGIN, 100 + BOARD_MARGIN);}
            else{
                graph.drawString("Sorry!You Lose!", 2 * BOARD_MARGIN, 100 + BOARD_MARGIN);}
            GameTrigger.USER_ACTION.closeKey(); //player cannot play game anymore
            }

        }
    }

