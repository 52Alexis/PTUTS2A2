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

                switch (keyEvent.getCode()){
                    case UP:
                        modelMap.pacman.direction=1;
                        break;
                    case RIGHT:
                        modelMap.pacman.direction=2;
                        break;
                    case DOWN:
                        modelMap.pacman.direction=3;
                        break;
                    case LEFT:
                        modelMap.pacman.direction=4;
                        break;
                    default:
                        break;
                }
            }
        });
        timertest();
    }

    public void timertest(){
        Timer timer=new Timer("movePacam",true);
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                modelMap.pacman.move();
                viewMap.colorAll();
            }
        };
        timer.scheduleAtFixedRate(task,0,1000/11);
    }
}
