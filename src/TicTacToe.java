import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    public static void main(String[] args) {
        // declarations
        int rowPlayed;
        int colPlayed;
        String player;
        boolean gameCompleted = false;
        boolean playAgain;
        int turnsCompleted;
        Scanner in = new Scanner(System.in);

        // game loop
        do {
            // Clear the board and set the player to X (since X always moves first)
            clearBoard();
            player = "X";
            turnsCompleted = 0;
            gameCompleted = false;

            // turn loop
            do {
                // loop until the converted player coordinates are a valid move

                // show board
                display();

                do {
                    // get the coordinates for the move which should be 1 – 3 for the row and col

                    rowPlayed = SafeInput.getRangedInt(in, "Player " + player + " enter the row you would like to play", 1, ROW);
                    colPlayed = SafeInput.getRangedInt(in, "Player " + player + " enter the column you would like to play", 1, COL);

                    // convert the player move coordinates to the array indices which are 0 – 2 by subtracting 1
                    rowPlayed = rowPlayed - 1;
                    colPlayed = colPlayed - 1;
                } while (!isValidMove(rowPlayed, colPlayed));

                makeMove(player,rowPlayed, colPlayed);

                // if appropriate check for a win or a tie (i.e. if it is possible for a win or a tie at this point in the game, check for it.)
                turnsCompleted++;
                if (turnsCompleted >= 3) {
                    // If there is a win or tie announce it
                    if (isWin(player)) {
                        System.out.print(player + " Wins!");
                        gameCompleted = true;
                    }
                    else if (isTie()) {
                        System.out.print("It ended in a tie! You both win!");
                        gameCompleted = true;
                    }
                }

                // Toggle the player (i.e. X becomes O, O becomes X)
                if (player.equals("X")) {
                    player = "O";
                } else {
                    player = "X";
                }

            } while (!gameCompleted);

            // and then prompt the players to play again.
            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again");
        } while (playAgain);






    }

    private static void clearBoard() { // sets all the board elements to a space
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL;j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() { // shows the Tic Tac Toe game used as part of the prompt for the users move choice…
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL;j++) {
                System.out.print(board[i][j]);
                if (j <2) {
                    System.out.print(" | ");
                }
            }
            System.out.print("\n");
        }
    }

    private static boolean isValidMove(int row, int col) { // returns true if there is a space at the given proposed move coordinates which means it is a legal move.
        return Objects.equals(board[row][col], " ");
    }

    private static boolean isWin(String player) {// checks to see if there is a win state on the current board for the specified player (X or O) This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
        return (isColWin(player) | isRowWin(player) | isDiagnalWin(player));
    }

    private static boolean isColWin(String player) {// checks for a col win for specified player
        for (int i = 0; i < COL; i++) {
            if (Objects.equals(board[0][i], player) && Objects.equals(board[1][i], player)
                    && Objects.equals(board[2][i], player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) { // checks for a row win for the specified player
        for (int i = 0; i < ROW; i++) {
            if (Objects.equals(board[i][0], player) && Objects.equals(board[i][1], player)
                    && Objects.equals(board[i][2], player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagnalWin(String player) { // checks for a diagonal win for the specified player
        if (Objects.equals(board[0][0], player) && Objects.equals(board[1][1], player)
                && Objects.equals(board[2][2], player)) {
            return true;
        }
        else if (Objects.equals(board[0][2], player) && Objects.equals(board[1][1], player)
                && Objects.equals(board[2][0], player)) {
            return true;
        }
        return false;
    }
    private static boolean isTie() { // checks for a tie condition: all spaces on the board are filled
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL;j++) {
                if (Objects.equals(board[i][j], " ")){
                    return false;
                }
            }
        }
        return true;
    }

    private static void makeMove(String player, int row, int col) {
        board[row][col] = player;
    }


}
