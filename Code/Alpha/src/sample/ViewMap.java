package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewMap {
    protected ModelMap modelMap;
    protected Stage primaryStage;


    public ViewMap(ModelMap modelMap,Stage stage) {
        this.modelMap = modelMap;
        this.primaryStage=stage;
        addWidgetsToView();
    }

    public void addWidgetsToView(){
        Group root = new Group();
        Scene sceneMap = new Scene(root,1200,800);
        primaryStage.setScene(sceneMap);
    }

    public void  display(){
        primaryStage.show();
    }

}
