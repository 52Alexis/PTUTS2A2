package pacMan;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
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

    protected Button buttonCheck;
    protected Button buttonExport;

    protected Image[] listImage;
    protected Rectangle[][] cases;

    protected Image[] greenLight;
    protected Rectangle[] symbolIntegrity;

    protected TextField tFOut;



    protected GridPane grid;
    protected VBox paneBoutons;
    protected VBox paneVerif;

    public ViewMapEditor(ModelMapEditor modelMapEditor, Stage primaryStage) {
        this.modelMapEditor = modelMapEditor;
        this.primaryStage = primaryStage;
        init();
        addWidgets();
    }

    public void init(){
        grid = new GridPane();
        paneBoutons = new VBox(); // boutons 16*16 groupe de 4*2 puis pacman et gomme puis fantomes
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

        greenLight=new Image[2];
        greenLight[0]=new Image("file:img/Editor/false.png");
        greenLight[1]=new Image("file:img/Editor/true.png");
        symbolIntegrity=new Rectangle[5];
        symbolIntegrity[0]=new Rectangle(40,40);
        symbolIntegrity[1]=new Rectangle(40,40);
        symbolIntegrity[2]=new Rectangle(40,40);
        symbolIntegrity[3]=new Rectangle(40,40);
        symbolIntegrity[4]=new Rectangle(40,40);
        symbolIntegrity[0].setFill(new ImagePattern(greenLight[0]));
        symbolIntegrity[1].setFill(new ImagePattern(greenLight[0]));
        symbolIntegrity[2].setFill(new ImagePattern(greenLight[0]));
        symbolIntegrity[3].setFill(new ImagePattern(greenLight[0]));
        symbolIntegrity[4].setFill(new ImagePattern(greenLight[0]));

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

        buttonCheck=new Button();
        buttonCheck.setText("Vérifier l'intégrité de la map");
        buttonExport=new Button();
        buttonExport.setText("Exporter la map");
        tFOut=new TextField();
        tFOut.setPromptText("Nom de la map");

        cases=new Rectangle[28][32];
    }

    public void addWidgets(){

        HBox root=new HBox();
        root.setBackground(new Background(new BackgroundFill(Color.gray(0.2),CornerRadii.EMPTY,null)));

        for (int i = 0; i<28;i++){
            for (int j = 0; j<32; j++){
                Rectangle uneCase = new Rectangle(16,16);
                uneCase.setFill(new ImagePattern(new Image("file:img/Walls/0.png")));
                uneCase.setStroke(Color.RED);
                cases[i][j]=uneCase;
                grid.add(uneCase,i,j,1,1);
            }
        }
        paneBoutons.setMinWidth(220);
        placementBoutons();
        paneVerif.setMinWidth(400);
        addIntegrityLabel();
        root.setMinWidth(grid.getMinWidth()+paneBoutons.getMinWidth()+paneVerif.getMinWidth());
        grid.setAlignment(Pos.CENTER);
        root.getChildren().addAll(paneBoutons,grid,paneVerif);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

    }

    public void addIntegrityLabel(){
        FlowPane flwp0=new FlowPane();
        FlowPane flwp1=new FlowPane();
        FlowPane flwp2=new FlowPane();
        FlowPane flwp3=new FlowPane();
        FlowPane flwp4=new FlowPane();
        Label lbl0=new Label("Il doit y avoir un blinky(fantome rouge) sur la carte.");
        Label lbl1=new Label("Blinky doit se trouver sur la case au dessus \nde la porte de la maison des fantomes.");
        Label lbl2=new Label("PacMan doit se trouver sur la carte.");
        Label lbl3=new Label("Il doit y avoir des points à manger pour PacMan");
        Label lbl4=new Label("Avoir un nom de carte");
        lbl0.setTextFill(Color.WHITE);
        lbl1.setTextFill(Color.WHITE);
        lbl2.setTextFill(Color.WHITE);
        lbl3.setTextFill(Color.WHITE);
        lbl4.setTextFill(Color.WHITE);
        flwp0.getChildren().addAll(symbolIntegrity[0],lbl0);
        flwp1.getChildren().addAll(symbolIntegrity[1],lbl1);
        flwp2.getChildren().addAll(symbolIntegrity[2],lbl2);
        flwp3.getChildren().addAll(symbolIntegrity[3],lbl3);
        flwp4.getChildren().addAll(symbolIntegrity[4],lbl4);

        paneVerif.getChildren().addAll(flwp0,flwp1,flwp2,flwp3,buttonCheck,tFOut,flwp4,buttonExport);
        paneVerif.setSpacing(10);
        paneVerif.setAlignment(Pos.CENTER);
    }

    private void placementBoutons() {
        FlowPane flp0=new FlowPane();
        FlowPane flp1=new FlowPane();
        FlowPane flp2=new FlowPane();
        FlowPane flp3=new FlowPane();
        FlowPane flp4=new FlowPane();
        flp0.getChildren().addAll(buttonSE, buttonSO, buttonNS, buttonPO);
        flp1.getChildren().addAll(buttonNE,buttonNO,buttonEO,buttonPG);
        flp2.getChildren().addAll(buttonPac,button0);
        flp3.getChildren().addAll(buttonP);
        flp4.getChildren().addAll(buttonf1,buttonf2,buttonf3,buttonf4);
        flp0.setAlignment(Pos.CENTER);
        flp0.setHgap(20);
        flp1.setAlignment(Pos.CENTER);
        flp1.setHgap(20);
        flp2.setAlignment(Pos.CENTER);
        flp2.setHgap(20);
        flp3.setAlignment(Pos.CENTER);
        flp3.setHgap(20);
        flp4.setAlignment(Pos.CENTER);
        flp4.setHgap(20);
        paneBoutons.getChildren().addAll(flp0,flp1,flp2,flp3,flp4);
        paneBoutons.setSpacing(20);
        paneBoutons.setAlignment(Pos.CENTER);

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
        buttonCheck.setOnAction(handler);
        buttonExport.setOnAction(handler);
    }

    public void videCase(Rectangle r){
        r.setFill(new ImagePattern(listImage[10]));
    }

    public void updateIntegrity(boolean b0, boolean b1, boolean b2, boolean b3, Boolean b4){
        if(b0){
            symbolIntegrity[0].setFill(new ImagePattern(greenLight[1]));
        }else {
            symbolIntegrity[0].setFill(new ImagePattern(greenLight[0]));
        }
        if(b1){
            symbolIntegrity[1].setFill(new ImagePattern(greenLight[1]));
        }else {
            symbolIntegrity[1].setFill(new ImagePattern(greenLight[0]));
        }
        if(b2){
            symbolIntegrity[2].setFill(new ImagePattern(greenLight[1]));
        }else {
            symbolIntegrity[2].setFill(new ImagePattern(greenLight[0]));
        }
        if(b3){
            symbolIntegrity[3].setFill(new ImagePattern(greenLight[1]));
        }else {
            symbolIntegrity[3].setFill(new ImagePattern(greenLight[0]));
        }
        if(b4){
            symbolIntegrity[4].setFill(new ImagePattern(greenLight[1]));
        }else {
            symbolIntegrity[4].setFill(new ImagePattern(greenLight[0]));
        }
    }
}
