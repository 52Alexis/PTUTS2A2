package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controlleur de la page de fin de partie
 */
public class ControllerEndGame implements EventHandler<ActionEvent> {
    protected ModelScores modelScores;
    protected ViewEndGame viewEndGame;
    protected ModelMap modelMap;

    public ControllerEndGame(ModelScores modelScores,ModelMap modelMap, ViewEndGame viewEndGame) {
        this.modelScores = modelScores;
        this.viewEndGame = viewEndGame;
        this.viewEndGame.setController(this);
        this.modelMap=modelMap;
        gameOver();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        StaticMusic.musicGameOver.stop();
        if (viewEndGame.fieldPseudo.getText().length() != 3 ) { //verification de la longueur des initiales du joueur
            String s = viewEndGame.fieldPseudo.getText();
            viewEndGame.fieldPseudo.setText(s);
            viewEndGame.errFieldPseudo.setVisible(true);
            return;
        }
        if (actionEvent.getSource().equals(viewEndGame.rejouer)){ //gestion du bouton rejouer
            String strScore = Pacman.getScore()+"";
            int zta=7-strScore.length();//mise en forme du score
            for(int i=0;i<zta ;i++)
            {
                strScore="0"+strScore;
            }

            strScore= viewEndGame.fieldPseudo.getText().toUpperCase()+"....."+strScore;

            modelScores.getScores().add(strScore);
            modelScores.updateScores();

            //creation de la nouvelle partie
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
            int zta=7-strScore.length();//mise en forme du score
            for(int i=0;i<zta ;i++)
            {
                strScore="0"+strScore;
            }

            strScore= viewEndGame.fieldPseudo.getText().toUpperCase()+"....."+strScore;

            modelScores.getScores().add(strScore);
            modelScores.updateScores();

            //retour sur le menu
            ViewMenu viewMenu = new ViewMenu(viewEndGame.primaryStage);

        }

    }

    /**
     * Fonction lançant l'animation d'apparation du text "Game Over"
     */
    public void gameOver() {
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
