package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ViewMapEditor {
    protected ModelMapEditor modelMapEditor;
    protected Stage primaryStage;

    protected Button buttonStraight;
    protected Button button0;

    protected GridPane paneMapEdited;

    public ViewMapEditor(ModelMapEditor modelMapEditor, Stage primaryStage) {
        this.modelMapEditor = modelMapEditor;
        this.primaryStage = primaryStage;
        init();
        addWidgets();
    }

    public void init(){
        buttonStraight = new Button();
        buttonStraight.setGraphic(new ImageView(new Image("file:img/Walls/NS.png")));
        button0 = new Button();
        button0.setGraphic(new ImageView(new Image("file:img/Walls/0.png")));
        paneMapEdited = new GridPane();
    }

    public void addWidgets(){
        SplitPane splitPane = new SplitPane();
        VBox boxBoutons = new VBox();
        boxBoutons.getChildren().addAll(buttonStraight,button0);
        boxBoutons.setSpacing(10);
        for (int i = 0; i<28;i++){
            for (int j = 0; j<32; j++){
                Rectangle uneCase = new Rectangle(16,16);
                uneCase.setFill(new ImagePattern(new Image("file:img/Walls/0.png")));
                paneMapEdited.add(uneCase,i,j,1,1);
            }
        }
        paneMapEdited.setAlignment(Pos.CENTER);
        splitPane.getItems().addAll(boxBoutons,paneMapEdited);
        splitPane.setDividerPosition(1,0.5f);
        Scene scene = new Scene(splitPane,1200,800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

    }

    public void setController(EventHandler<ActionEvent> handler){
        buttonStraight.setOnAction(handler);
        button0.setOnAction(handler);
    }
}
