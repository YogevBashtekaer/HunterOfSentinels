package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EnemyLevel extends BasicLevel{
    List<Enemy> activeEnemies;


    public EnemyLevel() {
        this.activeEnemies = new ArrayList<>();
    }

    public List<Enemy> getActiveEnemies() {
        return activeEnemies;
    }

    public void removeDeadEnemy(){
        for (int i = 0; i < this.activeEnemies.size(); i++) {
            if (this.activeEnemies.get(i).isDead()){
                this.activeEnemies.remove(i);
                i--;
            }
        }
    }

    public void update() {
        removeDeadEnemy();
        for (Enemy e : activeEnemies) {
            e.move();
        }
        removeDeadEnemy();
        updateWave();
    }
    public void handleStormAttack() {
        Collections.shuffle(activeEnemies);
        int hitCount = 0;

        for (Enemy enemy : activeEnemies) {
            if (!enemy.isDead()) {
                enemy.hitByLightning();
                hitCount++;
                if (hitCount == 3) break;
            }
        }
    }

    public abstract void updateWave();
    @Override
    public void paint(Graphics g) {
        for (Enemy e : activeEnemies) {
            e.paint(g);
        }
    }
}
