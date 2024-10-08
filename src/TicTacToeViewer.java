import javax.swing.*;

/** A viewer for the TicTacToe game. */
public class TicTacToeViewer {

    /** Creates a frame for the TicTacToe game. */
    public static void main(String[] args) {
        JFrame frame = new TicTacToeFrame();
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Tic Tac Toe");
    }
}
