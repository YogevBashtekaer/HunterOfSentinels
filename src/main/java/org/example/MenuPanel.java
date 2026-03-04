package org.example;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JButton startGameButton;
    private JButton instructionsButton;
    private Window parentWindow;

    private static final int START_BUTTON_WIDTH = 120;
    private static final int START_BUTTON_HEIGHT = 30;
    private static final int START_BUTTON_Y = 100;

    private static final int INSTRUCTIONS_BUTTON_WIDTH = 120;
    private static final int INSTRUCTIONS_BUTTON_HEIGHT = 30;
    private static final int INSTRUCTIONS_BUTTON_Y = 150;

    public  MenuPanel(Window parentWindow){
        this.parentWindow = parentWindow;
        this.setBackground(Color.black);
        this.setLayout(null);
        setPreferredSize(new Dimension(Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT));

        this.startGameButton = new JButton("start game");
        int startButtonX = (Utils.WINDOW_WIDTH - START_BUTTON_WIDTH) / 2;
        startGameButton.setBounds(startButtonX, START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        this.startGameButton.addActionListener(e -> parentWindow.switchToGamePanel());
        this.add(startGameButton);

        this.instructionsButton = new JButton("instructions");
        int instructionsButtonX = (Utils.WINDOW_WIDTH - INSTRUCTIONS_BUTTON_WIDTH) / 2;
        instructionsButton.setBounds(instructionsButtonX, INSTRUCTIONS_BUTTON_Y, INSTRUCTIONS_BUTTON_WIDTH, INSTRUCTIONS_BUTTON_HEIGHT);
        this.instructionsButton.addActionListener(e -> parentWindow.switchToInstructionsPanel());
        this.add(instructionsButton);

    }





}
