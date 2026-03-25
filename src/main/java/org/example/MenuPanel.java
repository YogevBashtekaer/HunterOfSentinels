package org.example;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private Image title;
    private Image coolPhoto;
    private JButton startGameButton;
    private JButton instructionsButton;
    private Window parentWindow;

    private static final String TITLE_IMAGE_PATH = "/Photos/title.png";
    private static final int TITLE_IMAGE_X = 300;
    private static final int TITLE_IMAGE_Y = 20;

    private static final String COOL_PHOTO_PATH = "/Photos/cool_photo.png";
    private static final int COOL_PHOTO_X = 300;
    private static final int COOL_PHOTO_Y = 300;

    private static final int START_BUTTON_WIDTH = 120;
    private static final int START_BUTTON_HEIGHT = 30;
    private static final int START_BUTTON_Y = 150;

    private static final int INSTRUCTIONS_BUTTON_WIDTH = 120;
    private static final int INSTRUCTIONS_BUTTON_HEIGHT = 30;
    private static final int INSTRUCTIONS_BUTTON_Y = 200;

    public  MenuPanel(Window parentWindow){
        this.parentWindow = parentWindow;
        this.setBackground(Color.black);
        this.setLayout(null);
        setPreferredSize(new Dimension(Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT));

        this.title = new ImageIcon(getClass().getResource(TITLE_IMAGE_PATH)).getImage();
        this.coolPhoto = new ImageIcon(getClass().getResource(COOL_PHOTO_PATH)).getImage();

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(title, TITLE_IMAGE_X, TITLE_IMAGE_Y, null);
        g.drawImage(coolPhoto, COOL_PHOTO_X, COOL_PHOTO_Y, null);
    }



}
