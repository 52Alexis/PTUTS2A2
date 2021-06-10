package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerEndGame implements EventHandler<ActionEvent> {
    protected ModelScores modelScores;
    protected ViewEndGame viewEndGame;
    protected ModelMap modelMap;

    public ControllerEndGame(ModelScores modelScores,ModelMap modelMap, ViewEndGame viewEndGame) {
        this.modelScores = modelScores;
        this.viewEndGame = viewEndGame;
        this.viewEndGame.setController(this);
        this.modelMap=modelMap;
        gameOVer();

    }


    @Override
    public void handle(ActionEvent actionEvent) {
        StaticMusic.musicGameOver.stop();
        StaticMusic.initMusic();
        if (actionEvent.getSource().equals(viewEndGame.rejouer)){
            String strScore = Pacman.getScore()+"";
            int zta=7-strScore.length();
            for(int i=0;i<zta ;i++)
            {
                strScore="0"+strScore;
            }

            strScore= viewEndGame.fieldPseudo.getText()+"....."+strScore;

            modelScores.getScores().add(strScore);
            modelScores.updateScores();

            try {
                modelMap = new ModelMap(modelMap.file);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
                return;
            }
            ViewMap viewMap = new ViewMap(modelMap, viewEndGame.primaryStage);
            ModelParametres modelParametres = new ModelParametres();
            ModelMap.createBonus();
            ControllerMap controllerMap=new ControllerMap(viewMap,modelMap,modelParametres);
            controllerMap.setController();
        }

        if (actionEvent.getSource().equals(viewEndGame.saveAndQuit)){
            String strScore = Pacman.getScore()+"";
            int zta=7-strScore.length();
            for(int i=0;i<zta ;i++)
            {
                strScore="0"+strScore;
            }

            strScore= viewEndGame.fieldPseudo.getText()+"....."+strScore;

            modelScores.getScores().add(strScore);
            modelScores.updateScores();

            ModelMenu modelMenu = new ModelMenu();
            ViewMenu viewMenu = new ViewMenu(modelMenu,viewEndGame.primaryStage);

        }

    }

    public void gameOVer() {
        Timer OverAnim=new Timer("overAnim",false);
        TimerTask overAnim=new TimerTask() {
            @Override
            public void run() {
                if(viewEndGame.cycleAnim2<viewEndGame.over.size()-1) {
                    viewEndGame.animGO();
                }else {
                    OverAnim.cancel();
                }
            }
        };
        OverAnim.scheduleAtFixedRate(overAnim,0,1000/8);
    }
}
