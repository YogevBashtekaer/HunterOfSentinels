package org.example;

public class HealthPowerUp extends PowerUp {
    final static String HEALTH_POWER_UP_PHOTO_PATH = "/Photos/health_power_up.png";
    public HealthPowerUp(int x, int y) {
        super(HEALTH_POWER_UP_PHOTO_PATH, x, y);
    }

    @Override
    public void apply(Player player) {
        player.addHealth();
    }
}
