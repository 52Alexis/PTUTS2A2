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
    protected ArrayList<Image> spritesPacman;
    protected ArrayList<Image> spritesBlinky;
    protected ArrayList<Image> spritesPinky;
    protected ArrayList<Image> spritesInky;
    protected ArrayList<Image> spritesClyde;
    protected ArrayList<Image> spritesGumFantome;
    protected ArrayList<Image> spritesDeadFantome;
    protected ArrayList<Image> spritesPacmanDead;


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
        images_mur.add(new Image(new FileInputStream("img/Walls/0.png")));  //0
        images_mur.add(new Image(new FileInputStream("img/Walls/EO.png"))); //1
        images_mur.add(new Image(new FileInputStream("img/Walls/NS.png"))); //2
        images_mur.add(new Image(new FileInputStream("img/Walls/NO.png"))); //3
        images_mur.add(new Image(new FileInputStream("img/Walls/SO.png"))); //4
        images_mur.add(new Image(new FileInputStream("img/Walls/SE.png"))); //5
        images_mur.add(new Image(new FileInputStream("img/Walls/NE.png"))); //6
        images_mur.add(new Image(new FileInputStream("img/Walls/P.png")));  //7

        spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/0.png")));
        spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/1.png")));
        spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/2.png")));
        spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/3.png")));
        spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/4.png")));
        spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/5.png")));
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
        String mur=modelMap.getCase(x,y).getTypeMur();
        switch (mur) {
            case "EO" -> rect.setFill(new ImagePattern(images_mur.get(1)));
            case "NS" -> rect.setFill(new ImagePattern(images_mur.get(2)));
            case "NO" -> rect.setFill(new ImagePattern(images_mur.get(3)));
            case "SO" -> rect.setFill(new ImagePattern(images_mur.get(4)));
            case "SE" -> rect.setFill(new ImagePattern(images_mur.get(5)));
            case "NE" -> rect.setFill(new ImagePattern(images_mur.get(6)));
            case "P" -> rect.setFill(new ImagePattern(images_mur.get(7)));
            default -> rect.setFill(new ImagePattern(images_mur.get(0)));
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
