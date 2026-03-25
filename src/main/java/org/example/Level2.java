package org.example;

import java.util.Random;

public class Level2 extends EnemyLevel{
    private int waitingEnemies;
    final static int MAX_WAITING_ENEMIES = 15;
    final static int NO_WAITING_ENEMIES = 0;
    final static int MAX_ACTIVE_ENEMIES = 5;
    final int centerX = Utils.WINDOW_WIDTH / 2;
    final int startY = 50;
    public Level2() {
        super();
        this.musicPath = Utils.LEVEL_2_MUSIC_PATH;
        this.waitingEnemies = MAX_WAITING_ENEMIES;
        this.activeEnemies.add(new Sentinel(centerX, startY));
        this.activeEnemies.add(new Sentinel(centerX - 60, startY + 40));
        this.activeEnemies.add(new Sentinel(centerX + 60, startY + 40));
        this.activeEnemies.add(new Sentinel(centerX - 120, startY + 80));
        this.activeEnemies.add(new Sentinel(centerX + 120, startY + 80));
    }
    @Override
    public void updateWave() {
        if (this.activeEnemies.size() < MAX_ACTIVE_ENEMIES && this.waitingEnemies > NO_WAITING_ENEMIES){
            this.waitingEnemies--;
            Random random = new Random();
            int offsetX = random.nextInt(401) - 200;
            this.activeEnemies.add(new Sentinel(centerX + offsetX, startY));
        } else if (this.activeEnemies.isEmpty() && this.waitingEnemies == NO_WAITING_ENEMIES) {
            this.levelCompleted = true;
            System.out.println("level 2 end!!!");
        }
    }
}
