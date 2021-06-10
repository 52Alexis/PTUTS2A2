package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ViewEndGame {
    protected ModelScores modelScores;
    protected Stage primaryStage;

    protected Label labelScores;
    protected TextField fieldPseudo;
    protected Label errFieldPseudo;
    protected Button rejouer;
    protected Button saveAndQuit;
    protected ArrayList<Image> over;
    protected int cycleAnim2;
    javafx.scene.shape.Rectangle im = new javafx.scene.shape.Rectangle(0, 0, 400, 175);


    public ViewEndGame(ModelScores modelScores,Stage primaryStage) {
        this.modelScores = modelScores;
        this.primaryStage = primaryStage;
        try {
            init();
            }catch (Exception e){
                e.printStackTrace();
            }
        addWidgetToView();
    }

    public void init() throws FileNotFoundException {
        labelScores = new Label();
        labelScores.setText(String.valueOf(Pacman.getScore()));

        rejouer = new Button("Play Again");
        saveAndQuit = new Button("Save and quit");

        over = new ArrayList<>();

        for(int i=0;i<16;i++) {
            over.add(new Image(new FileInputStream("img/gameOver/"+i+".png")));
        }

        fieldPseudo = new TextField();
        fieldPseudo.textProperty().addListener(l->{
            if (fieldPseudo.getText().length() > 3) {
                String s = fieldPseudo.getText().substring(0, 3);
                fieldPseudo.setText(s);
                errFieldPseudo.setVisible(true);
            }
        });

        errFieldPseudo = new Label("Rentrez 3 lettres");
        errFieldPseudo.setTextFill(Color.RED);
        errFieldPseudo.setVisible(false);
    }

    public void addWidgetToView(){
        VBox root = new VBox();
        root.setSpacing(15);
        Label labelNice = new Label("Nice Try !");
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(im);
        root.getChildren().add(labelNice);
        labelNice.getStyleClass().add("text");

        root.getChildren().add(new Label("Nice Try!"));

        HBox boxScore = new HBox();
        Label label = new Label("Score : ");
        boxScore.getChildren().addAll(label,labelScores);
        label.getStyleClass().add("text");

        HBox boxPseudo = new HBox();
        Label label1 = new Label("Enter pseudo : ");
        boxPseudo.getChildren().addAll(label1,fieldPseudo);
        label1.getStyleClass().add("labelScore");

        HBox boxBtn = new HBox();
        boxBtn.setSpacing(10);
        boxBtn.getChildren().addAll(rejouer,saveAndQuit);
        rejouer.getStyleClass().add("boutons");
        saveAndQuit.getStyleClass().add("boutons");

        root.getChildren().addAll(boxScore,boxPseudo,boxBtn);
        Scene scene = new Scene(root,448,512);
        scene.getStylesheets().add("file:src/pacMan/Style.css");
        primaryStage.setScene(scene);
    }

    public void setController(EventHandler<ActionEvent> handler){
        rejouer.setOnAction(handler);
        saveAndQuit.setOnAction(handler);
    }

    public void gameOver() {
        im.setFill(new ImagePattern(over.get(cycleAnim2)));
    }

}
