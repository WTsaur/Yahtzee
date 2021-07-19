package Yahtzee;

import java.util.Random;

import java.net.URL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class DiceModel extends JButton {
   private int value = 1;
   private boolean selected = false;
   private final int SCALE_VALUE = 170;

   public DiceModel() {
      URL url = this.getClass().getResource(Constants.DEFAULT_DICE);
      Image img = new ImageIcon(url).getImage().getScaledInstance(SCALE_VALUE, SCALE_VALUE, Image.SCALE_DEFAULT);
      ImageIcon defaultIcon = new ImageIcon(img);
      setIcon(defaultIcon);
      setBorder(new LineBorder(Color.WHITE, 4, true));
      setBorderPainted(selected);
      addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            selected = !selected;
            setBorderPainted(selected);
         }  
      });
   }

   public void reset() {
      selected = false;
      setBorderPainted(selected);
   }

   public boolean isSelected() {
      return selected;
   }

   public int getValue() {
      return value;
   }

   public int roll() {
      Random rand = new Random();
      value = rand.nextInt(6) + 1;
      URL url = this.getClass().getResource(Constants.BASE_IMAGE_PATH + "Dice-" + value + ".png");
      Image img = new ImageIcon(url).getImage().getScaledInstance(SCALE_VALUE, SCALE_VALUE, Image.SCALE_DEFAULT);
      ImageIcon newIcon = new ImageIcon(img);
      setIcon(newIcon);
      return value;
   }
}
