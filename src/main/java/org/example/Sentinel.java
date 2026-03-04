package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sentinel extends Enemy{
    private Random random = new Random();
    private double dx, dy;
    private boolean isDiving;

    private final String SENTINEL_PHOTO_PATH = "/Photos/sentinel.png";
    private final String SENTINEL_SHOT_PATH = "/Photos/sentinel_shot.png";
    private final String SENTINEL_HIT_BY_LIGHTNING_PATH = "/Photos/sentinel_hit_by_lightning.png";

    private   final int SENTINEL_SHOT_SPEED = 10;
    private   final int SENTINEL_SPEED = 1;
    public Sentinel(int x, int y, EnemyType enemyType) {
        super(x, y,enemyType);
        this.image = new ImageIcon(getClass().getResource(SENTINEL_PHOTO_PATH)).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.health = 1;
        this.movementSpeed = SENTINEL_SPEED;
        this.attackSpeed = SENTINEL_SHOT_SPEED;
        this.isDiving = false;
        this.originalImagePath = SENTINEL_PHOTO_PATH;
        this.lightningHitImagePath = SENTINEL_HIT_BY_LIGHTNING_PATH;
    }

    @Override
    public void updateMovement() {
        if (isDiving) {
            x += dx;
            y += dy;
        } else {
            dx = random.nextInt(3) - 1;
            dy = random.nextInt(3) - 1;
            if (dx == 0 && dy == 0) dx = 1;
            x += dx * movementSpeed;
            y += dy * movementSpeed;
        }
    }

    @Override
    public Attack attack(){
        Image shotImage = new ImageIcon(getClass().getResource(SENTINEL_SHOT_PATH)).getImage();
        int shotWidth = shotImage.getWidth(null);
        int shotX = this.x + (this.width / 2) - (shotWidth / 2);
        int shotY = this.y + this.height;
        return new Attack(SENTINEL_SHOT_PATH, this.attackSpeed, shotX, shotY);
    }




}
