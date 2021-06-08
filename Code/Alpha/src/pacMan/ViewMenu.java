package pacMan;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class ViewMenu {
    protected ModelMenu modelMenu;
    protected Stage primaryStage;

    protected Label titre;
    protected Button boutonNouvellePartie;
    protected Button boutonParametres;
    protected Button boutonMeilleursScores;

    public ViewMenu(ModelMenu modelMenu, Stage primaryStage) {
        this.modelMenu = modelMenu;
        this.primaryStage = primaryStage;
        initAttributs();
        addWidgetToView();
        primaryStage.setResizable(false);
    }

    public void initAttributs(){
        titre = new Label();
        titre.setGraphic(new ImageView(new Image("file:img/title.png")));
//        titre.setFill(Color.web("#ffe611"));

        boutonNouvellePartie = new Button("Nouvelle partie");
        boutonNouvellePartie.setOnAction(e->{
            ModelMap modelMap = null;
            try {
                modelMap = new ModelMap(new File("data/mainMap.map"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            ViewMap viewMap = new ViewMap(modelMap,primaryStage);
            ModelParametres modelParametres = new ModelParametres();
            ModelMap.createBonus();
            ControllerMap controllerMap=new ControllerMap(viewMap,modelMap,modelParametres);
            controllerMap.setController();
        });
        boutonParametres = new Button(("Paramètres"));
        boutonParametres.setOnAction(e->{
            ModelParametres modelParametres = new ModelParametres();
            ViewParametres viewParametres = new ViewParametres(modelParametres,primaryStage);
            ControllerParametres cp = new ControllerParametres(modelParametres,viewParametres);

        });
        boutonMeilleursScores = new Button("Meilleurs Scores");

    }

    public void addWidgetToView(){
        GridPane gridPane = new GridPane();

        VBox boxTitre = new VBox(titre);
        boxTitre.setAlignment(Pos.CENTER);
        boxTitre.setTranslateY(-275);
        gridPane.add(boxTitre,3,0);

        VBox boxBoutons = new VBox(10,boutonNouvellePartie,boutonParametres,boutonMeilleursScores);
        boxBoutons.setAlignment(Pos.CENTER);
        gridPane.add(boxBoutons,3,4);

        gridPane.setAlignment(Pos.CENTER);

        boutonNouvellePartie.getStyleClass().add("boutons");
        boutonParametres.getStyleClass().add("boutons");
        boutonMeilleursScores.getStyleClass().add("boutons");

//        titre.getStyleClass().add("title");
        gridPane.getStyleClass().add("background");

        Scene scene = new Scene(gridPane,1200,800);
        scene.getStylesheets().add("file:src/pacMan/Style.css");
        primaryStage.setScene(scene);
    }

    public void display(){
        primaryStage.show();
    }

}
