package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;

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
        if (actionEvent.getSource().equals(viewParametres.fieldUp)){
            viewParametres.errFieldUp.setVisible(false);
            viewParametres.fieldUp.addEventFilter(KeyEvent.KEY_PRESSED,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,0)) {
                    viewParametres.fieldUp.setText(tmp);
                    modelParametres.getConfigTouches().set(0, tmp);
                    modelParametres.updateConfig();
                }else {
                    viewParametres.errFieldUp.setVisible(true);
                }
            });
            viewParametres.fieldUp.removeEventFilter(KeyEvent.KEY_PRESSED,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,1)){
                    viewParametres.fieldDown.setText(tmp);
                    modelParametres.getConfigTouches().set(1,tmp);
                    modelParametres.updateConfig();
                }else {
                    viewParametres.errFieldUp.setVisible(true);
                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldDown)){
            viewParametres.errFieldDown.setVisible(false);
            viewParametres.fieldDown.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,1)){
                    viewParametres.fieldDown.setText(tmp);
                    modelParametres.getConfigTouches().set(1,tmp);
                    modelParametres.updateConfig();
                }else{
                    viewParametres.errFieldDown.setVisible(true);
                }
            });
            viewParametres.scene.removeEventFilter(KeyEvent.KEY_PRESSED,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,1)){
                    viewParametres.fieldDown.setText(tmp);
                    modelParametres.getConfigTouches().set(1,tmp);
                    modelParametres.updateConfig();
                }else{
                    viewParametres.errFieldDown.setVisible(true);
                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldRight)){
            viewParametres.errFieldRight.setVisible(false);
            viewParametres.fieldRight.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,2)){
                    viewParametres.fieldRight.setText(tmp);
                    modelParametres.getConfigTouches().set(2,tmp);
                    modelParametres.updateConfig();
                }else{
                    viewParametres.errFieldRight.setVisible(true);
                }
            });
            viewParametres.scene.removeEventFilter(KeyEvent.KEY_PRESSED,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,2)){
                    viewParametres.fieldRight.setText(tmp);
                    modelParametres.getConfigTouches().set(2,tmp);
                    modelParametres.updateConfig();
                }else{
                    viewParametres.errFieldRight.setVisible(true);

                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldLeft)){
            viewParametres.errFieldLeft.setVisible(false);
            viewParametres.scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,3)) {
                    viewParametres.fieldLeft.setText(tmp);
                    modelParametres.getConfigTouches().set(3, tmp);
                    modelParametres.updateConfig();
                }else{
                    viewParametres.errFieldLeft.setVisible(true);
                }
            });
            viewParametres.scene.removeEventFilter(KeyEvent.KEY_PRESSED,keyEvent ->{
                String tmp = keyEvent.getCode().toString();
                if(stringChecker(tmp,3)) {
                    viewParametres.fieldLeft.setText(tmp);
                    modelParametres.getConfigTouches().set(3, tmp);
                    modelParametres.updateConfig();
                }else{
                    viewParametres.errFieldLeft.setVisible(true);
                }
            });
        }
    }

    public boolean stringChecker(String s, int pos){
        for (int i = 0; i<modelParametres.getConfigTouches().size();i++){
            if (i == pos){

            }else{
//                System.out.println("Pressed  : " + s + " / Compared " + i + " : " + modelParametres.getConfigTouches().get(i));
                if (s.matches(modelParametres.getConfigTouches().get(i))){
//                    System.out.println(false);
                    return false;
                }

            }
        }
        return true;
    }
}
