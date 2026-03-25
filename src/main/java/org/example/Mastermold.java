package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Mastermold extends Enemy{
    private boolean direction;
    private Random random = new Random();

    private final int MASTERMOLD_HEALTH = 200;
    private final int MASTERMOLD_SHOT_SPEED = 10;
    private final int MASTERMOLD_SPEED = 1;

    private final String MASTERMOLD_PHOTO_PATH = "/Photos/mastermold.png";
    private final String MASTERMOLD_SHOT_PATH = "/Photos/mastermold_shot.jpg";
    private final String MASTERMOLD_HIT_BY_LIGHTNING_PATH = "/Photos/mastermod_hit_by_lightning.png";

    public Mastermold(int targetX, int targetY) {
        super(targetX, targetY);
        this.enemyType = EnemyType.MASTERMOLD;
        this.image = new ImageIcon(getClass().getResource(MASTERMOLD_PHOTO_PATH)).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.health = MASTERMOLD_HEALTH;
        this.movementSpeed = MASTERMOLD_SPEED;
        this.attackSpeed = MASTERMOLD_SHOT_SPEED;
        this.direction = random.nextBoolean();

        this.originalImagePath = MASTERMOLD_PHOTO_PATH;
        this.lightningHitImagePath = MASTERMOLD_HIT_BY_LIGHTNING_PATH;
    }



    @Override
    protected void updateMovement() {
        if (direction && this.x + MASTERMOLD_SPEED + this.width < Utils.WINDOW_WIDTH){
            this.x += MASTERMOLD_SPEED;
        }else {
            this.direction = false;
            this.x -= MASTERMOLD_SPEED;
            if (this.x + MASTERMOLD_SPEED < 0){
                this.direction = true;
            }
        }
    }

    @Override
    public Attack attack() {
        System.out.println("Mastermold attack");
        Image shotImage = new ImageIcon(getClass().getResource(MASTERMOLD_SHOT_PATH)).getImage();
        int shotWidth = shotImage.getWidth(null);
        int shotX = this.x + (this.width / 2) - (shotWidth / 2);
        int shotY = this.y + this.height;
        return new Attack(MASTERMOLD_SHOT_PATH, this.attackSpeed, shotX, shotY);

    }
}
