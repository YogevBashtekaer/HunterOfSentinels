package org.example;

import javax.swing.*;
import java.awt.*;

public class Attack extends Rectangle {
    protected Image image;
    protected int speed;

    public Attack(String imagePath, int speed,int startX,int startY) {
        this.image = new ImageIcon(getClass().getResource(imagePath)).getImage();
        this.x = startX;
        this.y = startY;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

        this.speed = speed;
    }

    public void move(){
        this.y+=speed;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
