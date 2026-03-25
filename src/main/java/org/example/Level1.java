package org.example;

import java.awt.*;

public class Level1 extends EnemyLevel{
    final static int ENEMY_Y = 50;
    int currentWave;
    public Level1() {
        super();
        this.musicPath = Utils.LEVEL_1_MUSIC_PATH;
        this.activeEnemies.add(new Sentinel(Utils.WINDOW_WIDTH/2,ENEMY_Y));
        this.currentWave = 1;
    }

    @Override
    public void updateWave() {
        if (activeEnemies.isEmpty()){
            switch (currentWave){
                case 1:
                    this.activeEnemies.add(new Sentinel(350,ENEMY_Y));
                    this.activeEnemies.add(new Sentinel(450,ENEMY_Y));
                    currentWave++;
                    break;
                case 2:
                    this.activeEnemies.add(new Sentinel(350,ENEMY_Y));
                    this.activeEnemies.add(new Sentinel(450,ENEMY_Y));
                    this.activeEnemies.add(new Sentinel(550,ENEMY_Y));
                    currentWave++;
                    break;
                case 3:
                    System.out.println("level 1 end");
                    this.levelCompleted = true;
            }
        }
    }


}
