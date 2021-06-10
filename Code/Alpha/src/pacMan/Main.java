package pacMan;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PacMan");
        StaticMusic.initMusic();

        ViewMenu viewMenu = new ViewMenu(primaryStage);
        viewMenu.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
