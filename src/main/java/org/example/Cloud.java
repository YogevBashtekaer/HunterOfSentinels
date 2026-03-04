package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Cloud extends Rectangle {
    private Image image;
    private int speed;

    public Cloud(int x,int y, String imagePath, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = new ImageIcon(getClass().getResource(imagePath)).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void move(){
        y += this.speed;
        if (y > Utils.WINDOW_HEIGHT) {
            y = -height;
            Random random = new Random();
            x = random.nextInt(Utils.WINDOW_WIDTH - width);
        }
    }

    public void paint(Graphics g){
        //g.fillRect(this.x,this.y,this.width,this.height);
        g.drawImage(this.image,this.x,this.y,this.width,this.height,null);
    }

}
