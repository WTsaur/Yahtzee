package Yahtzee;

import java.net.URL;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

/* A base background panel to be used as the foundation for other components to reside in */

public class Panel extends JPanel {
    
    protected Image bgImg;
    protected int WIDTH;
    protected int HEIGHT;

    public Panel(String imgPath) {
        URL url = this.getClass().getResource(imgPath);
        bgImg = new ImageIcon(url).getImage();
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
