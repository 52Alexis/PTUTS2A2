package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class StaticMusic {
    static MediaPlayer musicMain;
    static MediaPlayer musicTitle;
    static MediaPlayer musicRunaway;
    static MediaPlayer musicGameOver;
    static MediaPlayer fxVictory;
    static MediaPlayer fxDeath;
    static MediaPlayer fxGhostDeath;
    static MediaPlayer fxBonus;


    public static void initMusic(){
        boolean playingSound;

        String songPath0 = "sounds/MainTheme.mp3";
        Media sound0 = new Media(new File(songPath0).toURI().toString());
        musicMain = new MediaPlayer(sound0);

        String songPath1 = "sounds/TitleTheme.mp3";
        Media sound1 = new Media(new File(songPath1).toURI().toString());
        musicTitle = new MediaPlayer(sound1);

        String songPath2 = "sounds/Runaway.mp3";
        Media sound2 = new Media(new File(songPath2).toURI().toString());
        musicRunaway = new MediaPlayer(sound2);

        String songPath3 = "sounds/GameOver.mp3";
        Media sound3 = new Media(new File(songPath3).toURI().toString());
        musicGameOver = new MediaPlayer(sound3);

        String songPath4 = "sounds/FX_victory.mp3";
        Media sound4 = new Media(new File(songPath4).toURI().toString());
        fxVictory = new MediaPlayer(sound4);

        String songPath5 = "sounds/FX_death.mp3";
        Media sound5 = new Media(new File(songPath5).toURI().toString());
        fxDeath = new MediaPlayer(sound5);

        String songPath6 = "sounds/FX_ghost_death.mp3";
        Media sound6 = new Media(new File(songPath6).toURI().toString());
        fxGhostDeath = new MediaPlayer(sound6);

        String songPath7 = "sounds/FX_bonus.mp3";
        Media sound7 = new Media(new File(songPath7).toURI().toString());
        fxBonus = new MediaPlayer(sound7);

        musicMain.setOnEndOfMedia(new Runnable() {
            public void run() {
                musicMain.seek(Duration.ZERO);
            }
        });

        musicTitle.setOnEndOfMedia(new Runnable() {
            public void run() {
                musicMain.seek(Duration.ZERO);
            }
        });

        musicRunaway.setOnEndOfMedia(new Runnable() {
            public void run() {
                musicRunaway.stop();
                musicMain.play();
            }
        });

        musicGameOver.setOnEndOfMedia(new Runnable() {
            public void run() {
                musicGameOver.seek(Duration.ZERO);
            }
        });

        fxDeath.setOnEndOfMedia(new Runnable() {
            public void run() {
                fxDeath.stop();
            }
        });

        fxGhostDeath.setOnEndOfMedia(new Runnable() {
            public void run() {
                fxGhostDeath.stop();
            }
        });


    }

}
