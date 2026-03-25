package org.example;

import org.example.BasicLevel;
import org.example.Attack;
import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class DodgingLevel extends BasicLevel {
    protected List<Attack> attacks;
    protected int remainingAttacks;
    protected Random random;

    public abstract void update();
    public abstract void paint(Graphics g);
    public abstract List<Attack> getAttacks();
}