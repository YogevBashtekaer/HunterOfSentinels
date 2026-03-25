package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class PowerUp extends Rectangle {
    protected Image image;
    protected int speed;

    final static int SPEED = 2;

    public PowerUp(String imagePath, int x, int y){
        this.image = new ImageIcon(getClass().getResource(imagePath)).getImage();
        this.x = x;
        this.y = y;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.speed = SPEED;
    }

    public void move() {
        this.y += speed;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public abstract void apply(Player player);


}
