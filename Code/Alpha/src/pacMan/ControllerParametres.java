package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * classe gérant l'affection de touche réalisée dans paramètre
 */
public class ControllerParametres implements EventHandler<ActionEvent> {
    protected ModelParametres modelParametres;
    protected ViewParametres viewParametres;

    public ControllerParametres(ModelParametres modelParametres, ViewParametres viewParametres) {
        this.modelParametres = modelParametres;
        this.viewParametres = viewParametres;
        viewParametres.setController(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(viewParametres.btnReturn)){//retourne au menu
            ViewMenu viewMenu = new ViewMenu(viewParametres.primaryStage);
        }

        //gère la captation des touches
        if (actionEvent.getSource().equals(viewParametres.fieldUp)){
            viewParametres.errFieldUp.setVisible(false);
            viewParametres.fieldUp.addEventFilter(KeyEvent.ANY,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,0)) {
                    viewParametres.fieldUp.setText(tmp);
                    modelParametres.getConfigTouches().set(0, tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else {
                    viewParametres.errFieldUp.setVisible(true);
                }
            });
            viewParametres.fieldUp.removeEventFilter(KeyEvent.ANY,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,0)){
                    viewParametres.fieldUp.setText(tmp);
                    modelParametres.getConfigTouches().set(0,tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else {
                    viewParametres.errFieldUp.setVisible(true);
                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldDown)){
            viewParametres.errFieldDown.setVisible(false);
            viewParametres.fieldDown.addEventFilter(KeyEvent.ANY, keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,1)){
                    viewParametres.fieldDown.setText(tmp);
                    modelParametres.getConfigTouches().set(1,tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else{
                    viewParametres.errFieldDown.setVisible(true);
                }
            });
            viewParametres.fieldDown.removeEventFilter(KeyEvent.ANY,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,1)){
                    viewParametres.fieldDown.setText(tmp);
                    modelParametres.getConfigTouches().set(1,tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else{
                    viewParametres.errFieldDown.setVisible(true);
                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldRight)){
            viewParametres.errFieldRight.setVisible(false);
            viewParametres.fieldRight.addEventFilter(KeyEvent.ANY, keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,2)){
                    viewParametres.fieldRight.setText(tmp);
                    modelParametres.getConfigTouches().set(2,tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else{
                    viewParametres.errFieldRight.setVisible(true);
                }
            });
            viewParametres.fieldRight.removeEventFilter(KeyEvent.ANY,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,2)){
                    viewParametres.fieldRight.setText(tmp);
                    modelParametres.getConfigTouches().set(2,tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else{
                    viewParametres.errFieldRight.setVisible(true);
                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldLeft)){
            viewParametres.errFieldLeft.setVisible(false);
            viewParametres.fieldLeft.addEventFilter(KeyEvent.ANY,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,3)) {
                    viewParametres.fieldLeft.setText(tmp);
                    modelParametres.getConfigTouches().set(3, tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else {
                    viewParametres.errFieldUp.setVisible(true);
                }
            });
            viewParametres.fieldLeft.removeEventFilter(KeyEvent.ANY,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,3)){
                    viewParametres.fieldLeft.setText(tmp);
                    modelParametres.getConfigTouches().set(3,tmp);
                    modelParametres.updateConfig();
                    viewParametres.btnReturn.requestFocus();
                }else {
                    viewParametres.errFieldUp.setVisible(true);
                }
            });
        }
        actionEvent.consume();
    }

    //Vérifie que la touche n'est pas déjà affectée
    public boolean stringChecker(String s, int pos){
        for (int i = 0; i<modelParametres.getConfigTouches().size();i++){
            if (i != pos) {
                if (s.matches(modelParametres.getConfigTouches().get(i))){
                    return false;
                }
            }
        }
        return true;
    }
}
