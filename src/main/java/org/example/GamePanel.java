package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Window parentWindow;
    private Player player;
    private Timer gameTimer;
    private List<Attack> playerAttacks;
    private List<Attack> enemyAttacks;
    private List<Enemy> enemies;
    private List<Cloud> clouds;
    private boolean stormAttackActive;
    private static final int LEFT_BOUND = 10;
    private static final int RIGHT_BOUND = Utils.WINDOW_WIDTH- 100;
    private final static int TIMER_DELAY = 16;



    public GamePanel(Window parentWindow){
        this.setBackground(Color.black);
        this.setLayout(null);

        this.parentWindow = parentWindow;
        this.player = new Player();
        this.playerAttacks = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.enemies.add(new Sentinel(700,50,EnemyType.SENTINEL));
        this.enemies.add(new Sentinel(500,50,EnemyType.SENTINEL));
        this.enemies.add(new Sentinel(300,50,EnemyType.SENTINEL));
        this.enemies.add(new Sentinel(100,50,EnemyType.SENTINEL));
        this.enemies.add(new Sentinel(200,150,EnemyType.SENTINEL));
        this.enemies.add(new Sentinel(200,350,EnemyType.SENTINEL));
        this.enemyAttacks = new ArrayList<>();
        this.clouds = new ArrayList<>();
        makeClouds();
        this.stormAttackActive = false;

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);

        //this.parentWindow.changeMusic(Utils.LEVEL_1_MUSIC_PATH);

        this.gameTimer = new Timer(TIMER_DELAY,this);
        this.gameTimer.start();
    }

    public void makeClouds(){
        Random random = new Random();
        int x;
        int y;
        String currentPhotoPath;
        int spacing = 150;
        for (int i = 0; i < 3; i++) {
            x = random.nextInt(Utils.WINDOW_WIDTH);
            y = i*spacing;
            currentPhotoPath = i % 2 == 0 ? Utils.CLOUD_PATH_1 : Utils.CLOUD_PATH_2;
            this.clouds.add(new Cloud(x,y,currentPhotoPath,Utils.NORMAL_CLOUD_SPEED));
        }
    }


    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Cloud c : clouds){
            c.paint(g);
        }

        this.player.paint(g);
        for (Attack a : playerAttacks) {
            a.paint(g);
        }

        for (Enemy e : enemies) {
            e.paint(g);
        }

        for (Attack a : enemyAttacks){
            a.paint(g);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_RIGHT:
                if(this.player.x < RIGHT_BOUND) {
                    this.player.moveRight();
                }
                break;

            case KeyEvent.VK_LEFT:
                if(this.player.x > LEFT_BOUND) {
                    this.player.moveLeft();
                }
                break;
            case KeyEvent.VK_SPACE:
                if(!player.isShooting()) {
                    if (this.player.getCanShoot()) {
                        Attack a = this.player.attack();
                        this.playerAttacks.add(a);
                    }
                    break;
                }
            case KeyEvent.VK_S:
                if(this.player.getCanUseStormAttack()) {
                    player.stormAttack();
                    this.stormAttackActive = true;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void handleStormAttack() {
        Collections.shuffle(enemies);
        int hitCount = 0;

        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                enemy.hitByLightning();
                hitCount++;
                if (hitCount == 3) break;
            }
        }
        stormAttackActive = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (stormAttackActive){
            handleStormAttack();
            this.stormAttackActive = false;
        }
        for (int i = 0; i < this.clouds.size(); i++) {
            this.clouds.get(i).move();
        }

        for (int i = 0; i < playerAttacks.size(); i++) {
            Attack a = playerAttacks.get(i);
            a.move();

            if (a.y + a.height < 0) {
                playerAttacks.remove(i);
                i--;
            }
        }

        for (int i = 0; i < enemyAttacks.size(); i++) {
            Attack a = enemyAttacks.get(i);
            a.move();

            if (a.y + a.height > Utils.WINDOW_HEIGHT) {
                enemyAttacks.remove(i);
                i--;
            }
        }

        for (Enemy enemy : enemies) {
            if (Math.random() < 0.01) {
                Attack shot = enemy.attack();
                enemyAttacks.add(shot);
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.isDead() && !enemy.hitByLightning) {
                enemies.remove(i);
                i--;
            } else {
                enemy.move();
            }
        }

        repaint();
    }
}

