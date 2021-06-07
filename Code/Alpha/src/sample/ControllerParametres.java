package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
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
            viewParametres.scene.setOnKeyPressed(keyEvent -> {
                String tmp;
                if (keyEvent.getCode().equals(KeyCode.UP)){
                    tmp = "UP";
                }else if (keyEvent.getCode().equals(KeyCode.DOWN)){
                    tmp = "DOWN";
                }else if (keyEvent.getCode().equals(KeyCode.RIGHT)){
                    tmp = "RIGHT";
                }else if (keyEvent.getCode().equals(KeyCode.LEFT)){
                    tmp = "LEFT";
                }else{
                    tmp = keyEvent.getCode().toString();
                }
                if(stringChecker(tmp)){
                    viewParametres.fieldUp.setText(tmp);
                    modelParametres.getConfigTouches().set(0,tmp);
                    modelParametres.updateConfig();
                    viewParametres.scene.setOnKeyPressed(null);
                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldDown)){
            viewParametres.scene.setOnKeyPressed(keyEvent -> {
                String tmp = keyEvent.getCode().getName();
                if(stringChecker(tmp)){
                    viewParametres.fieldDown.setText(tmp);
                    modelParametres.getConfigTouches().set(1,tmp);
                }
            });
        }

        if (actionEvent.getSource().equals(viewParametres.fieldRight)){
            viewParametres.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    String tmp = keyEvent.getCode().getName();
                    if(stringChecker(tmp)){
                        viewParametres.fieldRight.setText(tmp);
                        modelParametres.getConfigTouches().set(2,tmp);
                    }
                }
            });

        }

        if (actionEvent.getSource().equals(viewParametres.fieldLeft)){
            viewParametres.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    String tmp = keyEvent.getCode().toString();
                    if(stringChecker(tmp)){
                        viewParametres.fieldLeft.setText(tmp);
                        modelParametres.getConfigTouches().set(3,tmp);
                    }
                }
            });

        }
    }

    public boolean stringChecker(String s){
        if (s.matches(viewParametres.fieldDown.getText()) || s.matches(viewParametres.fieldRight.getText()) ||
            s.matches(viewParametres.fieldLeft.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Key already assigned");
            alert.setContentText("You have already assigned this key to another function.");
            alert.initOwner(viewParametres.primaryStage);
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
