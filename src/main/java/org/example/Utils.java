package org.example;

public class Utils {
    final static int WINDOW_WIDTH = 800;
    final static int WINDOW_HEIGHT = 800;

    final static String CLOUD_PATH_1 = "/Photos/cloud1.png";
    final static String CLOUD_PATH_2 = "/Photos/cloud2.png";
    final static int NORMAL_CLOUD_SPEED = 10;

    final static String OPENING_MUSIC_PATH = "/Music/X_Men_Opening_Theme.wav";
    final static String LEVEL_1_MUSIC_PATH = "/Music/X-Men_ first_class_magneto.wav";
    final static String LEVEL_2_MUSIC_PATH = "/Music/Enter_Storm_Rogue_and_Gambit.wav";
    final static String LEVEL_3_MUSIC_PATH = "/Music/Security_Alert.wav";
    final static String LEVEL_4_MUSIC_PATH = "/Music/Sentinels_Attack.wav";
    final static String LEVEL_5_MUSIC_PATH = "/Music/Final_Battle.wav";
    final static String ENDLESS_LEVEL_MUSIC_PATH = "/Music/wolverine_and_the_x_men.wav";
    final static String MISSION_COMPLETE_MUSIC_PATH = "/Music/Mission_Complete.wav";
    final static String GAME_OVER_MUSIC_PATH = "/Music/Game_Over.wav";

    public static void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (Exception e){
            System.out.println("problem with sleep!!!!!");
        }
    }

}
