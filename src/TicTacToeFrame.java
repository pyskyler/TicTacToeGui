

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/** A frame that contains a panel with a tic-tac-toe game. */
public class TicTacToeFrame extends JFrame
{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;
    private static final int ROW = 3;
    private static final int COL = 3;
    private int turnsCompleted;
    private String player = "X";
    private boolean gameCompleted = false;
    private TicTacToeTile[][] guiBoard = new TicTacToeTile[ROW][COL];
    private String[][] logicBoard = new String[ROW][COL];
    private JPanel mainPanel = new JPanel();

    /** Constructs a frame for the tic-tac-toe game. */
    public TicTacToeFrame()
    {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        mainPanel.setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(ROW,COL));

        // create the gui board
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                TicTacToeTile tile = new TicTacToeTile(row, col);

                tile.addActionListener(e -> {
                    tile.setText(player);
                    logicBoard[tile.getRow()][tile.getCol()] = player;
                    checkGameState();
                });

                guiBoard[row][col] = tile;

                gamePanel.add(tile);
            }
        }

        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                logicBoard[row][col] = " ";
            }
        }

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void checkGameState()
    {
        turnsCompleted++;
        String endMessage = "error";
        if (turnsCompleted >= 3) {
            // If there is a win or tie announce it
            if (isWin(player)) {
                endMessage = "Player " + player + " wins!";
                gameCompleted = true;
            } else if (turnsCompleted == 9) {
                endMessage = "It ended in a tie! You both win!";
                gameCompleted = true;
            } else if (turnsCompleted >= 7) {
                if (isTie()) {
                    endMessage = "It ended in a tie! You both win!";
                    gameCompleted = true;
                }
            }
        }

        if (gameCompleted) {
            int playAgain = JOptionPane.showConfirmDialog(mainPanel, endMessage + "\nWould you like to play again?",
                    "Play again?", JOptionPane.YES_NO_OPTION);
            if (playAgain == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }

        }

        // Toggle the player (i.e. X becomes O, O becomes X)
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }
    }

    private boolean isWin(String player) {// checks to see if there is a win state on the current board for the specified player (X or O) This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
        return (isColWin(player) | isRowWin(player) | isDiagonalWin(player));
    }

    private boolean isColWin(String player) {// checks for a col win for specified player
        for (int i = 0; i < COL; i++)
        {
            if (Objects.equals(logicBoard[0][i], player) && Objects.equals(logicBoard[1][i], player)
                    && Objects.equals(logicBoard[2][i], player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(String player) { // checks for a row win for the specified player
        for (int i = 0; i < ROW; i++)
        {
            if (Objects.equals(logicBoard[i][0], player) && Objects.equals(logicBoard[i][1], player)
                    && Objects.equals(logicBoard[i][2], player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) { // checks for a diagonal win for the specified player
        if (Objects.equals(logicBoard[0][0], player) && Objects.equals(logicBoard[1][1], player)
                && Objects.equals(logicBoard[2][2], player)) {
            return true;
        }
        else if (Objects.equals(logicBoard[0][2], player) && Objects.equals(logicBoard[1][1], player)
                && Objects.equals(logicBoard[2][0], player)) {
            return true;
        }
        return false;
    }

    private boolean isTie() { // checks for a tie condition: all spaces on the board are filled

        // test each possible move for each player
        boolean isTie = true;

        ArrayList <int[]> openTiles = new ArrayList<>();

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL;j++) {
                if (Objects.equals(logicBoard[i][j], " ")){
                    openTiles.add(new int[]{i, j});
                }
            }
        }

        for (int[] tile : openTiles) {
            logicBoard[tile[0]][tile[1]] = "X";
            if (isWin("X")) {
                isTie = false;
            }
            logicBoard[tile[0]][tile[1]] = "O";
            if (isWin("O")) {
                isTie = false;
            }
            logicBoard[tile[0]][tile[1]] = " ";
        }

        return isTie;
    }

    private void resetGame() {
        clearBoard();
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                guiBoard[row][col].setText(" ");
                logicBoard[row][col] = " ";
            }
        }
        turnsCompleted = 0;
        player = "X";
        gameCompleted = false;
    }

    private void clearBoard() { // sets all the board elements to a space
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL;j++) {
                logicBoard[i][j] = " ";
            }
        }
    }
}