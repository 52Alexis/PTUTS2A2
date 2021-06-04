package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ViewMap {
    protected ModelMap modelMap;
    protected Stage primaryStage;
    protected Rectangle[][] cases;
    protected ArrayList<Image> images_mur;


    public ViewMap(ModelMap modelMap,Stage stage) {
        this.modelMap = modelMap;
        this.primaryStage=stage;
        images_mur=new ArrayList<>();
        try{
            loadimage();
        }catch (Exception e){
            e.printStackTrace();
        }
        addWidgetsToView();
    }

    public void loadimage() throws FileNotFoundException {
        images_mur.add(new Image(new FileInputStream("img/casevide.png")));      //0
        images_mur.add(new Image(new FileInputStream("img/lignehori.png")));     //1
        images_mur.add(new Image(new FileInputStream("img/ligneverti.png")));    //2
        images_mur.add(new Image(new FileInputStream("img/coindroitbas.png")));  //3
        images_mur.add(new Image(new FileInputStream("img/coindroithaut.png"))); //4
        images_mur.add(new Image(new FileInputStream("img/coingauchebas.png"))); //5
        images_mur.add(new Image(new FileInputStream("img/coingauchehaut.png")));//6
    }

    public void addWidgetsToView(){
        GridPane root = new GridPane();
        cases=new Rectangle[modelMap.X][modelMap.Y];
        for (int i=0; i<modelMap.getX();i++){
            for(int j=0;j< modelMap.getY();j++){
                cases[i][j] =new Rectangle(i*modelMap.getTailleCase(),i*modelMap.getTailleCase(),modelMap.getTailleCase(),modelMap.getTailleCase());
                color(i,j);
                mur(i,j);
                root.add(cases[i][j],i,j,1,1);
            }
        }
        Scene sceneMap = new Scene(root,modelMap.getX()*modelMap.getTailleCase(),modelMap.getY()*modelMap.getTailleCase());
        primaryStage.setScene(sceneMap);
        primaryStage.setResizable(false);
    }

    public void  display(){
        primaryStage.show();
    }

    public void mur(int x, int y){
        Rectangle rect=cases[x][y];
        Case[][] model =modelMap.getCases();
        boolean murNord;
        boolean murSud;
        boolean murEst;
        boolean murOuest;
        if(!model[x][y].isMur()){
            rect.setFill(new ImagePattern(images_mur.get(0)));
        }else{
            try {
                murNord= model[x][y-1].isMur();
            }catch (Exception e){
                murNord=false;
            }
            try {
                murSud= model[x][y+1].isMur();
            }catch (Exception e){
                murSud=false;
            }
            try {
                murEst= model[x+1][y].isMur();
            }catch (Exception e){
                murEst=false;
            }
            try {
                murOuest= model[x - 1][y].isMur();
            }catch (Exception e){
                murOuest=false;
            }
            if(murNord){
                if(murSud){
                    if(murEst){
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(0))); //plein
                        }else {
                            rect.setFill(new ImagePattern(images_mur.get(2))); // croisement en T NSE
                        }
                    }else {
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(2))); // croisement en T NSO
                        }else {
                            rect.setFill(new ImagePattern(images_mur.get(2))); // croisement en NS ligne verticale
                        }
                    }
                }else{
                    if(murEst){
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(1))); // croisement en T NEO
                        }else {
                            rect.setFill(new ImagePattern(images_mur.get(3))); // croisement en NE
                        }
                    }else {
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(5))); // croisement en NO
                        }else {
                            rect.setFill(new ImagePattern(images_mur.get(2))); // croisement en N
                        }
                    }
                }
            }else{
                if(murSud){
                    if(murEst){
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(1))); // croisement en T SEO
                        }else {
                            rect.setFill(new ImagePattern(images_mur.get(6))); // croisement en SE
                        }
                    }else {
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(4))); // croisement en SO
                        }else {
                            rect.setFill(new ImagePattern(images_mur.get(2))); // croisement S
                        }
                    }
                }else{
                    if(murEst){
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(1))); // croisement en EO ligne horizontale
                        }else {
                            rect.setFill(new ImagePattern(images_mur.get(1))); // croisement en E
                        }
                    }else {
                        if(murOuest){
                            rect.setFill(new ImagePattern(images_mur.get(1))); // croisement en O
                        }else {
                            rect.setFill(Color.FUCHSIA); // pas de croisement
                        }
                    }
                }
            }
        }
    }

    public void color(int x,int y){
        Rectangle uneCase=cases[x][y];
        if (modelMap.cases[x][y].haveMobile()){
            Mobile mobile=modelMap.cases[x][y].mobile;
            switch (mobile.type) {
                case 1 -> uneCase.setFill(Color.YELLOW);
                case 2 -> uneCase.setFill(Color.RED);
                case 3 -> uneCase.setFill(Color.PINK);
                case 4 -> uneCase.setFill(Color.BLUE);
                case 5 -> uneCase.setFill(Color.ORANGE);
            }
        }else {
            if (!modelMap.cases[x][y].isMur()) {
                uneCase.setFill(new ImagePattern(images_mur.get(0)));
            }
        }
    }

    public void colorAll(){
        for (int i=0; i<modelMap.getX();i++){
            for(int j=0;j< modelMap.getY();j++){
                color(i,j);
            }
        }
    }

}
