package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
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
        FlowPane root = new FlowPane();
        for (int i=0; i<modelMap.getCases().length;i++){
            Rectangle uneCase = new Rectangle(i*modelMap.getTailleCase(),i*modelMap.getTailleCase(),modelMap.getTailleCase(),modelMap.getTailleCase());
            uneCase.setFill(Color.DARKBLUE);
            uneCase.setStroke(Color.GREEN);
            root.getChildren().add(uneCase);
        }
        Scene sceneMap = new Scene(root,28*modelMap.getTailleCase(),36*modelMap.getTailleCase());
        primaryStage.setScene(sceneMap);
    }

    public void  display(){
        primaryStage.show();
    }

}
