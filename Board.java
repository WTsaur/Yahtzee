package Yahtzee;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Board extends Panel {

    private GridBagLayout gbl;
    public JLabel label;

    public Board(String img) {
        super(img);
    }

    public Board(Image bgImg) {
        super(bgImg);
        gbl = new GridBagLayout();
        label = new JLabel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        setLayout(gbl);
        add(label);
        gbl.setConstraints(label, gbc);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}