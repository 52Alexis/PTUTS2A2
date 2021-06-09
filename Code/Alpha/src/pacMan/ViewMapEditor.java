package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ViewMapEditor {
    protected ModelMapEditor modelMapEditor;
    protected Stage primaryStage;


    protected Button buttonEO;  //hori
    protected Button buttonNE;  //basgauche
    protected Button buttonNO;  //basdoite
    protected Button buttonNS;  //vertical
    protected Button buttonSE;  //hautgauche
    protected Button buttonSO;  //hautdroit
    protected Button buttonP;   //porte
    protected Button buttonPG;  //pacgum
    protected Button buttonPO;  //points

    protected Button buttonPac;
    protected Button button0;   //gomme

    protected Button buttonf1;   //gomme
    protected Button buttonf2;   //gomme
    protected Button buttonf3;   //gomme
    protected Button buttonf4;   //gomme

    protected Button buttonExport;

    protected Image[] listImage;
    protected Rectangle[][] cases;



    protected GridPane grid;
    protected Pane paneBoutons;
    protected VBox paneVerif;

    public ViewMapEditor(ModelMapEditor modelMapEditor, Stage primaryStage) {
        this.modelMapEditor = modelMapEditor;
        this.primaryStage = primaryStage;
        init();
        addWidgets();
    }

    public void init(){
        grid = new GridPane();
        paneBoutons = new Pane(); // boutons 16*16 groupe de 4*2 puis pacman et gomme puis fantomes
        paneVerif=new VBox();
        listImage=new Image[15];

        listImage[0]=new Image("file:img/Walls/EO.png");//hori
        listImage[1]=new Image("file:img/Walls/NE.png");//basgauche
        listImage[2]=new Image("file:img/Walls/NO.png");//basdroite
        listImage[3]=new Image("file:img/Walls/NS.png");//verti
        listImage[4]=new Image("file:img/Walls/P.png");//porte
        listImage[5]=new Image("file:img/Walls/PG.png");//gum
        listImage[6]=new Image("file:img/Walls/PO.png");//point
        listImage[7]=new Image("file:img/Walls/SE.png");//hautgauche
        listImage[8]=new Image("file:img/Walls/SO.png");//hautdroite
        listImage[9]=new Image("file:img/Entity/Mobile/Pacman/normal/9.png");//pacman
        listImage[10]=new Image("file:img/Walls/0.png");//void
        listImage[11]=new Image("file:img/Entity/Mobile/Fantome/blinky/8.png");
        listImage[12]=new Image("file:img/Entity/Mobile/Fantome/pinky/8.png");
        listImage[13]=new Image("file:img/Entity/Mobile/Fantome/inky/8.png");
        listImage[14]=new Image("file:img/Entity/Mobile/Fantome/clyde/8.png");

        buttonEO = new Button();
        buttonEO.setGraphic(new ImageView(listImage[0]));
        buttonNE = new Button();
        buttonNE.setGraphic(new ImageView(listImage[1]));
        buttonNO = new Button();
        buttonNO.setGraphic(new ImageView(listImage[2]));
        buttonNS = new Button();
        buttonNS.setGraphic(new ImageView(listImage[3]));
        buttonP = new Button();
        buttonP.setGraphic(new ImageView(listImage[4]));
        buttonPG = new Button();
        buttonPG.setGraphic(new ImageView(listImage[5]));
        buttonPO = new Button();
        buttonPO.setGraphic(new ImageView(listImage[6]));
        buttonSE = new Button();
        buttonSE.setGraphic(new ImageView(listImage[7]));
        buttonSO = new Button();
        buttonSO.setGraphic(new ImageView(listImage[8]));
        buttonPac=new Button();
        buttonPac.setGraphic(new ImageView(listImage[9]));
        button0 = new Button();
        button0.setGraphic(new ImageView(listImage[10]));
        buttonf1=new Button();
        buttonf1.setGraphic(new ImageView(listImage[11]));
        buttonf2=new Button();
        buttonf2.setGraphic(new ImageView(listImage[12]));
        buttonf3=new Button();
        buttonf3.setGraphic(new ImageView(listImage[13]));
        buttonf4=new Button();
        buttonf4.setGraphic(new ImageView(listImage[14]));

        buttonExport=new Button();
        buttonExport.setText("Exporter la map");
        
        cases=new Rectangle[28][32];
    }

    public void addWidgets(){

        HBox root=new HBox();
        
        paneBoutons.getChildren().addAll(buttonEO,buttonNE,buttonNO,buttonNS,buttonSE,buttonSO,buttonP,buttonPG,buttonPO,buttonPac,button0,buttonf1,buttonf2,buttonf3, buttonf4,buttonExport);
        for (int i = 0; i<28;i++){
            for (int j = 0; j<32; j++){
                Rectangle uneCase = new Rectangle(16,16);
                uneCase.setFill(new ImagePattern(new Image("file:img/Walls/0.png")));
                uneCase.setStroke(Color.RED);
                cases[i][j]=uneCase;
                grid.add(uneCase,i,j,1,1);
            }
        }
        placementBoutons();
        paneBoutons.setMinWidth(220);
        paneVerif.setMinWidth(400);
        root.setMinWidth(grid.getMinWidth()+paneBoutons.getMinWidth()+paneVerif.getMinWidth());
        grid.setAlignment(Pos.CENTER);
        root.getChildren().addAll(paneBoutons,grid,paneVerif);
        Scene scene = new Scene(root);


        scene.setFill(Color.gray(0.2));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

    }

    private void placementBoutons() {
        buttonSE.setLayoutX(20);
        buttonSE.setLayoutY(20);
        buttonSO.setLayoutX(70);
        buttonSO.setLayoutY(20);
        buttonNS.setLayoutX(120);
        buttonNS.setLayoutY(20);
        buttonPO.setLayoutX(170);
        buttonPO.setLayoutY(20);

        buttonNE.setLayoutX(20);
        buttonNE.setLayoutY(50);
        buttonNO.setLayoutX(70);
        buttonNO.setLayoutY(50);
        buttonEO.setLayoutX(120);
        buttonEO.setLayoutY(50);
        buttonPG.setLayoutX(170);
        buttonPG.setLayoutY(50);

        buttonPac.setLayoutX(10);
        buttonPac.setLayoutY(120);
        button0.setLayoutX(60);
        button0.setLayoutY(124);

        buttonP.setLayoutX(95);
        buttonP.setLayoutY(200);

        buttonf1.setLayoutX(15);
        buttonf1.setLayoutY(240);
        buttonf2.setLayoutX(65);
        buttonf2.setLayoutY(240);
        buttonf3.setLayoutX(115);
        buttonf3.setLayoutY(240);
        buttonf4.setLayoutX(165);
        buttonf4.setLayoutY(240);

        buttonExport.setLayoutX(50);
        buttonExport.setLayoutY(400);
    }

    public void setController(EventHandler<ActionEvent> handler){
        buttonEO.setOnAction(handler);
        buttonNE.setOnAction(handler);
        buttonNO.setOnAction(handler);
        buttonNS.setOnAction(handler);
        buttonSE.setOnAction(handler);
        buttonSO.setOnAction(handler);
        buttonP.setOnAction(handler);
        buttonPG.setOnAction(handler);
        buttonPO.setOnAction(handler);
        buttonPac.setOnAction(handler);
        button0.setOnAction(handler);
        buttonf1.setOnAction(handler);
        buttonf2.setOnAction(handler);
        buttonf3.setOnAction(handler);
        buttonf4.setOnAction(handler);
        buttonExport.setOnAction(handler);
    }

    public void videCase(Rectangle r){
        r.setFill(new ImagePattern(listImage[10]));
    }
}
