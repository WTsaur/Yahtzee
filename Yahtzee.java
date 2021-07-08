package Yahtzee;

import java.awt.EventQueue;
import java.net.URL;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Yahtzee extends JFrame {

    private static Board board;
    private static Menu menu;
    private static Image BgImage;
    private static int MAX_PLAYERS = 20;

    public Yahtzee() {
        URL url = this.getClass().getResource("/Yahtzee/images/background-green.jpg");
        BgImage = new ImageIcon(url).getImage();
        initUI();
    }

    private void initUI() {
        menu = new Menu(BgImage);
        menu.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = menu.TxtEntry.getText();
                try {
                    int num = Integer.parseInt(input);
                    if (num > MAX_PLAYERS || num < 1) {
                        menu.displayInputSizeErrorMessage();
                    } else {
                        menu.dismissErrorMessage();
                        startGame(num);
                    }
                } catch (NumberFormatException err) {
                    menu.displayInputTypeErrorMessage();
                }
            }
        });
        board = new Board(BgImage);
        board.setVisible(false);
        
        getContentPane().add(board);
        getContentPane().add(menu);
        pack();
        setTitle("Yahtzee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void startGame(int players) {
        board.setVisible(true);
        board.label.setText("" + players);
        menu.setVisible(false);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Yahtzee ex = new Yahtzee();
            ex.setVisible(true);
        });
    }
}
