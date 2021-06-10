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

    public ControllerEndGame(ModelScores modelScores, ViewEndGame viewEndGame) {
        this.modelScores = modelScores;
        this.viewEndGame = viewEndGame;
        this.viewEndGame.setController(this);
    }


    @Override
    public void handle(ActionEvent actionEvent) {

        StaticMusic.musicGameOver.stop();
        StaticMusic.initMusic();
        gameOVer();
        if (actionEvent.getSource().equals(viewEndGame.rejouer)){
            String strScore = Pacman.getScore()+"";
            int zta=7-strScore.length();
            for(int i=0;i<zta ;i++)
            {
                strScore="0"+strScore;
            }

            strScore= viewEndGame.fieldPseudo+"....."+strScore;

            modelScores.getScores().add(strScore);
            modelScores.updateScores();

            ModelMap modelMap = null;
            try {
                modelMap = new ModelMap(new File("data/mainMap.map"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            ViewMap viewMap = new ViewMap(modelMap, viewEndGame.primaryStage);
            ModelParametres modelParametres = new ModelParametres();
            ModelMap.createBonus();
            ControllerMap controllerMap=new ControllerMap(viewMap,modelMap,modelParametres);
            controllerMap.setController();
            for (int i = 0;i<viewEndGame.over.size();i++ ) {
                gameOVer();
                viewEndGame.primaryStage.setScene(viewEndGame.scene);
            }
        }

        if (actionEvent.getSource().equals(viewEndGame.saveAndQuit)){
            String strScore = Pacman.getScore()+"";
            int zta=7-strScore.length();
            for(int i=0;i<zta ;i++)
            {
                strScore="0"+strScore;
            }

            strScore= viewEndGame.fieldPseudo+"....."+strScore;

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
                viewEndGame.cycleAnim2++;
            }
        };
        OverAnim.scheduleAtFixedRate(overAnim,0,1000/8);
    }
}
