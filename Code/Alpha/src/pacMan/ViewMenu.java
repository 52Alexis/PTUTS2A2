package pacMan;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ViewMenu {
    protected Stage primaryStage;

    protected Label titre;
    protected Button boutonNouvellePartie;
    protected Button boutonPartiePerso;
    protected Button boutonMapEditor;
    protected Button boutonParametres;
    protected Button boutonMeilleursScores;
    protected Button boutonQuitter;

    private Rectangle rectangle;
    private ArrayList<Image> animbackground;
    private Timer anim;
    private int tick;

    protected FileChooser fileChooser;

    public ViewMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initAttributs();
        addWidgetToView();
        primaryStage.setResizable(false);
        StaticMusic.musicTitle.play();
        lauchanim();

    }

    public void initAttributs(){
        titre = new Label();
        titre.setGraphic(new ImageView(new Image("file:img/title.png")));
//        titre.setFill(Color.web("#ffe611"));

        boutonNouvellePartie = new Button("Nouvelle partie");
        boutonNouvellePartie.setOnAction(e->{
            StaticMusic.musicTitle.stop();
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
            anim.cancel();
        });

        boutonPartiePerso=new Button();
        boutonPartiePerso.setText("Partie personnalisée");
        boutonPartiePerso.setOnAction(e->{
            File file=throwChooser();
            ModelMap modelMap = null;
            if(file!=null){
                try {
                    modelMap = new ModelMap(file);
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                StaticMusic.musicTitle.stop();
                ViewMap viewMap = new ViewMap(modelMap,primaryStage);
                ModelParametres modelParametres = new ModelParametres();
                ModelMap.createBonus();
                ControllerMap controllerMap=new ControllerMap(viewMap,modelMap,modelParametres);
                controllerMap.setController();
                anim.cancel();
            }
        });

        boutonMapEditor = new Button("Editeur de maps");
        boutonMapEditor.setOnAction(e->{
            ModelMapEditor modelMapEditor = new ModelMapEditor();
            ViewMapEditor viewMapEditor = new ViewMapEditor(modelMapEditor,primaryStage);
            ControllerMapEditor controllerMapEditor = new ControllerMapEditor(modelMapEditor,viewMapEditor);
            anim.cancel();
        });
        boutonParametres = new Button(("Paramètres"));
        boutonParametres.setOnAction(e->{
            ModelParametres modelParametres = new ModelParametres();
            ViewParametres viewParametres = new ViewParametres(modelParametres,primaryStage);
            ControllerParametres cp = new ControllerParametres(modelParametres,viewParametres);
            anim.cancel();

        });
        boutonMeilleursScores = new Button("Meilleurs Scores");
        boutonMeilleursScores.setOnAction(e->{
            ModelScores modelScores = new ModelScores();
            ViewScores viewScores = new ViewScores(modelScores,primaryStage);
            anim.cancel();
        });

        boutonQuitter = new Button("Quitter");
        boutonQuitter.setOnAction(e->{
            anim.cancel();
            primaryStage.close();
        });

        fileChooser=new FileChooser();
        fileChooser.setTitle("Selectionner une map");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Cartes","*.map"));

        animbackground=new ArrayList<>();
        for(int i=0; i<114;i++){
            animbackground.add(new Image("file:animation/"+i+".png"));
        }
        rectangle=new Rectangle(280,80);

    }

    public void addWidgetToView(){
        GridPane gridPane = new GridPane();

        VBox boxTitre = new VBox(titre);
        boxTitre.setAlignment(Pos.CENTER);
        boxTitre.setTranslateY(-50);
        gridPane.add(boxTitre,3,0);

        gridPane.add(rectangle,3,2);

        VBox boxBoutons = new VBox(10,boutonNouvellePartie,boutonPartiePerso,boutonMapEditor,boutonParametres,boutonMeilleursScores,boutonQuitter);
        boxBoutons.setAlignment(Pos.CENTER);
        gridPane.add(boxBoutons,3,4);

        gridPane.setAlignment(Pos.CENTER);

        boutonNouvellePartie.getStyleClass().add("boutons");
        boutonMapEditor.getStyleClass().add("boutons");
        boutonParametres.getStyleClass().add("boutons");
        boutonMeilleursScores.getStyleClass().add("boutons");
        boutonPartiePerso.getStyleClass().add("boutons");
        boutonQuitter.getStyleClass().add("boutons");

//        titre.getStyleClass().add("title");
        gridPane.getStyleClass().add("background");

        Scene scene = new Scene(gridPane,1200,800);
        scene.getStylesheets().add("file:src/pacMan/Style.css");
        primaryStage.setScene(scene);
    }

    public void lauchanim(){
        anim=new Timer("Anim Menu",false);
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                tick++;
                rectangle.setFill(new ImagePattern(animbackground.get(tick% animbackground.size())));
            }
        };
        anim.scheduleAtFixedRate(task,0,1000/24);
    }

    public void display(){
        primaryStage.show();
    }

    public File throwChooser(){
        return fileChooser.showOpenDialog(primaryStage);
    }

}
