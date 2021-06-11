package pacMan;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Controller de la partie
 */
public class ControllerMap{
    ViewMap viewMap;
    ModelMap modelMap;
    ModelParametres modelParametres;
    boolean started;
    Timer timer;
    EventHandler<KeyEvent> keyEventEventHandler;
    ArrayList<String> touches;
    Timeline endGame;
    Timer checkStatus;

    public ControllerMap(ViewMap viewMap, ModelMap modelMap, ModelParametres modelParametres) {
        this.viewMap = viewMap;
        this.modelMap = modelMap;
        touches = new ArrayList<>();
        this.modelParametres = modelParametres;
        touches = modelParametres.getConfigTouches();
        started=false;
        secondaryTimer();
        endGame();
        StaticMusic.musicGameOver.stop();

        // Evenement gérant les appuis sur une touche
        keyEventEventHandler= new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //System.out.println("PRESSED! " + keyEvent.getCode());
                modelMap.getPacman().lastDirection = modelMap.getPacman().direction; //conserve un court historique de la direction du pacman

                String s = keyEvent.getCode().toString(); //captation des touches
                if (touches.get(0).equals(s)) {
                    modelMap.getPacman().nextDirection = 1;
                } else if (touches.get(2).equals(s)) {
                    modelMap.getPacman().nextDirection = 2;
                } else if (touches.get(1).equals(s)) {
                    modelMap.getPacman().nextDirection = 3;
                } else if (touches.get(3).equals(s)) {
                    modelMap.getPacman().nextDirection = 4;
                }else if (s.equals("ESCAPE")&&started){ //Pause si appuyé sur espace
                    started=false;
                    if (StaticMusic.isRunaway) StaticMusic.musicRunaway.pause();
                    else StaticMusic.musicMain.pause();
                    timer.cancel();
                    return;
                }
                if (!started) { //lance ou relance la partie après une pause
                    StaticMusic.musicGameOver.stop();
                    if (StaticMusic.isRunaway) StaticMusic.musicRunaway.play();
                    else {
                        StaticMusic.musicMain.play();
                        StaticMusic.musicRunaway.stop();
                    }
                    started = true;
                    timer();

                }
            }
        };
    }

    public void setController(){ //active le controller pour la partie
        viewMap.scene.addEventHandler(KeyEvent.KEY_PRESSED,keyEventEventHandler);
    }

    public void removeController(){ //retire le controller pour la partie
        viewMap.scene.removeEventHandler(KeyEvent.KEY_PRESSED,keyEventEventHandler);
    }

    /**
     * Fonction gérant les boucle d'animation principales, le déplacement des personnages et la gestion globale du temps
     */
    public void timer(){
        timer=new Timer("mainTimer",true);
        TimerTask movpac=new TimerTask() { // tache gérant les deplacements des personnages et la victoire d'un niveau
            @Override
            public void run() {
                modelMap.getPacman().move();
                viewMap.move();
                if(modelMap.getListPointDispo().isEmpty()){
                    try {
                        StaticMusic.musicRunaway.stop();
                        StaticMusic.musicMain.stop();
                        StaticMusic.fxVictory.play();
                        StaticMusic.isRunaway = false;
                        started=false;
                        modelMap.nextlvl();
                        viewMap.move();
                        timer.cancel();
                        removeController();
                        setController();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        TimerTask anim= new TimerTask() { //tache gérant les cycle d'animation des fantomes
            @Override
            public void run() {
                modelMap.increment();
                modelMap.setBonus();
                viewMap.anim();
                viewMap.manageScore();
                viewMap.setBonus();
            }
        };
        TimerTask movfantome=new TimerTask() { //tache lançant l'algorithme de chaque fantome
            @Override
            public void run() {
                ArrayList<Fantome> listFantome=modelMap.getAllFantome();
                for(Fantome f :listFantome){
                    f.move();
                }
            }
        };

        /*
        TimerTask tps=new TimerTask() {
            @Override
            public void run() {
                System.out.println("Temps :"+modelMap.getTemps()+" Score :"+Pacman.getScore()+" Vies :"+Pacman.getVies());
            }
        };
        */

        TimerTask gum=new TimerTask() {//tache gérant la vulnérabilité des fantome et le temps
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
                        StaticMusic.isRunaway = false;
                        modelMap.tps=0;
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(movfantome,0,1000/(6+modelMap.getLevel()));
        timer.scheduleAtFixedRate(movpac,0,1000/8);
        timer.scheduleAtFixedRate(anim,0,1000/24);
        //timer.scheduleAtFixedRate(tps,0,1000);
        timer.scheduleAtFixedRate(gum,0,1000);
    }

    /**
     * Timer secondaire gérant les taches liés aux collisions et au game over
     */
    public void secondaryTimer(){
        checkStatus=new Timer("secondaryTimer", true);
        /*
        TimerTask chk=new TimerTask() {
            @Override
            public void run() {
                if(Pacman.getVies()<=0){
                    timer.cancel();
                    checkStatus.cancel();
                    removeController();
                }
            }
        };
        */
        TimerTask death=new TimerTask() {//Tache gérant les collisions
            @Override
            public void run() {
                ArrayList<Fantome> listFantome=modelMap.getAllFantome();
                for(Fantome f :listFantome){
                    if(f.emplacement==modelMap.getPacman().emplacement){
                        if(f.isGum()){
                            f.die();
                            StaticMusic.fxGhostDeath.stop();
                            StaticMusic.fxGhostDeath.play();
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
                                StaticMusic.musicMain.stop();
                                StaticMusic.musicRunaway.stop();
                                StaticMusic.fxDeath.play();
                                StaticMusic.isRunaway = false;
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

        checkStatus.scheduleAtFixedRate(death,0,1000/240);
        //checkStatus.scheduleAtFixedRate(chk,0,1000);
    }

    /**
     * Cycle d'animation de la mort du pacman
     */
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

    public void endGame(){
        endGame = new Timeline(new KeyFrame(Duration.seconds(2), e->{
            if (Pacman.getVies()<=0){
                timer.cancel();
                checkStatus.cancel();
                StaticMusic.musicGameOver.play();
                ModelScores modelScores = new ModelScores();
                ViewEndGame viewEndGame = new ViewEndGame(modelScores, viewMap.primaryStage);
                ControllerEndGame ceg = new ControllerEndGame(modelScores,modelMap,viewEndGame);
                endGame.stop();
            }
        }));
        endGame.setCycleCount(Animation.INDEFINITE);
        endGame.play();
    }

}
