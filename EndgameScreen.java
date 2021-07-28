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
import javax.swing.JTextField;

import java.io.*; 

public class EndgameScreen extends Panel {
   private JButton QuitButton;
   private JButton RestartButton;
   private JTextField currScore;
   private JTextField highScore;

   public EndgameScreen(String bgImgPath) {
      super(bgImgPath);
      setLayout(new BorderLayout());

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
      currScore = new JTextField(15);
      highScore = new JTextField(15);
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
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 2;
      buttonPanel.add(currScore, gbc);
      gbc.gridx++;
      buttonPanel.add(highScore, gbc);
      add(buttonPanel, BorderLayout.CENTER);
      setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
   }

   public void setText(Scorecard sc) {
      //algorithm:
      //get score as string (consider implementing toString)
      //add text to currScore
      //try : save file exists
      //      open file
      //      add top score to high 
      //catch : add empty text message
      currScore.setText(sc.toString());
      try {
         FileInputStream fis = new FileInputStream(Constants.GAME_DATA_FILE);
	 ObjectInputStream ois = new ObjectInputStream(fis);
	 Scorecard saved_sc = (Scorecard) ois.readObject();
         highScore.setText(saved_sc.toString());
      }
      catch (IOException e) {
         highScore.setText("No save files.");
      }
      catch (ClassNotFoundException e) {
         highScore.setText("No save files.");
      }
      //
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
   }
}
