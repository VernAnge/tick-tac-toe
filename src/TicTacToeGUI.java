// File: TicTacToeGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private TicTacToeLogic game;
    private JButton[][] buttons;

    public TicTacToeGUI() {
        game = new TicTacToeLogic();
        buttons = new JButton[3][3];

        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.placeMark(row, col)) {
                buttons[row][col].setText(String.valueOf(game.getCurrentPlayer()));
                if (game.checkForWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + game.getCurrentPlayer() + " wins!");
                    resetGame();
                } else if (game.isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "The game is a tie!");
                    resetGame();
                } else {
                    game.changePlayer();
                }
            }
        }
    }

    private void resetGame() {
        game.initializeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
