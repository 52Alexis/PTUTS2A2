package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

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
                }
            }
        };

        TimerTask tps=new TimerTask() {
            @Override
            public void run() {
                System.out.println("Temps :"+modelMap.getTemps()+" Score :"+Pacman.getScore()+" Vies :"+Pacman.getVies());
            }
        };
        timer.scheduleAtFixedRate(movfantome,0,1000/(6+modelMap.getLevel()));
        timer.scheduleAtFixedRate(movpac,0,1000/8);
        timer.scheduleAtFixedRate(anim,0,1000/24);
        timer.scheduleAtFixedRate(tps,0,1000);

    }

    public void secondaryTimer(){
        Timer checkStatus=new Timer("secondaryTimer", true);
        TimerTask chk=new TimerTask() {
            @Override
            public void run() {
                if(Pacman.getVies()<=0){
                    timer.cancel();
                    System.out.println("GAME OVER");
                    checkStatus.cancel();
                }
            }
        };

        TimerTask death=new TimerTask() {
            @Override
            public void run() {
                ArrayList<Fantome> listFantome=modelMap.getAllFantome();
                for(Fantome f :listFantome){
                    if(f.emplacement==modelMap.getPacman().emplacement){
                        if(f.isGum()){
                            f.die();
                            Pacman p = modelMap.getPacman();
                            p.nFantome++;
                            int score=100;
                            for(int i=0;i< p.nFantome;i++){
                                score=score*2;
                            }
                            Pacman.setScore(Pacman.getScore()+score);
                        }else {
                            Pacman.setVies(Pacman.getVies() - 1);
                            timer.cancel();
                            started = false;
                            setController();
                            modelMap.death();
                        }
                    }
                }
            }
        };

        TimerTask gum=new TimerTask() {
            @Override
            public void run() {
                if(modelMap.activatedgum){
                    modelMap.tps++;
                    if(modelMap.tps==8){
                        ArrayList<Fantome> listFantome=modelMap.getAllFantome();
                        for(Fantome f :listFantome){
                            f.setGum(false);
                        }
                        modelMap.activatedgum=false;
                        modelMap.tps=0;
                    }
                }
            }
        };
        checkStatus.scheduleAtFixedRate(death,0,1000/10);
        checkStatus.scheduleAtFixedRate(chk,0,1000);
        checkStatus.scheduleAtFixedRate(gum,0,1000);
    }
}
