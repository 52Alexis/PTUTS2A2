package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
                uneCase.setFill(Color.PURPLE);
            }else{
                uneCase.setFill(Color.BLACK);
            }
            if (modelMap.cases[i].haveMobile()){
                Mobile mobile=modelMap.cases[i].mobile;
                switch (mobile.type){
                    case 1:
                        uneCase.setFill(Color.YELLOW);
                        break;
                    case 2:
                        uneCase.setFill(Color.RED);
                        break;
                    case 3:
                        uneCase.setFill(Color.PINK);
                        break;
                    case 4:
                        uneCase.setFill(Color.BLUE);
                        break;
                    case 5:
                        uneCase.setFill(Color.ORANGE);
                        break;
                }
            }
            uneCase.setStroke(Color.GREEN);
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
