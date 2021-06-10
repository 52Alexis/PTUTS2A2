package pacMan;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewScores {
    protected ModelScores modelScores;
    protected Stage primaryStage;

    protected Label titre;
    protected VBox boxScores;
    protected Button btnReturn;
    protected ArrayList<String> scores;

    public ViewScores(ModelScores modelScores, Stage primaryStage) {
        this.modelScores = modelScores;
        this.primaryStage = primaryStage;
        init();
        addWidgetToView();
    }

    public void init(){
        titre = new Label("Titre");

        boxScores = new VBox();
        boxScores.setAlignment(Pos.CENTER);

        btnReturn = new Button("<");
        btnReturn.setOnAction(e->{
            ModelMenu modelMenu = new ModelMenu();
            ViewMenu viewMenu = new ViewMenu(modelMenu,primaryStage);
        });
        btnReturn.setTranslateY(-300);
        btnReturn.setTranslateX(-500);

        scores = modelScores.scores;
        System.out.println(scores);
    }

    public void addWidgetToView(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        for (int i = 0; i<scores.size(); i++){
            Label label = new Label(i+1 + "\t- "+scores.get(i));
            boxScores.getChildren().add(label);
        }
        root.getChildren().addAll(btnReturn,titre,boxScores);
        Scene scene = new Scene(root,1200,800);
        primaryStage.setScene(scene);
    }

}
