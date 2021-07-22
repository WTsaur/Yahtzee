package Yahtzee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class PauseScreen extends Panel{
   public JButton RestartButton;
   public JButton QuitButton;
   public JButton ResumeButton;
   
   public PauseScreen(String bgImgPath) {
      super(bgImgPath);
      setLayout(new BorderLayout());
      
      ResumeButton = new JButton("Resume Game");
      ResumeButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) { 
            Yahtzee.toggleBoardScreen();
            Yahtzee.togglePauseScreen();
         }
      });

      RestartButton = new JButton("Restart Game");
      RestartButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) { 
            Yahtzee.RestartGame();
         }
      });      
      
      QuitButton = new JButton("Quit Game");
      QuitButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            int doSave = JOptionPane.showConfirmDialog(getParent(), "Would you like to save your current game?"); 
            if (doSave == JOptionPane.YES_OPTION) {
               Yahtzee.QuitGame(true);
            } else if (doSave == JOptionPane.NO_OPTION) {
               Yahtzee.QuitGame(false);
            }
         }
      });

      GridBagConstraints gbc = new GridBagConstraints();
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridBagLayout());
      buttonPanel.setOpaque(false);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      buttonPanel.add(RestartButton, gbc);
      gbc.gridy++;
      buttonPanel.add(QuitButton, gbc);

      add(buttonPanel, BorderLayout.CENTER);

      setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
   }

   public void painComponent(Graphics g) {
      super.paintComponent(g);
   }
}
