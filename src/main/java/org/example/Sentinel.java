package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sentinel extends Enemy{
    private Random random = new Random();
    private double dx, dy;
    private boolean isDiving;
    private boolean direction;

    private final String SENTINEL_PHOTO_PATH = "/Photos/sentinel.png";
    private final String SENTINEL_SHOT_PATH = "/Photos/sentinel_shot.png";
    private final String SENTINEL_HIT_BY_LIGHTNING_PATH = "/Photos/sentinel_hit_by_lightning.png";

    private final int SENTINEL_HEALTH = 1;
    private final int SENTINEL_SHOT_SPEED = 10;
    private final int SENTINEL_SPEED = 1;
    private final int SENTINEL_DIVE_SPEED = 5;
    public Sentinel(int x, int y) {
        super(x, y);
        this.enemyType = EnemyType.SENTINEL;
        this.image = new ImageIcon(getClass().getResource(SENTINEL_PHOTO_PATH)).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.health = SENTINEL_HEALTH;
        this.movementSpeed = SENTINEL_SPEED;
        this.attackSpeed = SENTINEL_SHOT_SPEED;
        this.isDiving = false;
        this.direction = random.nextBoolean();
        this.originalImagePath = SENTINEL_PHOTO_PATH;
        this.lightningHitImagePath = SENTINEL_HIT_BY_LIGHTNING_PATH;
    }
    private void randomVerticalMovement() {

        int choice = random.nextInt(3);

        if (choice == 0) {
            if (this.y - SENTINEL_SPEED >= 0) {
                this.y -= SENTINEL_SPEED;
            }

        } else if (choice == 1) {
            if (this.y + SENTINEL_SPEED <= 350) {
                this.y += SENTINEL_SPEED;
            }

        }

    }


    @Override
    public void updateMovement() {
        if (isDiving) {
            x += dx;
            y += dy;
        } else {
            if (direction && this.x + SENTINEL_SPEED + this.width < Utils.WINDOW_WIDTH){
                this.x += SENTINEL_SPEED;
                randomVerticalMovement();
            }else {
                this.direction = false;
                this.x -= SENTINEL_SPEED;
                randomVerticalMovement();
                if (this.x + SENTINEL_SPEED < 0){
                    this.direction = true;
                }
            }
        }
    }

    public void dive(int playerX, int playerY){
        this.canAttack = false;
        this.isDiving = true;
        this.movementSpeed = SENTINEL_DIVE_SPEED;
        double diffX = playerX - this.x;
        double diffY = playerY - this.y;
        double length = Math.sqrt(diffX*diffX + diffY*diffY);
        dx = (diffX / length) * movementSpeed * 2;
        dy = (diffY / length) * movementSpeed * 2;
    }
    @Override
    public Attack attack(){
        Image shotImage = new ImageIcon(getClass().getResource(SENTINEL_SHOT_PATH)).getImage();
        int shotWidth = shotImage.getWidth(null);
        int shotX = this.x + (this.width / 2) - (shotWidth / 2);
        int shotY = this.y + this.height;
        return new Attack(SENTINEL_SHOT_PATH, this.attackSpeed, shotX, shotY);
    }


    public boolean isDiving() {
        return isDiving;
    }
}
