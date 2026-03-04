package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class Enemy extends Rectangle {
    protected Image image;
    protected int health;
    protected int movementSpeed;
    protected int attackSpeed;
    protected String originalImagePath;
    protected String lightningHitImagePath;
    protected boolean hitByLightning;
    protected EnemyType enemyType;
    protected int targetX;
    protected int targetY;
    protected boolean entering;

    final static int LIGHTNING_DAMAGE = 20;
    public Enemy(int targetX, int targetY, EnemyType enemyType) {
        this.x = targetX;
        this.y = -250;

        this.targetX = targetX;
        this.targetY = targetY;
        this.entering = true;

        this.enemyType = enemyType;
        this.hitByLightning = false;
    }
    public void moveToTarget(){
        this.y += 1;
        if (this.y >= this.targetY){
            this.entering = false;
        }
    }

    public void move() {
        if (this.entering){
            moveToTarget();
        }else {
            updateMovement();
        }
    }
    protected abstract void updateMovement();

    public abstract Attack attack();
    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public void hitByLightning() {
            hitByLightning = true;
            health -= LIGHTNING_DAMAGE;
            this.image = new ImageIcon(getClass().getResource(this.lightningHitImagePath)).getImage();

            new Thread(() -> {
                Utils.sleep(500);
                if (!isDead()) {
                    this.image = new ImageIcon(getClass().getResource(this.originalImagePath)).getImage();
                }
                hitByLightning = false;
            }).start();
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}