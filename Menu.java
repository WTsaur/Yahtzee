package Yahtzee;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu extends Panel {

    private GridBagLayout gbl;
    public JTextField TxtEntry;
    public JLabel label;
    private JLabel errorLabel;
    public JButton startButton;

    public Menu(String img) {
        super(img);
    }

    public Menu(Image bgImg) {
        super(bgImg);
        gbl = new GridBagLayout();
        label = new JLabel("Number of Players:");
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        TxtEntry = new JTextField(2);
        TxtEntry.setText("" + Yahtzee.MIN_PLAYERS);
        startButton = new JButton("Start Game");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        setLayout(gbl);
        add(label);
        add(TxtEntry);
        add(startButton);
        add(errorLabel);
        gbl.setConstraints(label, gbc);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void displayInputSizeErrorMessage() {
        TxtEntry.setText("" + Yahtzee.MIN_PLAYERS);
        errorLabel.setText("Please pick a number between (and including) 2 to 20.");
        errorLabel.setVisible(true);
    }

    public void dismissErrorMessage() {
        TxtEntry.setText("" + Yahtzee.MIN_PLAYERS);
        errorLabel.setVisible(false);
    }

    public void displayInputTypeErrorMessage() {
        TxtEntry.setText("" + Yahtzee.MIN_PLAYERS);
        errorLabel.setText("Please enter an integer!");
        errorLabel.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
