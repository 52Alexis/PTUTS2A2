package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class ViewMap {
    protected ModelMap modelMap;
    protected Stage primaryStage;
    protected ArrayList<String> configTouches;


    public ViewMap(ModelMap modelMap,Stage stage) {
        this.modelMap = modelMap;
        this.primaryStage=stage;
        addWidgetsToView();
    }

    public void addWidgetsToView(){
        File file = new File("user/config.cfg");
        configTouches = new ArrayList<>(4);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int i = 0; i<4;i++){
                configTouches.add(i,String.valueOf(reader.readLine()));
                System.out.println("Read : " + configTouches.get(i));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FlowPane root = new FlowPane();
        for (int i=0; i<modelMap.getCases().length;i++){
            Rectangle uneCase = new Rectangle(i*modelMap.getTailleCase(),i*modelMap.getTailleCase(),modelMap.getTailleCase(),modelMap.getTailleCase());
            if(modelMap.cases[i].isMur() || i==50){
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
        sceneMap.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String code = keyEvent.getCode().toString();
                System.out.println(keyEvent.getCode());
                System.out.println(getConfigTouches().toString());
                if (code.matches(getConfigTouches().get(0))){
                    System.out.println("Pressed : " + getConfigTouches().get(0));
                    root.getChildren().get(50).setTranslateY(-10);
                }
                if (keyEvent.getCode() == KeyCode.valueOf(getConfigTouches().get(1))){
                    System.out.println("Pressed : " + getConfigTouches().get(1));
                    root.getChildren().get(50).setTranslateY(+10);
                }
                if (keyEvent.getCode() == KeyCode.getKeyCode(getConfigTouches().get(2))){
                    System.out.println("Pressed : " + getConfigTouches().get(2));
                    root.getChildren().get(50).setTranslateX(-10);
                }
                if (keyEvent.getCode() == KeyCode.getKeyCode(getConfigTouches().get(3))){
                    System.out.println("Pressed : " + getConfigTouches().get(3));
                    root.getChildren().get(50).setTranslateY(+10);
                }

            }
        });
        primaryStage.setScene(sceneMap);
        primaryStage.setResizable(false);
    }

    public void  display(){
        primaryStage.show();
    }

    public ArrayList<String> getConfigTouches() {
        return configTouches;
    }
}
