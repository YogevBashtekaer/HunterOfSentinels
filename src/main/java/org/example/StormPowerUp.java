package org.example;

public class StormPowerUp extends PowerUp {
    final static String STORM_POWER_UP_PHOTO_PATH = "/Photos/storm_attack_power_up.png";
    public StormPowerUp(int x, int y) {
        super(STORM_POWER_UP_PHOTO_PATH, x, y);
    }

    @Override
    public void apply(Player player) {
        player.resetStormAttack();
    }
}
