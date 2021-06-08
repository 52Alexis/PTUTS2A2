package pacMan;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
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
    protected ArrayList<Image> spritesBonus;


    protected ArrayList<Rectangle> listMobile;
    protected Rectangle bonus;
    protected int lastdirection;
    protected boolean hasMoved;
    protected int cycleAnim;
    protected Label labelScore;
    protected GridPane paneVies;


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
        spritesBonus=new ArrayList<>();

        images_mur.add(new Image(new FileInputStream("img/Walls/0.png")));  //0
        images_mur.add(new Image(new FileInputStream("img/Walls/EO.png"))); //1
        images_mur.add(new Image(new FileInputStream("img/Walls/NS.png"))); //2
        images_mur.add(new Image(new FileInputStream("img/Walls/NO.png"))); //3
        images_mur.add(new Image(new FileInputStream("img/Walls/SO.png"))); //4
        images_mur.add(new Image(new FileInputStream("img/Walls/SE.png"))); //5
        images_mur.add(new Image(new FileInputStream("img/Walls/NE.png"))); //6
        images_mur.add(new Image(new FileInputStream("img/Walls/P.png")));  //7
        images_mur.add(new Image(new FileInputStream("img/Walls/PO.png"))); //points -8
        images_mur.add(new Image(new FileInputStream("img/Walls/PG.png"))); //pacgum -9

        for(int i=0;i<24;i++){
            spritesPacman.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/normal/"+i+".png")));
        }
        for(int i=0;i<16;i++){
            spritesPacmanDead.add(new Image(new FileInputStream("img/Entity/Mobile/Pacman/death/"+i+".png")));
        }
        for(int i=0;i<16;i++){
            spritesBlinky.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/blinky/"+i+".png")));
        }
        for(int i=0;i<16;i++){
            spritesPinky.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/pinky/"+i+".png")));
        }
        for(int i=0;i<16;i++){
            spritesInky.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/inky/"+i+".png")));
        }
        for(int i=0;i<16;i++){
            spritesClyde.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/clyde/"+i+".png")));
        }
        for(int i=0;i<16;i++){
            spritesGumFantome.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/gum/"+i+".png")));
        }
        for(int i=0;i<8;i++){
            spritesDeadFantome.add(new Image(new FileInputStream("img/Entity/Mobile/Fantome/dead/"+i+".png")));
        }
        for(int i=0;i<8;i++){
            spritesBonus.add(new Image(new FileInputStream("img/Entity/Fixe/Bonus/"+i+".png")));
        }


    }

    public void addWidgetsToView(){
        Pane root=new Pane();
        GridPane grid = new GridPane();
        bonus=new Rectangle();
        bonus.setHeight(modelMap.getTailleCase());
        bonus.setWidth(modelMap.getTailleCase());
        bonus.setX(modelMap.getPacman().emplacement.getX()*modelMap.getTailleCase()-4);
        bonus.setY(modelMap.getPacman().emplacement.getY()*modelMap.getTailleCase()+4);
        cases=new Rectangle[modelMap.X][modelMap.Y];
        for (int i=0; i<modelMap.getX();i++){
            for(int j=0;j< modelMap.getY();j++){
                cases[i][j] =new Rectangle(i*modelMap.getTailleCase(),i*modelMap.getTailleCase(),modelMap.getTailleCase(),modelMap.getTailleCase());
                mur(i,j);
                grid.add(cases[i][j],i,j,1,1);
            }
        }
        HBox boxBas = new HBox();
        paneVies = new GridPane();
        paneVies.setHgap(5);
        paneVies.setVgap(10);
        for (int i = 0; i<Pacman.getVies(); i++){
            Label label = new Label();
            label.setGraphic(new ImageView(new Image("file:img/Entity/Mobile/Pacman/normal/21.png")));
            paneVies.add(label,i,0);
        }

        labelScore = new Label(Pacman.getScore()+"");
        labelScore.setTextAlignment(TextAlignment.RIGHT);
        boxBas.getChildren().addAll(paneVies,labelScore);
        boxBas.setTranslateY(modelMap.getY()*modelMap.getTailleCase());

        root.getChildren().addAll(grid,boxBas);
        initEntity();
        root.getChildren().add(bonus);
        for(Rectangle rect:listMobile){
            root.getChildren().add(rect);
        }
        setPoints();
        Scene sceneMap = new Scene(root,modelMap.getX()*modelMap.getTailleCase(),modelMap.getY()*modelMap.getTailleCase()+30);
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

    public void move(){
        for(int i=0; i<modelMap.getListMobile().size();i++) {
            listMobile.get(i).setX(modelMap.getListMobile().get(i).emplacement.x * modelMap.getTailleCase() - 4);
            listMobile.get(i).setY(modelMap.getListMobile().get(i).emplacement.y * modelMap.getTailleCase() - 4);
        }
    }

    public void manageVie(){

    }

    public void manageScore(){

    }

    public void anim(){
        setPoints();
        ArrayList<Mobile> modelListMobile=modelMap.getListMobile();
        ImagePattern sprite;
        for(int i=0;i<modelListMobile.size();i++){
            if(i==0){
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
                int an=getAngle(fantome);
                if(fantome.isGum()||fantome.isDead()){
                    if(fantome.isGum()) {
                        sprite = new ImagePattern(spritesGumFantome.get(an+modelMap.getT()%2));
                    }else {
                        sprite = new ImagePattern(spritesDeadFantome.get(an/2));
                    }
                }else {
                    switch (fantome.type) {
                        case 1:
                            sprite = new ImagePattern(spritesBlinky.get(an+modelMap.getT()%2));
                            break;
                        case 2:
                            sprite = new ImagePattern(spritesPinky.get(an+modelMap.getT()%2));
                            break;
                        case 3:
                            sprite = new ImagePattern(spritesInky.get(an+modelMap.getT()%2));
                            break;
                        case 4:
                            sprite = new ImagePattern(spritesClyde.get(an+modelMap.getT()%2));
                            break;
                        default:
                            return;
                    }
                }
            }
            listMobile.get(i).setFill(sprite);
        }
    }

    public void deathAnim(){
        listMobile.get(0).setFill(new ImagePattern(spritesPacmanDead.get(cycleAnim)));
    }

    public void allMur(){
        for(int i=0;i<modelMap.getX();i++){
            for(int j=0;j<modelMap.getY();j++){
                mur(i,j);
            }
        }
    }

    public void setPoints(){
        allMur();
        int i;
        for(Point p:modelMap.getListPointDispo()){
            if(p==null||p.emplacement==null){
                return;
            }
            if(p.gum){
                i=9;
            }else{
                i=8;
            }
            cases[p.emplacement.getX()][p.emplacement.getY()].setFill(new ImagePattern(images_mur.get(i)));
        }
    }

    public void setBonus(){
        ImagePattern img;
        if(modelMap.getCurrentBonus()!=null){
            img=new ImagePattern(spritesBonus.get(modelMap.searchBonusValue(modelMap.getCurrentBonus().score)));
        }else{
            img=new ImagePattern(images_mur.get(0));
        }
        bonus.setFill(img);
    }

    public int getAngle(Fantome f){
        int x=modelMap.getPacman().emplacement.getX()-f.emplacement.getX();
        int y=modelMap.getPacman().emplacement.getY()-f.emplacement.getY();
        double angle=Math.atan2(y,x);
        angle=Math.toDegrees(angle);
        int group;
        if(angle<=157.5){
            if(angle<=112.5){
                if(angle<=67.5){
                    if(angle<=27.5){
                        if(angle<=-22.5){
                            if(angle<=-67.5){
                                if(angle<=112.5){
                                    if(angle<=157.5){
                                        group=0;
                                    }else{
                                        group=1;
                                        //haut gauche
                                    }
                                }else {
                                    group=2;
                                    //haut
                                }
                            }else{
                                group=3;
                                //haut droit
                            }
                        }else {
                            group=4;
                            //droite
                        }
                    }else{
                        group=5;
                        //bas droite
                    }
                }else {
                    group=6;
                    //bas
                }
            }else {
                group=7;
                //bas gauche
            }
        }else{
            group=0;
            //gauche
        }
        group=group*2;
        return group;
    }

}