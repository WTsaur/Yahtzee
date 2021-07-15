package Yahtzee

import java.util.Random;

public class Dice extends Panel {
   private int value;

   //randomly set value to number 1-6 and select panel image
   public Dice() {
      Random rand = new Random();
      value = rand.nextInt() % 6;
      value++;
      switch (value) {
         case 1:
            super("images/Dice-1.png");
            break;
         case 2:
            super("images/Dice-2.png");
            break;
         case 3:
            super("images/Dice-3.png");
            break;
         case 4:
            super("images/Dice-4.png");
            break;
         case 5:
            super("images/Dice-5.png");
            break;
         case 6:
            super("images/Dice-6.png");
            break;
      }
   }

   public void painComponent(Graphics g) {
      super.paintComponent(g);
   }

   public int getNumber() {
      return value;
   }

}
