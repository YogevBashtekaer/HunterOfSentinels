package org.example;

import java.util.Random;

public class Level4 extends EnemyLevel{
    private Random random = new Random();
    Nimrod nimrod;
    final static int NIMROD_Y = 50;
    final static int SENTINEL_Y = 150;
    final static int SENTINEL_1_x = 200;
    final static int SENTINEL_2_x = 600;
    final static int MAX_ENEMIES = 3;

    private int spawnTimer;
    private final int SPAWN_DELAY = 150;
    public Level4() {
        super();
        nimrod = new Nimrod(Utils.WINDOW_WIDTH/2,NIMROD_Y);
        this.activeEnemies.add(nimrod);
        this.musicPath = Utils.LEVEL_4_MUSIC_PATH;
        this.spawnTimer = 0;

    }

    @Override
    public void updateWave() {
        if(!nimrod.isDead() && this.activeEnemies.size() < MAX_ENEMIES){
            spawnTimer++;
            if (spawnTimer >= SPAWN_DELAY) {
                boolean sentinelX = random.nextBoolean();
                this.activeEnemies.add(new Sentinel(sentinelX ? SENTINEL_1_x : SENTINEL_2_x, SENTINEL_Y));
                spawnTimer = 0;
            }
        }
        if(this.nimrod.isDead() && this.activeEnemies.isEmpty()){
            this.levelCompleted = true;
            System.out.println("level 4 completed");
        }
    }
}
