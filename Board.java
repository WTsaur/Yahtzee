package Yahtzee;

import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

public class Board extends JPanel {

    public Board() {
        initBoard();
    }

    private void initBoard() {
        setBackground(new ColorUIResource(25, 147, 31));
    }
}