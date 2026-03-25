package org.example;

import java.util.Random;

public class Level5 extends EnemyLevel{
    private Random random = new Random();

    Mastermold mastermold;

    private int spawnTimer;
    private final int SPAWN_DELAY = 150;

    final static int MASTERMOLD_Y = 50;
    final static int MAX_ENEMIES = 3;

    final static int SENTINEL_Y = 150;
    final static int SENTINEL_1_x = 200;
    final static int SENTINEL_2_x = 600;

    public Level5() {
        super();
        mastermold = new Mastermold(Utils.WINDOW_WIDTH/2,MASTERMOLD_Y);
        Sentinel sentinel1 = new Sentinel(SENTINEL_1_x,SENTINEL_Y);
        Sentinel sentinel2 = new Sentinel(SENTINEL_2_x,SENTINEL_Y);
        this.activeEnemies.add(this.mastermold);
        this.activeEnemies.add(sentinel1);
        this.activeEnemies.add(sentinel2);
        this.musicPath = Utils.LEVEL_5_MUSIC_PATH;
        spawnTimer = 0;
    }

    @Override
    public void updateWave() {
        if(!mastermold.isDead() && this.activeEnemies.size() < MAX_ENEMIES){
            spawnTimer++;
            if (spawnTimer >= SPAWN_DELAY) {
                boolean sentinelX = random.nextBoolean();
                this.activeEnemies.add(new Sentinel(sentinelX ? SENTINEL_1_x : SENTINEL_2_x, SENTINEL_Y));
                spawnTimer = 0;
            }
        }
        if(this.mastermold.isDead() && this.activeEnemies.isEmpty()){
            this.levelCompleted = true;
            System.out.println("level 5 completed");
        }
    }
}
