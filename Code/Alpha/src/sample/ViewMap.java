package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewMap {
    //protected ModelMap modelMap;
    protected Stage primaryStage;

    protected Text text;

    public ViewMap(Stage stage) {
        //this.modelMap = modelMap;
        this.primaryStage=stage;
    }



    public void  display(){
        primaryStage.show();
    }

}
