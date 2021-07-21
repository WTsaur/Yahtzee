package Yahtzee;

import java.net.URL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.BorderFactory;

public class RestartScreen extends Panel{
   public JButton RestartButton;
   public JButton QuitButton;
   private GridBagLayout gbl; 
   private boolean isRestarted;   
   
   public RestartScreen(String bgImgPath) {
      super(bgImgPath);
      gbl = new GridBagLayout();
      setLayout(gbl);
      GridBagConstraints gbc = new GridBagConstraints();
      RestartButton = new JButton("Restart Game");
      QuitButton = new JButton("Quit Game");
      isRestarted = false;

      RestartButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) { 
            setVisible(false);
            isRestarted = false;            
         }
      });
      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.gridwidth = 2;
      add(RestartButton, gbc);

      QuitButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      gbc.gridx = 1;
      gbc.gridy = 3;
      gbc.gridwidth = 2;
      add(QuitButton, gbc);

      setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
   }

   public boolean hasRestarted() {
      return isRestarted;
   }

   public void painComponent(Graphics g) {
      super.paintComponent(g);
   }
}
