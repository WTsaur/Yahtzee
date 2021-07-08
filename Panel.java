package Yahtzee;

import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Graphics;

public class Panel extends JPanel {
    
    private Image bgImg;
    protected int WIDTH;
    protected int HEIGHT;

    public Panel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public Panel(Image bgImg) {
        this.bgImg = bgImg;
        this.WIDTH = bgImg.getWidth(null);
        this.HEIGHT = bgImg.getHeight(null);
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setSize(size);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(bgImg, 0, 0, null);
    }
}
