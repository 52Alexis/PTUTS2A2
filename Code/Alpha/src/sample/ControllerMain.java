package sample;

import javafx.stage.Stage;

public class ControllerMain {

    public ControllerMain(Stage stage) {
        ModelMap modelMap = new ModelMap();
        ViewMap viewMap = new ViewMap(modelMap,stage);
    }



}
