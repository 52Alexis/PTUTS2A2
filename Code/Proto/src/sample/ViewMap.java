package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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


    protected ArrayList<Rectangle> listMobile;
    protected int lastdirection;
    protected boolean hasMoved;


    public ViewMap(ModelMap modelMap,Stage stage) {
        this.modelMap = modelMap;
        this.primaryStage=stage;
        try{
            loadimage();
        }catch (Exception e){
            e.printStackTrace();
        }
        addWidgetsToView();
    }

    public void loadimage() throws FileNotFoundException {
        images_mur=new ArrayList<>();
        spritesPacman=new ArrayList<>();
        spritesBlinky=new ArrayList<>();
        spritesPinky=new ArrayList<>();
        spritesInky=new ArrayList<>();
        spritesClyde=new ArrayList<>();
        spritesGumFantome=new ArrayList<>();
        spritesDeadFantome=new ArrayList<>();
        spritesPacmanDead=new ArrayList<>();

        images_mur.add(new Image(new FileInputStream("img/Walls/0.png")));  //0
        images_mur.add(new Image(new FileInputStream("img/Walls/EO.png"))); //1
        images_mur.add(new Image(new FileInputStream("img/Walls/NS.png"))); //2
        images_mur.add(new Image(new FileInputStream("img/Walls/NO.png"))); //3
        images_mur.add(new Image(new FileInputStream("img/Walls/SO.png"))); //4
        images_mur.add(new Image(new FileInputStream("img/Walls/SE.png"))); //5
        images_mur.add(new Image(new FileInputStream("img/Walls/NE.png"))); //6
        images_mur.add(new Image(new FileInputStream("img/Walls/P.png")));  //7

        for(int i=0;i<24;i++){
            spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/"+i+".png")));
        }
        for(int i=0;i<16;i++){
            spritesPacmanDead.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/death/"+i+".png")));
        }
        for(int i=0;i<8;i++){
            spritesBlinky.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/blinky/"+i+".png")));
        }
        for(int i=0;i<8;i++){
            spritesPinky.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/pinky/"+i+".png")));
        }
        for(int i=0;i<8;i++){
            spritesInky.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/inky/"+i+".png")));
        }
        for(int i=0;i<8;i++){
            spritesClyde.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/clyde/"+i+".png")));
        }

        for(int i=0;i<8;i++){
            spritesGumFantome.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/gum/"+i+".png")));
        }
        for(int i=0;i<8;i++){
            spritesDeadFantome.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/dead/"+i+".png")));
        }


    }

    public void addWidgetsToView(){
        Pane root=new Pane();
        GridPane grid = new GridPane();
        cases=new Rectangle[modelMap.X][modelMap.Y];
        for (int i=0; i<modelMap.getX();i++){
            for(int j=0;j< modelMap.getY();j++){
                cases[i][j] =new Rectangle(i*modelMap.getTailleCase(),i*modelMap.getTailleCase(),modelMap.getTailleCase(),modelMap.getTailleCase());
                color(i,j);
                mur(i,j);
                grid.add(cases[i][j],i,j,1,1);
            }
        }
        root.getChildren().add(grid);
        initEntity();
        for(Rectangle rect:listMobile){
            root.getChildren().add(rect);
        }
        Scene sceneMap = new Scene(root,modelMap.getX()*modelMap.getTailleCase(),modelMap.getY()*modelMap.getTailleCase());
        primaryStage.setScene(sceneMap);
        primaryStage.setResizable(false);
    }

    public void  display(){
        primaryStage.show();
    }

    public void initEntity(){
        listMobile=new ArrayList<>();
        for(int i=0; i<modelMap.getListMobile().size();i++){
            listMobile.add(new Rectangle());
            listMobile.get(i).setHeight(24);
            listMobile.get(i).setWidth(24);
        }
        hasMoved=false;

        move();
        anim();
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

    public void move(){
        for(int i=0; i<modelMap.getListMobile().size();i++) {
            listMobile.get(i).setX(modelMap.getListMobile().get(i).emplacement.x * modelMap.getTailleCase() - 4);
            listMobile.get(i).setY(modelMap.getListMobile().get(i).emplacement.y * modelMap.getTailleCase() - 4);
        }
    }

    public void anim(){
        ArrayList<Mobile> modelListMobile=modelMap.getListMobile();
        ImagePattern sprite;
        System.out.println(modelListMobile.size());
        for(int i=0;i<modelListMobile.size();i++){
            if(i==0){
                System.out.println("p"+i);
                if(modelMap.getPacman().direction!=0 || hasMoved) {
                    if(modelMap.getPacman().direction!=0) {
                        lastdirection = modelMap.getPacman().direction;
                    }
                    hasMoved=true;
                    sprite = new ImagePattern(spritesPacman.get(((spritesPacman.size()/4) * (lastdirection - 1)) + (modelMap.getT() % (spritesPacman.size()/4))));

                }else{
                    sprite = new ImagePattern(spritesPacman.get(0));
                }
            }else{
                Fantome fantome=modelMap.getFantome(i);
                System.out.println("f"+i);
                switch (fantome.type){
                    case 1:
                        sprite=new ImagePattern(spritesBlinky.get(0));
                        break;
                    case 2:
                        sprite=new ImagePattern(spritesPinky.get(0));
                        break;
                    case 3:
                        sprite=new ImagePattern(spritesInky.get(0));
                        break;
                    case 4:
                        sprite=new ImagePattern(spritesClyde.get(0));
                        break;
                    default:
                        return;
                }
            }
            listMobile.get(i).setFill(sprite);
        }
    }

}
