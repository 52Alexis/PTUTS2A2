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
    boolean started;

    public ControllerMap(ViewMap viewMap, ModelMap modelMap) {
        this.viewMap = viewMap;
        this.modelMap = modelMap;
        started=false;
    }

    public void setController(){
        viewMap.primaryStage.getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent) {
                System.out.println("PRESSED! "+keyEvent.getCode());
                modelMap.getPacman().lastDirection=modelMap.getPacman().direction;

                switch (keyEvent.getCode()) {
                    case UP:
                        modelMap.getPacman().nextDirection = 1;
                        break;
                    case RIGHT:
                        modelMap.getPacman().nextDirection = 2;
                        break;
                    case DOWN:
                        modelMap.getPacman().nextDirection = 3;
                        break;
                    case LEFT:
                        modelMap.getPacman().nextDirection = 4;
                        break;
                    default:
                        break;
                }

                if(!started){
                    started=true;
                    timer();
                }
            }
        });

    }

    public void timer(){
        Timer timer=new Timer("movePacam",true);
        TimerTask mov=new TimerTask() {
            @Override
            public void run() {
                modelMap.getPacman().move();
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

        TimerTask tps=new TimerTask() {
            @Override
            public void run() {
                System.out.println(modelMap.getTemps());
            }
        };
        timer.scheduleAtFixedRate(mov,0,1000/8);
        timer.scheduleAtFixedRate(anim,0,1000/24);
        timer.scheduleAtFixedRate(tps,0,1000);

    }
}
