
import javax.swing.JButton;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A tile in a tic-tac-toe game.
 * @author wulft
 */
public class TicTacToeTile extends JButton
{
    private int row;
    private int col;

    /**
     * Constructs a tile with a given row and column.
     * @param row the row of the tile
     * @param col the column of the tile
     */
    public TicTacToeTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the row of the tile.
     * @return the row of the tile
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column of the tile.
     * @return the column of the tile
     */
    public int getCol() {
        return col;
    }




}
