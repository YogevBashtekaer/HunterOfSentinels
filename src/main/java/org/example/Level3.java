package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Level3 extends DodgingLevel{

    final static int MAX_ATTACKS = 15;
    final static int NUM_ACTIVE_BULLETS = 3;
    private final int ATTACK_SPEED = 5;
    private final String SHOT_PATH = "/Photos/sentinel_shot.png";

    public Level3() {
        this.attacks = new ArrayList<>();
        this.remainingAttacks = MAX_ATTACKS;
        this.musicPath = Utils.LEVEL_3_MUSIC_PATH;
        this.random = new Random();
    }

    @Override
    public void update() {
        if (attacks.size() < NUM_ACTIVE_BULLETS && remainingAttacks > 0) {
            int x = random.nextInt(Utils.WINDOW_WIDTH);
            attacks.add(new Attack(SHOT_PATH, ATTACK_SPEED, x, -100));
            remainingAttacks--;
            System.out.println("Remaining attacks: " + remainingAttacks);
        } else if (attacks.isEmpty() && remainingAttacks == 0) {
            this.levelCompleted = true;
            System.out.println("End of Level 3");
        }
    }

    @Override
    public void paint(Graphics g) {
        for (Attack a : attacks) {
            a.paint(g);
        }
    }

    @Override
    public List<Attack> getAttacks() {
        return attacks;
    }
}
