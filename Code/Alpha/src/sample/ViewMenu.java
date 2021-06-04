package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewMenu {
    protected ModelMenu modelMenu;
    protected Stage primaryStage;

    protected Text titre;
    protected Button boutonNouvellePartie;
    protected Button boutonParametres;
    protected Button boutonMeilleursScores;

    public ViewMenu(ModelMenu modelMenu, Stage primaryStage) {
        this.modelMenu = modelMenu;
        this.primaryStage = primaryStage;
        initAttributs();
        addWidgetToView();
    }

    public void initAttributs(){
        titre = new Text("PacMan");
        titre.setFont(Font.font("Alfa Slab One",64));

        boutonNouvellePartie = new Button("Nouvelle partie");
        boutonNouvellePartie.setOnAction(e->{
            ModelMap modelMap = new ModelMap(28,36);
            ViewMap viewMap = new ViewMap(modelMap,primaryStage);

        });
        boutonParametres = new Button(("Paramètres"));
        boutonMeilleursScores = new Button("Meilleurs Scores");

    }

    public void addWidgetToView(){
        GridPane gridPane = new GridPane();

        VBox boxTitre = new VBox(titre);
        boxTitre.setAlignment(Pos.CENTER);
        boxTitre.setTranslateY(-300);
        gridPane.add(boxTitre,3,0);

        VBox boxBoutons = new VBox(10,boutonNouvellePartie,boutonParametres,boutonMeilleursScores);
        boxBoutons.setAlignment(Pos.CENTER);
        gridPane.add(boxBoutons,3,4);

        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane,1200,800);
        primaryStage.setScene(scene);
    }

    public void display(){
        primaryStage.show();
    }

}
