package pacMan;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        titre = new Label();
        titre.setText("Meilleurs Scores");
        titre.setTranslateY(-50);

        boxScores = new VBox();
        boxScores.setAlignment(Pos.CENTER);

        btnReturn = new Button();
        btnReturn.setText("<");
        btnReturn.setOnAction(e->{
            ViewMenu viewMenu = new ViewMenu(primaryStage);
        });
        btnReturn.setTranslateY(-130);
        btnReturn.setTranslateX(-500);

        scores = modelScores.scores;
        System.out.println(scores);
    }

    public void addWidgetToView(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("background");
        for (int i = 0; i<scores.size(); i++){
            Label label = new Label(i+1 + "\t- "+scores.get(i));
            label.getStyleClass().add("labelScore");
            boxScores.getChildren().add(label);
        }
        btnReturn.getStyleClass().add("boutons2");
        titre.getStyleClass().add("title");
        root.getChildren().addAll(btnReturn,titre,boxScores);
        Scene scene = new Scene(root,1200,800);
        scene.getStylesheets().add("file:src/pacMan/Style.css");
        primaryStage.setScene(scene);
    }

}
