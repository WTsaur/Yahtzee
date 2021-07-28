package Yahtzee;

import java.util.Vector; 

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EndgameScreen extends Panel {
    private JLabel winnerLabel;
    private JButton QuitButton;
    private JButton RestartButton;
    private DefaultListModel<String> listModel;

    public EndgameScreen(String bgImgPath) {
        super(bgImgPath);
        setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);

        winnerLabel = new JLabel();
        winnerLabel.setFont(new Font("Sansserif", Font.BOLD, 24));
        winnerLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(winnerLabel, gbc);

        listModel = new DefaultListModel<>();

        JList<String> PlayerList = new JList<>(listModel);
        PlayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PlayerList.setLayoutOrientation(JList.VERTICAL);
        PlayerList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    PlayerList.setSelectedIndex(-1);
                }  
            }
        });

        JScrollPane ListScroller = new JScrollPane(PlayerList);
        ListScroller.setPreferredSize(new Dimension(250, 80));
        gbc.gridy = 1;
        mainPanel.add(ListScroller, gbc);

        RestartButton = new JButton("Restart Game");
        RestartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Yahtzee.RestartGame();
            }
        });
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(RestartButton, gbc);

        QuitButton = new JButton("Quit Game");
        QuitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Yahtzee.QuitGame(false);
            }
        });
        gbc.gridx = 1;
        mainPanel.add(QuitButton, gbc);

        add(mainPanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void setRankings(Vector<Player> players) {
        Player winner = players.get(0);
        winnerLabel.setText(winner.getName() + " wins!");
        
        listModel.clear();
        int idx = 0;
        for (Player p : players) {
            listModel.add(idx++, idx + ". " + p.getName() + "\t" + p.getScorecard().getGrandTotal());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
