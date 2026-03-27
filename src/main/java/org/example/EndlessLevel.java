package org.example;

import java.util.Random;

public class EndlessLevel extends EnemyLevel{
    final static int MAX_ACTIVE_ENEMIES = 5;
    final int centerX = Utils.WINDOW_WIDTH / 2;
    final int startY = 50;
    public EndlessLevel() {
        super();
        this.musicPath = Utils.ENDLESS_LEVEL_MUSIC_PATH;
        this.activeEnemies.add(new Sentinel(centerX, startY));
        this.activeEnemies.add(new Sentinel(centerX - 60, startY + 40));
        this.activeEnemies.add(new Sentinel(centerX + 60, startY + 40));
        this.activeEnemies.add(new Sentinel(centerX - 120, startY + 80));
        this.activeEnemies.add(new Sentinel(centerX + 120, startY + 80));
    }
    @Override
    public void updateWave() {
        if (this.activeEnemies.size() < MAX_ACTIVE_ENEMIES ){
            Random random = new Random();
            int offsetX = random.nextInt(401) - 200;
            this.activeEnemies.add(new Sentinel(centerX + offsetX, startY));
        }
    }
}
