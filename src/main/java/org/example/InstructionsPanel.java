package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {
    private JButton backToMenuButton;
    private static final int BACK_BUTTON_WIDTH = 120;
    private static final int BACK_BUTTON_HEIGHT = 30;
    private static final int BACK_BUTTON_Y = 700;

    private Image bgImage;
    private static final String BG_IMAGE_PATH = "/Photos/instructions_bg.png";

    public InstructionsPanel(Window parentWindow) {
        this.setBackground(Color.black);
        this.setLayout(null);

        this.bgImage = new ImageIcon(getClass().getResource(BG_IMAGE_PATH)).getImage();

        this.backToMenuButton = new JButton("Back to menu");
        int buttonX = (Utils.WINDOW_WIDTH - BACK_BUTTON_WIDTH) / 2;
        backToMenuButton.setBounds(buttonX, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        this.backToMenuButton.addActionListener(e -> parentWindow.switchToMenuPanel());
        this.add(backToMenuButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bgImage, 0, 0, Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT, null);
        g.setColor(Color.white);
        g.setFont(new Font("Verdana", Font.BOLD, 20));
        String[] instructions = {
                "You are in Black Bird",
                "Fight enemies and bosses",
                "Dodge attacks and defeat them",
                "Start with 3 lives and 1 special attack",
                "If you use the special attack or lose lives, enemies may drop items",
                "Controls:",
                "Move Right: RIGHT ARROW or D",
                "Move Left: LEFT ARROW or A",
                "Diagonal Shoot Left: Q",
                "Diagonal Shoot Right: E",
                "Storm Attack: S",
                "Shoot: SPACE",
                "Pause Game: P",
                "Toggle Music: M"
        };
        int y = 50;
        for (String line : instructions) {
            int textWidth = g.getFontMetrics().stringWidth(line);
            int x = (Utils.WINDOW_WIDTH - textWidth) / 2;
            g.drawString(line, x, y);
            y += 40;
        }
    }
}
