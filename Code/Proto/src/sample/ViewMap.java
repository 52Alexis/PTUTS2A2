package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewMap {
    protected ModelMap modelMap;
    protected Stage primaryStage;
    protected Rectangle[][] cases;


    public ViewMap(ModelMap modelMap,Stage stage) {
        this.modelMap = modelMap;
        this.primaryStage=stage;
        addWidgetsToView();
    }

    public void addWidgetsToView(){
        GridPane root = new GridPane();
        cases=new Rectangle[modelMap.X][modelMap.Y];
        for (int i=0; i<modelMap.getX();i++){
            for(int j=0;j< modelMap.getY();j++){
                cases[i][j] =new Rectangle(i*modelMap.getTailleCase(),i*modelMap.getTailleCase(),modelMap.getTailleCase(),modelMap.getTailleCase());
                color(i,j);
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

    public void color(int x,int y){
        Rectangle uneCase=cases[x][y];
        if(modelMap.cases[x][y].isMur()){
            uneCase.setFill(Color.PURPLE);
        }else{
            uneCase.setFill(Color.BLACK);
        }
        if (modelMap.cases[x][y].haveMobile()){
            Mobile mobile=modelMap.cases[x][y].mobile;
            switch (mobile.type) {
                case 1 -> uneCase.setFill(Color.YELLOW);
                case 2 -> uneCase.setFill(Color.RED);
                case 3 -> uneCase.setFill(Color.PINK);
                case 4 -> uneCase.setFill(Color.BLUE);
                case 5 -> uneCase.setFill(Color.ORANGE);
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
