package org.example;

import javax.swing.*;
import java.awt.*;

public class Player extends Rectangle{
    private Image image;
    private boolean isShooting;
    private boolean canShoot;
    private boolean canUseStormAttack;
    private int currentShootWaitTime;


    final static int PLAYER_X = Utils.WINDOW_WIDTH/2;
    final static int PLAYER_Y = 650;
    final static int PLAYER_SPEED = 25;
    final static int PLAYER_LAZER_SPEED = -10;

    final static int SHOOT_WAIT_TIME_LEVEL_1 = 1000;
    final static int SHOOT_WAIT_TIME_LEVEL_2 = 750;

    final static String BLACK_BIRD_PHOTO_PATH = "/Photos/black_bird.png";
    final static String BLACK_BIRD_LAZER_PHOTO_PATH = "/Photos/black_bird_lazer.png";
    final static String BLACK_BIRD_SHOOTING_PHOTO_PATH = "/Photos/black_bird_shooting.png";
    final static String TEST_PHOTO_PATH = "/Photos/result.png";



    final static int ATTACK_ANIMATION_TIME = 100;
    public Player() {
        this.isShooting = false;
        this.canShoot = true;
        this.currentShootWaitTime = SHOOT_WAIT_TIME_LEVEL_1;
        this.canUseStormAttack = true;
        this.x = PLAYER_X;
        this.y = PLAYER_Y;
        this.image = new ImageIcon(getClass().getResource(BLACK_BIRD_PHOTO_PATH)).getImage();
        if (image == null){
            System.out.println("Error loading player image");
        }

        this.height = this.image.getHeight(null);
        this.width = this.image.getWidth(null);
    }

    public void paint(Graphics g){
        g.fillRect(this.x,this.y,this.width,this.height);
        g.drawImage(this.image,this.x,this.y,this.width,this.height,null);
    }

    public void updateShootWaitTime(){
        if (this.currentShootWaitTime == SHOOT_WAIT_TIME_LEVEL_1){
            this.currentShootWaitTime = SHOOT_WAIT_TIME_LEVEL_2;
        }
    }
    public boolean stormAttack(){
        boolean answer = false;
        if (this.canUseStormAttack){
            this.canUseStormAttack = false;
            answer = true;
            System.out.println("use storm attack");
        }else {
            System.out.println("can't use storm attack");
        }
        return answer;
    }

    public boolean getCanUseStormAttack() {
        return canUseStormAttack;
    }

    public void resetStormAttack() {
        this.canUseStormAttack = true;
    }
    public boolean isShooting() {
        return isShooting;
    }

    public boolean getCanShoot() {
        return canShoot;
    }

    public void moveRight(){
        this.x += PLAYER_SPEED;
        System.out.println("move right");
    }
    public void  moveLeft(){
        this.x -= PLAYER_SPEED;
        System.out.println("move left");
    }
    public Attack attack(){
        if (!canShoot)return null;

        this.isShooting = true;
        new Thread(()->{
            this.image = new ImageIcon(getClass().getResource(BLACK_BIRD_SHOOTING_PHOTO_PATH)).getImage();
            Utils.sleep(ATTACK_ANIMATION_TIME);
            this.image = new ImageIcon(getClass().getResource(BLACK_BIRD_PHOTO_PATH)).getImage();
            this.isShooting = false;
            this.canShoot = false;
        }).start();

        int attackHeight = new ImageIcon(getClass().getResource(BLACK_BIRD_LAZER_PHOTO_PATH)).getImage().getHeight(null);
        int attackWidth = new ImageIcon(getClass().getResource(BLACK_BIRD_LAZER_PHOTO_PATH)).getImage().getWidth(null);
        int attackX = this.x + this.width/2 - attackWidth/2;
        int attackY = this.y - attackHeight;

        Attack attack = new Attack(BLACK_BIRD_LAZER_PHOTO_PATH,PLAYER_LAZER_SPEED ,attackX , attackY);

        new Thread(() -> {
            Utils.sleep(this.currentShootWaitTime);
            canShoot = true;
        }).start();

        return attack;
    }
}
