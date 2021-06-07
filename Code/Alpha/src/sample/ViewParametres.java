package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewParametres {
    protected ModelParametres modelParametres;
    protected Stage primaryStage;
    protected Button fieldUp;
    protected Button fieldDown;
    protected Button fieldRight;
    protected Button fieldLeft;
    protected Scene scene;

    public ViewParametres(ModelParametres modelParametres, Stage primaryStage) {
        this.modelParametres = modelParametres;
        this.primaryStage = primaryStage;
        init();
        addWidgetsToView();
    }

    public void init(){
        fieldUp = new Button();
        fieldUp.setText(modelParametres.getConfigTouches().get(0));
        fieldDown = new Button();
        fieldDown.setText(modelParametres.getConfigTouches().get(1));
        fieldRight = new Button();
        fieldRight.setText(modelParametres.getConfigTouches().get(2));
        fieldLeft = new Button();
        fieldLeft.setText(modelParametres.getConfigTouches().get(3));
    }

    public void addWidgetsToView(){
        Pane root = new VBox();
        root.getChildren().addAll(fieldUp,fieldDown,fieldRight,fieldLeft);
        scene = new Scene(root,200,200);
        primaryStage.setScene(scene);
    }

    public void setController(EventHandler< ActionEvent > handler){
        fieldUp.setOnAction(handler);
        fieldDown.setOnAction(handler);
        fieldRight.setOnAction(handler);
        fieldLeft.setOnAction(handler);
    }
}
