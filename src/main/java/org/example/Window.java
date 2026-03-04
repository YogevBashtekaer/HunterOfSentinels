package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Music music;


    public Window() throws HeadlessException {
        this.setTitle(" Hunter Of Sentinels");
        this.setBounds(0,0, Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);

        ImageIcon icon = new ImageIcon(getClass().getResource("/Photos/logo.png"));
        this.setIconImage(icon.getImage());

        MenuPanel menuPanel = new MenuPanel(this);
        GamePanel gamePanel = new GamePanel(this);
        InstructionsPanel instructionsPanelPanel = new InstructionsPanel(this);

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(gamePanel,"game");
        mainPanel.add(instructionsPanelPanel,"instructions");

        this.music = new Music(Utils.OPENING_MUSIC_PATH);
        this.music.loop();
        this.add(mainPanel);
        //this.pack();
        this.setVisible(true);
    }

    public void switchToGamePanel(){
        cardLayout.show(mainPanel,"game");
        mainPanel.getComponent(1).requestFocusInWindow();
        changeMusic(Utils.LEVEL_1_MUSIC_PATH);
    }

    public void switchToMenuPanel(){
        cardLayout.show(mainPanel,"menu");
    }

    public void switchToInstructionsPanel(){
        cardLayout.show(mainPanel,"instructions");
    }

    public void changeMusic(String path){
        if(this.music != null){
            try {
                this.music.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.music = new Music(path);
        this.music.loop();
    }

}
