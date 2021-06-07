package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerMap extends Controller{
    ViewMap viewMap;
    ModelMap modelMap;

    public ControllerMap(ViewMap viewMap, ModelMap modelMap) {
        this.viewMap = viewMap;
        this.modelMap = modelMap;
    }

    public void setController(){
        viewMap.primaryStage.getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent) {
                System.out.println("PRESSED! "+keyEvent.getCode());
                modelMap.pacman.lastDirection=modelMap.pacman.direction;

                switch (keyEvent.getCode()) {
                    case UP:
                        modelMap.pacman.nextDirection = 1;
                        break;
                    case RIGHT:
                        modelMap.pacman.nextDirection = 2;
                        break;
                    case DOWN:
                        modelMap.pacman.nextDirection = 3;
                        break;
                    case LEFT:
                        modelMap.pacman.nextDirection = 4;
                        break;
                    default:
                        break;
                }
            }
        });
        timer();
    }

    public void timer(){
        Timer timer=new Timer("movePacam",true);
        TimerTask mov=new TimerTask() {
            @Override
            public void run() {
                modelMap.pacman.move();
                viewMap.move();
            }
        };
        TimerTask anim= new TimerTask() {
            @Override
            public void run() {
                modelMap.increment();
                viewMap.anim();
            }
        };
        timer.scheduleAtFixedRate(mov,0,1000/8);
        timer.scheduleAtFixedRate(anim,0,1000/24);
    }
}
