package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerMap extends Controller{
    ViewMap viewMap;
    ModelMap modelMap;
    boolean started;
    Timer timer;

    public ControllerMap(ViewMap viewMap, ModelMap modelMap) {
        this.viewMap = viewMap;
        this.modelMap = modelMap;
        started=false;
        secondaryTimer();
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
        timer=new Timer("mainTimer",true);
        TimerTask movpac=new TimerTask() {
            @Override
            public void run() {
                modelMap.getPacman().move();
                viewMap.move();
                if(modelMap.getListPointDispo().isEmpty()){
                    try {
                        modelMap.nextlvl();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        TimerTask anim= new TimerTask() {
            @Override
            public void run() {
                modelMap.increment();
                modelMap.setBonus();
                viewMap.anim();
            }
        };
        TimerTask movfantome=new TimerTask() {
            @Override
            public void run() {
                ArrayList<Fantome> listFantome=modelMap.getAllFantome();
                for(Fantome f :listFantome){
                    f.move();
                    if(f.emplacement==modelMap.getPacman().emplacement){
                        Pacman.setVies(Pacman.getVies()-1);
                        try {
                            modelMap.regen();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        TimerTask tps=new TimerTask() {
            @Override
            public void run() {
                System.out.println("Temps :"+modelMap.getTemps()+" Score :"+Pacman.getScore()+" Vies :"+Pacman.getVies());
            }
        };
        timer.scheduleAtFixedRate(movfantome,0,1000/7);
        timer.scheduleAtFixedRate(movpac,0,1000/8);
        timer.scheduleAtFixedRate(anim,0,1000/24);
        timer.scheduleAtFixedRate(tps,0,1000);

    }

    public void secondaryTimer(){
        Timer checkStatus=new Timer("secondaryTimer", true);
        TimerTask chk=new TimerTask() {
            @Override
            public void run() {
                ArrayList<Fantome> listFantome=modelMap.getAllFantome();
                for(Fantome f :listFantome){
                    if(f.emplacement==modelMap.getPacman().emplacement){
                        Pacman.setVies(Pacman.getVies()-1);
                        try {
                            timer.cancel();
                            started=false;
                            setController();
                            modelMap.regen();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(Pacman.getVies()==0){
                    timer.cancel();
                    System.out.println("GAME OVER");
                    checkStatus.cancel();
                }
            }
        };

        checkStatus.scheduleAtFixedRate(chk,0,1000);
    }
}
