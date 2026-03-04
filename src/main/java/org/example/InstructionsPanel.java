package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {
    private JButton backToMenuButton;

    private static final int BACK_BUTTON_WIDTH = 120;
    private static final int BACK_BUTTON_HEIGHT = 30;
    private static final int BACK_BUTTON_Y = 150;
    public InstructionsPanel(Window parentWindow) {
        this.setBackground(Color.green);
        this.setLayout(null);

        this.backToMenuButton = new JButton("Back to menu");
        int buttonX = (Utils.WINDOW_WIDTH - BACK_BUTTON_WIDTH) / 2;
        backToMenuButton.setBounds(buttonX, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        this.backToMenuButton.addActionListener(e -> parentWindow.switchToMenuPanel());
        this.add(backToMenuButton);
    }
}
