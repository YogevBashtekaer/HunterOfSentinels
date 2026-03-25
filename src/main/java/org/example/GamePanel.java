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
    private List<PowerUp> powerUps;

    private JLabel gameOverLabel;
    private JButton restartButton;
    private JButton menuButton;
    private Image hpLogo;
    private Image stormAttackLogo;
    private GameLevel gameLevel;
    private BasicLevel currentLevel;
    private String screenMessage;

    private boolean stormAttackActive;
    private static final int LEFT_BOUND = 10;
    private static final int RIGHT_BOUND = Utils.WINDOW_WIDTH- 100;
    private final static int TIMER_DELAY = 16;

    final static int GAME_OVER_LABEL_WIDTH = Utils.WINDOW_WIDTH / 2 - 50;
    final static int GAME_OVER_LABEL_HIGH = Utils.WINDOW_HEIGHT / 2 - 100;

    final static String HP_LOGO_PATH = "/Photos/hp_logo.png";
    final static int LOGO_X = 20;
    final static int HP_Y = 20;
    final static int HEART_SPACING = 10;

    final static String STORM_ATTACK_LOGO_PATH = "/Photos/storm_attack_logo.png";
    final static int STORM_ATTACK_Y = HP_Y + 40;
    final static int PLAYER_DAMAGE = 10;


    public GamePanel(Window parentWindow){
        this.setBackground(Color.black);
        this.setLayout(null);

        this.parentWindow = parentWindow;
        this.player = new Player();
        this.playerAttacks = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.enemyAttacks = new ArrayList<>();
        this.clouds = new ArrayList<>();
        makeClouds();
        this.stormAttackActive = false;
        this.gameLevel = GameLevel.INTRO;
        powerUps = new ArrayList<>();

        this.hpLogo = new ImageIcon(getClass().getResource(HP_LOGO_PATH)).getImage();
        this.stormAttackLogo = new ImageIcon(getClass().getResource(STORM_ATTACK_LOGO_PATH)).getImage();

        this.gameOverLabel = new JLabel("GAME OVER");
        this.gameOverLabel.setBounds(GAME_OVER_LABEL_WIDTH,GAME_OVER_LABEL_HIGH,200,50);
        this.gameOverLabel.setForeground(Color.red);
        this.gameOverLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(this.gameOverLabel);
        this.gameOverLabel.setVisible(false);


        this.restartButton = new JButton("Restart");
        restartButton.setBounds(Utils.WINDOW_WIDTH/2-50, 150, 100, 50);
        restartButton.addActionListener(e -> restartGame());
        this.add(restartButton);
        restartButton.setVisible(false);

        this.menuButton = new JButton("Menu");
        menuButton.setBounds(Utils.WINDOW_WIDTH / 2 - 50, 220, 100, 50);
        menuButton.addActionListener(e -> parentWindow.switchToMenuPanel());
        this.add(menuButton);
        menuButton.setVisible(false);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);

        startEnemyCounter();
        this.gameTimer = new Timer(TIMER_DELAY,this);

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
    public void startGame() {
        this.gameTimer.start();
        parentWindow.changeMusic(Utils.LEVEL_1_MUSIC_PATH);
    }

    public void gameOver(){
        this.gameTimer.stop();
        this.parentWindow.changeMusic(Utils.GAME_OVER_MUSIC_PATH);
        this.gameOverLabel.setVisible(true);
        this.menuButton.setVisible(true);
        this.restartButton.setVisible(true);
    }

    private void restartGame() {
        this.playerAttacks.clear();
        this.enemyAttacks.clear();
        this.enemies.clear();
        this.clouds.clear();
        makeClouds();


        this.player.reset();
        this.stormAttackActive = false;

        this.gameLevel = GameLevel.INTRO;
        this.currentLevel = null;

        this.gameOverLabel.setVisible(false);
        this.menuButton.setVisible(false);
        this.restartButton.setVisible(false);

        this.gameTimer.start();
        parentWindow.changeMusic(Utils.LEVEL_1_MUSIC_PATH);
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

        for (PowerUp p : powerUps) {
            p.paint(g);
        }

        for (Attack a : enemyAttacks){
            a.paint(g);
        }


        for (int i = 0; i < player.getHealth(); i++) {
            g.drawImage(this.hpLogo, LOGO_X + i * (hpLogo.getWidth(null) + HEART_SPACING), HP_Y, null);
        }
        if(this.player.getCanUseStormAttack()){
            g.drawImage(this.stormAttackLogo,LOGO_X,STORM_ATTACK_Y,null);
        }

        if(this.screenMessage != null) {
            Graphics2D g2 = (Graphics2D) g;
            Font messageFont = new Font("Verdana", Font.BOLD, 48);
            g2.setFont(messageFont);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(this.screenMessage);
            int x = (Utils.WINDOW_WIDTH - textWidth) / 2;
            int y = Utils.WINDOW_HEIGHT / 2;
            g2.setColor(Color.RED);
            g2.drawString(this.screenMessage, x, y);
        }
    }

    public void showMessage(String message) {
        new Thread(() -> {
            this.screenMessage = message;
            Utils.sleep(1500);
            this.screenMessage = null;
        }).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if(this.player.x < RIGHT_BOUND) {
                    this.player.moveRight();
                }
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if(this.player.x > LEFT_BOUND) {
                    this.player.moveLeft();
                }
                break;
            case KeyEvent.VK_SPACE:
                tryShoot();
                break;
            case KeyEvent.VK_S:
                if(this.player.getCanUseStormAttack()) {
                    player.stormAttack();
                    this.stormAttackActive = true;
                }
                break;
            case KeyEvent.VK_Q:
                if(this.player.x > LEFT_BOUND) {
                    this.player.moveLeft();
                }
                tryShoot();
                break;
            case KeyEvent.VK_E:
                if(this.player.x < RIGHT_BOUND) {
                    this.player.moveRight();
                }
                tryShoot();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void tryShoot() {
        if (!player.isShooting() && player.getCanShoot()) {
            Attack a = player.attack();
            playerAttacks.add(a);
        }
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

    private void changeLevel(){
        switch (this.gameLevel){
            case INTRO:
                showMessage("ENEMY DETECTED!");
                this.gameLevel = GameLevel.LEVEL_1;
                this.currentLevel = new Level1();
                this.enemies = ((EnemyLevel)currentLevel).getActiveEnemies();
                break;
            case LEVEL_1:
                showMessage("ENEMY DETECTED!");
                this.gameLevel = GameLevel.LEVEL_2;
                this.currentLevel = new Level2();
                this.parentWindow.changeMusic(currentLevel.getMusicPath());
                this.enemies = ((EnemyLevel)currentLevel).getActiveEnemies();
                break;
            case LEVEL_2:
                showMessage("WARNING: INCOMING FIRE!");
                this.gameLevel = GameLevel.LEVEL_3;
                this.currentLevel = new Level3();
                this.parentWindow.changeMusic(currentLevel.getMusicPath());
                this.enemyAttacks = ((DodgingLevel)this.currentLevel).getAttacks();
                break;
            case LEVEL_3:
                showMessage("NIMROD HAS ARRIVED!");
                this.gameLevel = GameLevel.LEVEL_4;
                this.currentLevel = new Level4();
                this.parentWindow.changeMusic(currentLevel.getMusicPath());
                this.enemies = ((EnemyLevel)currentLevel).getActiveEnemies();
                break;
            case LEVEL_4:
                showMessage("MASTERMOLD DETECTED!");
                this.gameLevel = GameLevel.LEVEL_5;
                this.currentLevel = new Level5();
                this.parentWindow.changeMusic(currentLevel.getMusicPath());
                this.enemies = ((EnemyLevel)currentLevel).getActiveEnemies();
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameLevel.equals(GameLevel.INTRO) || this.currentLevel.levelCompleted ){
            changeLevel();
        }

        this.currentLevel.update();

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
            if (Math.random() < 0.01 && enemy.isCanAttack()) {
                Attack shot = enemy.attack();
                enemyAttacks.add(shot);
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.isDead() && !enemy.hitByLightning || enemy.y > Utils.WINDOW_HEIGHT) {
                enemies.remove(i);
                i--;
            } else {
                enemy.move();
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy instanceof Sentinel) {
                Sentinel s = (Sentinel) enemy;

                if (!s.entering && !s.isDiving() && Math.random() < 0.01 && Math.random() < 0.01) {
                    s.dive((int) player.getCenterX(), (int) player.getCenterY());
                }
            }
        }

        for (PowerUp p : powerUps) {
            p.move();
        }

        checkCollisions();

        if (!this.player.isAlive()){
            gameOver();
        }
        repaint();
    }

    public void checkCollisions() {
        Rectangle playerBounds = this.player.getBounds();
        ArrayList<Attack> attacksToRemove = new ArrayList<>();
        for (Attack attack : enemyAttacks) {
            Rectangle attackBounds = attack.getBounds();
            if (playerBounds.intersects(attackBounds)) {
                attacksToRemove.add(attack);
                System.out.println("hit");
                this.player.loseHealth();
            }
        }
        enemyAttacks.removeAll(attacksToRemove);

        for (Enemy enemy : enemies) {
            Rectangle enemyBounds = enemy.getBounds();

            if (playerBounds.intersects(enemyBounds)) {
                System.out.println("enemy hit player");
                this.player.loseHealth();
                enemy.takeDamage(20);
            }
        }
        ArrayList<Attack> playerAttacksToRemove = new ArrayList<>();
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();

        for (Attack attack : playerAttacks) {
            Rectangle attackBounds = attack.getBounds();

            for (Enemy enemy : enemies) {
                Rectangle enemyBounds = enemy.getBounds();

                if (attackBounds.intersects(enemyBounds)) {
                    System.out.println("enemy hit");
                    enemy.takeDamage(PLAYER_DAMAGE);
                    playerAttacksToRemove.add(attack);
                    if (enemy.isDead()) {

                        double chance = Math.random();

                        if (chance < 0.1) {
                            System.out.println("power up: health");
                            powerUps.add(new HealthPowerUp(enemy.x, enemy.y));
                        }
                        else if (chance < 0.18) {
                            System.out.println("power up: storm attack");
                            powerUps.add(new StormPowerUp(enemy.x, enemy.y));
                        }

                        enemiesToRemove.add(enemy);
                    }
                }
            }
        }
        playerAttacks.removeAll(playerAttacksToRemove);
        enemies.removeAll(enemiesToRemove);


        ArrayList<PowerUp> toRemove = new ArrayList<>();
        for(PowerUp p : powerUps){

            if(playerBounds.intersects(p.getBounds())){
                p.apply(player);
                toRemove.add(p);
            }
        }

        powerUps.removeAll(toRemove);
    }




    private void startEnemyCounter() {
        Timer counterTimer = new Timer(5000, e -> {
            System.out.println("Enemies remaining: " + enemies.size());
        });
        counterTimer.start();
    }

}

