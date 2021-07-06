package Yahtzee;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Yahtzee extends JFrame {
    public Yahtzee() {
        initUI();
    }

    private void initUI() {

        add(new Board());

        setSize(750, 600);
        setTitle("Yahtzee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            Yahtzee ex = new Yahtzee();
            ex.setVisible(true);
        });
    }
}
