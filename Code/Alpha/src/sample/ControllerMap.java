package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerMap extends Controller{
    ViewMap viewMap;
    ModelMap modelMap;
    ModelParametres modelParametres;
    boolean started;
    Timer timer;
    EventHandler<KeyEvent> keyEventEventHandler;
    ArrayList<String> touches;

    public ControllerMap(ViewMap viewMap, ModelMap modelMap, ModelParametres modelParametres) {
        this.viewMap = viewMap;
        this.modelMap = modelMap;
        touches = new ArrayList<>();
        this.modelParametres = modelParametres;
        touches = modelParametres.getConfigTouches();
        started=false;
        secondaryTimer();
        keyEventEventHandler= new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("PRESSED! " + keyEvent.getCode());
                modelMap.getPacman().lastDirection = modelMap.getPacman().direction;

                String s = keyEvent.getCode().toString();
                if (touches.get(0).equals(s)) {
                    modelMap.getPacman().nextDirection = 1;
                } else if (touches.get(2).equals(s)) {
                    modelMap.getPacman().nextDirection = 2;
                } else if (touches.get(1).equals(s)) {
                    modelMap.getPacman().nextDirection = 3;
                } else if (touches.get(3).equals(s)) {
                    modelMap.getPacman().nextDirection = 4;
                }else if (s.equals("ESCAPE")){
                    started=false;
                    timer.cancel();
                    return;
                }
                if (!started) {
                    started = true;
                    timer();
                }
            }
        };
    }

    public void setController(){
        viewMap.primaryStage.getScene().addEventHandler(KeyEvent.KEY_PRESSED,keyEventEventHandler);
    }

    public void removeController(){
        viewMap.primaryStage.getScene().removeEventHandler(KeyEvent.KEY_PRESSED,keyEventEventHandler);
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
                        started=false;
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
                viewMap.manageScore();
                viewMap.setBonus();
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
        timer.scheduleAtFixedRate(movfantome,0,1000/(6+modelMap.getLevel()));
        timer.scheduleAtFixedRate(movpac,0,1000/8);
        timer.scheduleAtFixedRate(anim,0,1000/24);
        timer.scheduleAtFixedRate(tps,0,1000);
        timer.scheduleAtFixedRate(gum,0,1000);


    }

    public void secondaryTimer(){
        Timer checkStatus=new Timer("secondaryTimer", true);
        TimerTask chk=new TimerTask() {
            @Override
            public void run() {
                if(Pacman.getVies()<=0){
                    System.out.println("GAME OVER");
                    timer.cancel();
                    checkStatus.cancel();
                    removeController();
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
                            if(!f.isDead()){
                            Pacman.setVies(Pacman.getVies() - 1);
                            timer.cancel();
                            started = false;
                            removeController();
                            viewMap.manageVie();
                            animDeath();
                            modelMap.death();
                            }
                        }
                    }
                }
            }
        };

        checkStatus.scheduleAtFixedRate(death,0,1000/10);
        checkStatus.scheduleAtFixedRate(chk,0,1000);
    }

    public void animDeath(){
        Timer deathAnim=new Timer("animDeath",false);
        TimerTask animDeath=new TimerTask() {
            @Override
            public void run() {
                if(viewMap.cycleAnim<viewMap.spritesPacmanDead.size()-1){
                    viewMap.cycleAnim++;
                    viewMap.deathAnim();
                }else {
                    viewMap.cycleAnim=0;
                    deathAnim.cancel();
                    if(!(Pacman.getVies()<=0)) {
                        setController();
                    }
                }
            }
        };
        deathAnim.scheduleAtFixedRate(animDeath,0,1000/8);
    }
}
