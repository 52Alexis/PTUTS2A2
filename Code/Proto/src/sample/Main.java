package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PacMan");
        File file;
        try {
            file = new File("data/mainMap.map");
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        ModelMap modelMap = new ModelMap(file);
        ModelMap.createBonus();
        ViewMap viewMap = new ViewMap(modelMap, primaryStage);
        ControllerMap controllerMap = new ControllerMap(viewMap,modelMap);
        controllerMap.setController();
        viewMap.display();

        //ModelMenu modelMenu = new ModelMenu();
        //ViewMenu viewMenu = new ViewMenu(modelMenu,primaryStage);
        //viewMenu.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
