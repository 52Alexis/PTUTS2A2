package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewParametres {
    protected ModelParametres modelParametres;
    protected Stage primaryStage;

    protected Button btnReturn;
    protected Button fieldUp;
    protected Label errFieldUp;
    protected Button fieldDown;
    protected Label errFieldDown;
    protected Button fieldRight;
    protected Label errFieldRight;
    protected Button fieldLeft;
    protected Label errFieldLeft;

    protected Scene scene;

    public ViewParametres(ModelParametres modelParametres, Stage primaryStage) {
        this.modelParametres = modelParametres;
        this.primaryStage = primaryStage;
        init();
        addWidgetsToView();
        primaryStage.setResizable(false);
    }

    public void init(){
        btnReturn = new Button("<");
        btnReturn.setTranslateY(-300);
        btnReturn.setTranslateX(-500);
        fieldUp = new Button();
        fieldUp.setText(modelParametres.getConfigTouches().get(0));
        errFieldUp = new Label("Key already assignent");
        errFieldUp.setVisible(false);
        errFieldUp.setTextFill(Color.RED);
        fieldDown = new Button();
        fieldDown.setText(modelParametres.getConfigTouches().get(1));
        errFieldDown = new Label("Key already assignent");
        errFieldDown.setVisible(false);
        errFieldDown.setTextFill(Color.RED);
        fieldRight = new Button();
        fieldRight.setText(modelParametres.getConfigTouches().get(2));
        errFieldRight = new Label("Key already assignent");
        errFieldRight.setVisible(false);
        errFieldRight.setTextFill(Color.RED);
        fieldLeft = new Button();
        fieldLeft.setText(modelParametres.getConfigTouches().get(3));
        errFieldLeft = new Label("Key already assignent");
        errFieldLeft.setVisible(false);
        errFieldLeft.setTextFill(Color.RED);
    }

    public void addWidgetsToView(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        HBox boxUp = new HBox();
        Label labelUp = new Label("UP key : ");
        boxUp.setAlignment(Pos.CENTER);
        boxUp.getChildren().addAll(labelUp,fieldUp,errFieldUp);
        HBox boxDown = new HBox();
        Label labelDown = new Label("DOWN key : ");
        boxDown.setAlignment(Pos.CENTER);
        boxDown.getChildren().addAll(labelDown,fieldDown,errFieldDown);
        HBox boxRight = new HBox();
        Label labelRight = new Label("RIGHT key : ");
        boxRight.setAlignment(Pos.CENTER);
        boxRight.getChildren().addAll(labelRight,fieldRight,errFieldRight);
        HBox boxLeft = new HBox();
        Label labelLeft = new Label("UP key : ");
        boxLeft.setAlignment(Pos.CENTER);
        boxLeft.getChildren().addAll(labelLeft,fieldLeft,errFieldLeft);

        labelUp.getStyleClass().add("text");
        labelRight.getStyleClass().add("text");
        labelLeft.getStyleClass().add("text");
        labelDown.getStyleClass().add("text");


        btnReturn.getStyleClass().add("boutons");
        fieldUp.getStyleClass().add("boutons");
        fieldDown.getStyleClass().add("boutons");
        fieldRight.getStyleClass().add("boutons");
        fieldLeft.getStyleClass().add("boutons");
        //errFieldLeft

        root.getChildren().addAll(btnReturn,boxUp,boxDown,boxRight,boxLeft);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(root,1200,800);
        scene.getStylesheets().add("file:src/sample/Style.css");
        primaryStage.setScene(scene);
    }

    public void setController(EventHandler< ActionEvent > handler){
        btnReturn.setOnAction(handler);
        fieldUp.setOnAction(handler);
        fieldDown.setOnAction(handler);
        fieldRight.setOnAction(handler);
        fieldLeft.setOnAction(handler);
    }
}
