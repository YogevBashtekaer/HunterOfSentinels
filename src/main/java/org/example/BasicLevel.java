package org.example;

import java.awt.*;

public abstract class BasicLevel {
    protected String musicPath;
    protected boolean levelCompleted;
    public abstract void update();
    public abstract void paint(Graphics g);

    public BasicLevel() {
        this.levelCompleted = false;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public boolean isLevelCompleted() {
        return levelCompleted;
    }


}
