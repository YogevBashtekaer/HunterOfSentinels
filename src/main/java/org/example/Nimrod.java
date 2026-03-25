package org.example;

import javax.swing.*;
import java.awt.*;

public class Nimrod extends Enemy{
    private double t = 0;
    private int centerX;
    private int centerY;
    private int radius = 120;

    private final String NIMROD_PHOTO_PATH = "/Photos/nimrod.png";
    private final String NIMROD_SHOT_PATH = "/Photos/nimrod_shot.png";
    private final String NIMROD_HIT_BY_LIGHTNING_PATH = "/Photos/nimrid_hit_by_lightning.png";

    private final int NIMROD_HEALTH = 100;
    private final int NIMROD_SHOT_SPEED = 10;
    private final int NIMROD_SPEED = 1;


    public Nimrod(int targetX, int targetY) {
        super(targetX, targetY);
        this.centerX = targetX;
        this.centerY = targetY;
        this.enemyType = EnemyType.NIMROD;
        this.image = new ImageIcon(getClass().getResource(NIMROD_PHOTO_PATH)).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.health = NIMROD_HEALTH;
        this.movementSpeed = NIMROD_SPEED;
        this.attackSpeed = NIMROD_SHOT_SPEED;

        this.originalImagePath = NIMROD_PHOTO_PATH;
        this.lightningHitImagePath = NIMROD_HIT_BY_LIGHTNING_PATH;
    }

    @Override
    protected void updateMovement() {
        t += 0.05;
        this.x = (int)(centerX + Math.sin(t) * radius);
        this.y = (int)(centerY + Math.sin(t * 2) * radius);
    }

    @Override
    public Attack attack(){
        Image shotImage = new ImageIcon(getClass().getResource(NIMROD_SHOT_PATH)).getImage();
        int shotWidth = shotImage.getWidth(null);
        int shotX = this.x + (this.width / 2) - (shotWidth / 2);
        int shotY = this.y + this.height;
        return new Attack(NIMROD_SHOT_PATH, this.attackSpeed, shotX, shotY);
    }
}
