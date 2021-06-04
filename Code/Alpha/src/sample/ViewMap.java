package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewMap {
    protected ModelMap modelMap;
    protected Stage primaryStage;


    public ViewMap(ModelMap modelMap,Stage stage) {
        this.modelMap = modelMap;
        this.primaryStage=stage;
        addWidgetsToView();
    }

    public void addWidgetsToView(){
        FlowPane root = new FlowPane();
        for (int i=0; i<modelMap.getCases().length;i++){
            Rectangle uneCase = new Rectangle(i*modelMap.getTailleCase(),i*modelMap.getTailleCase(),modelMap.getTailleCase(),modelMap.getTailleCase());
            if(modelMap.cases[i].isMur()){
                uneCase.setFill(Color.RED);
            }else {
                try {
                    Image image = new Image(new FileInputStream("images/walls/lignehori.png"));
                    uneCase.setFill(new ImagePattern(image));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //uneCase.setStroke(Color.GREEN);
            root.getChildren().add(uneCase);
        }
        Scene sceneMap = new Scene(root,modelMap.getX()*modelMap.getTailleCase(),modelMap.getY()*modelMap.getTailleCase());
        primaryStage.setScene(sceneMap);
        primaryStage.setResizable(false);
    }

    public void  display(){
        primaryStage.show();
    }

}
