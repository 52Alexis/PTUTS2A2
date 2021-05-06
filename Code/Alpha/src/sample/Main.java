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

public class Main extends Application {
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PacMan");
        ModelMap modelMap = new ModelMap();
        ViewMap viewMap = new ViewMap(primaryStage);
        viewMap.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
